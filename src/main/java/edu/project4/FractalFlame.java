package edu.project4;

import edu.project4.Models.NonlinearVariations;
import edu.project4.Models.Pixel;
import edu.project4.Variations.LinearVariation;
import edu.project4.Variations.Variation;
import java.util.ArrayList;
import java.util.Random;

public class FractalFlame {

    private static final Random RANDOM = new Random();

    public static Pixel[][] createWithSingleThread(int width, int height,
        ArrayList<LinearVariation> linearVariations, ArrayList<NonlinearVariations> nonlinearVariations,
        int iterationsCount
    ) {
        double rowMax = (double) width / height;
        double rowMin = -(double) width / height;
        double colMax = 1.0;
        double colMin = -1.0;
        Pixel[][] pixels = new Pixel[width][height];
        for (int i = 0; i < pixels.length; i++) {
            for (int j = 0; j < pixels[0].length; j++) {
                pixels[i][j] = new Pixel(0.0, 0.0, 0.0, 0, 0.0);
            }
        }
        for (int i = 0; i < linearVariations.size(); i++) {
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
        return pixels;
    }
}
