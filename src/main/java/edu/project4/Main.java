package edu.project4;

import edu.project4.Models.ImageFormat;
import edu.project4.Models.NonlinearVariations;
import edu.project4.Models.Pixel;
import edu.project4.Variations.LinearVariation;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Random;
import static java.nio.file.Files.deleteIfExists;

public class Main {

    private Main() {
    }

    private static final Path SINGLE_IMAGE_PATH = Path.of("src/main/java/edu/project4/single.png");
    private static final Path MULTI_IMAGE_PATH = Path.of("src/main/java/edu/project4/multi.png");
    private static final double GAMMA = 1.5;
    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;
    private static final int ITERATIONS = 10000;
    private static final int LINEAR_VARIATIONS_COUNT = 100;
    private static final Random RANDOM = new Random();

    @SuppressWarnings("RegexpSinglelineJava")
    public static void main(String[] args) throws IOException {
        //Создание линейных преобразований
        ArrayList<LinearVariation> linearVariations = new ArrayList<>();
        while (linearVariations.size() != LINEAR_VARIATIONS_COUNT) {
            LinearVariation linearVariation =
                LinearVariation.createLinearVariation(
                    RANDOM.nextDouble(1),
                    RANDOM.nextDouble(1),
                    RANDOM.nextDouble(1),
                    RANDOM.nextDouble(1),
                    RANDOM.nextDouble(1),
                    RANDOM.nextDouble(1)
                );
            if (linearVariation != null) {
                linearVariations.add(linearVariation);
            }
        }

        //Выбор нелинейных преобразований
        ArrayList<NonlinearVariations> nonlinearVariations = new ArrayList<>();
        nonlinearVariations.add(NonlinearVariations.DISC);
        nonlinearVariations.add(NonlinearVariations.HEART);

        deleteIfExists(SINGLE_IMAGE_PATH);
        deleteIfExists(MULTI_IMAGE_PATH);
        BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);

        //Отрисовка фрактального пламени в однопотоке
        long before = System.nanoTime();
        Pixel[][] pixels = FractalFlame.createWithSingleThread(WIDTH, HEIGHT,
        linearVariations, nonlinearVariations, ITERATIONS);
        long after = System.nanoTime();
        long singleThreadSolutionTime = after - before;
        System.out.println("Single thread: " + singleThreadSolutionTime);
        ImageUtils.correctGamma(pixels, GAMMA);
        ImageUtils.generateImage(image, pixels);
        ImageUtils.saveImage(image, SINGLE_IMAGE_PATH, ImageFormat.PNG);

//        //Отрисовка фрактального пламени в многопотоке
//        before = System.nanoTime();
//        Pixel[][] pixels2 = fractalFlame.createWithMultiThread(ITERATIONS);
//        after = System.nanoTime();
//        long multiThreadSolutionTime = after - before;
//        System.out.println("Multi thread: " + multiThreadSolutionTime);
//        ImageUtils.correctGamma(pixels2, GAMMA);
//        ImageUtils.generateImage(image2, pixels);
//        ImageUtils.saveImage(image2, MULTI_IMAGE_PATH, ImageFormat.PNG);
    }
}
