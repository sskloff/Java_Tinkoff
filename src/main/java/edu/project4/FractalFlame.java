package edu.project4;

import edu.project4.Models.NonlinearVariations;
import edu.project4.Models.Pixel;
import edu.project4.Variations.LinearVariation;
import edu.project4.Variations.Variation;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class FractalFlame {

    private static final Random RANDOM = new Random();
    private static final int CORES = 4;
    private int width;
    private int height;
    private double rowMax;
    private double rowMin;
    private double colMax;
    private double colMin;
    private Pixel[][] pixels;
    private ArrayList<LinearVariation> linearVariations;
    private ArrayList<NonlinearVariations> nonlinearVariations;

    public FractalFlame() {
    }

    public static FractalFlame createFractalFlame(
        int width, int height,
        ArrayList<LinearVariation> linearVariations, ArrayList<NonlinearVariations> nonlinearVariations
    ) {
        FractalFlame flame = new FractalFlame();
        flame.width = width;
        flame.height = height;
        flame.linearVariations = linearVariations;
        flame.nonlinearVariations = nonlinearVariations;
        flame.rowMax = (double) width / height;
        flame.rowMin = -(double) width / height;
        flame.colMax = 1.0;
        flame.colMin = -1.0;
        Pixel[][] pixelsMatrix = new Pixel[width][height];
        for (int i = 0; i < pixelsMatrix.length; i++) {
            for (int j = 0; j < pixelsMatrix[0].length; j++) {
                pixelsMatrix[i][j] = new Pixel(0.0, 0.0, 0.0, 0, 0.0);
            }
        }
        flame.pixels = pixelsMatrix;
        return flame;
    }

    public Pixel[][] createWithSingleThread(int iterationsCount) {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        CountDownLatch latch = new CountDownLatch(1);
        executor.execute(new Task(0, linearVariations.size(), iterationsCount, latch));
        executor.shutdown();
        try {
            latch.await();
        } catch (InterruptedException e) {
            throw new RuntimeException();
        }
        return pixels;
    }

    public Pixel[][] createWithMultiThread(int iterationsCount) {
        ExecutorService executor = Executors.newFixedThreadPool(CORES);
        CountDownLatch latch = new CountDownLatch(CORES);
        int taskSize = linearVariations.size() / CORES;
        int remainder = linearVariations.size() % CORES;
        for (int i = 0; i < linearVariations.size() - remainder; i += taskSize) {
            executor.submit(new Task(i, i + taskSize, iterationsCount, latch));
        }
        executor.submit(new Task(
            linearVariations.size() - remainder, linearVariations.size(), iterationsCount, latch)
        );
        executor.shutdown();
        try {
            latch.await();
        } catch (InterruptedException e) {
            throw new RuntimeException();
        }
        return pixels;
    }

    private class Task implements Runnable {
        private final int start;
        private final int end;
        private final int iterationsCount;
        private final CountDownLatch latch;

        private Task(int start, int end, int iterationsCount, CountDownLatch latch) {
            this.start = start;
            this.end = end;
            this.iterationsCount = iterationsCount;
            this.latch = latch;
        }

        @Override
        public void run() {
            for (int i = start; i < end; i++) {
                for (int step = 0; step < iterationsCount; step++) {
                    LinearVariation linearVariation = linearVariations.get(RANDOM.nextInt(linearVariations.size()));
                    double newRow = RANDOM.nextDouble(rowMin, rowMax);
                    double newCol = RANDOM.nextDouble(colMin, colMax);
                    double row = linearVariation.a() * newRow + linearVariation.b() * newCol + linearVariation.c();
                    double col = linearVariation.d() * newRow + linearVariation.e() * newCol + linearVariation.f();

                    int index = RANDOM.nextInt(nonlinearVariations.size());
                    Variation nonlinearVariation = NonlinearVariations.MAP.get(nonlinearVariations.get(index));
                    double x = nonlinearVariation.applyX(row, col);
                    double y = nonlinearVariation.applyY(row, col);
                    if (x >= rowMin && x <= rowMax && y >= colMin && y <= colMax) {
                        int x1 = width - (int) (((rowMax - x) / (rowMax - rowMin)) * width);
                        int y1 = height - (int) (((colMax - y) / (colMax - colMin)) * height);
                        if (x1 < width && y1 < height) {
                            if (pixels[x1][y1].getHitCount() == 0) {
                                pixels[x1][y1].setRed(linearVariation.red());
                                pixels[x1][y1].setGreen(linearVariation.green());
                                pixels[x1][y1].setBlue(linearVariation.blue());
                            } else {
                                pixels[x1][y1].setRed((pixels[x1][y1].getRed() + linearVariation.red()) / 2);
                                pixels[x1][y1].setGreen((pixels[x1][y1].getGreen() + linearVariation.green()) / 2);
                                pixels[x1][y1].setBlue((pixels[x1][y1].getBlue() + linearVariation.blue()) / 2);
                            }
                            pixels[x1][y1].setHitCount(pixels[x1][y1].getHitCount() + 1);
                        }
                    }
                }
            }
            latch.countDown();
        }
    }
}
