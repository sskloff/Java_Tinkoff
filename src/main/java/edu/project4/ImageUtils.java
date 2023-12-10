package edu.project4;

import edu.project4.Models.ImageFormat;
import edu.project4.Models.Pixel;
import javax.imageio.ImageIO;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;

public final class ImageUtils {

    private ImageUtils() {
    }

    public static void correctGamma(Pixel[][] pixels, double gamma) {
        double max = 0.0;
        for (Pixel[] pixel : pixels) {
            for (int col = 0; col < pixels[0].length; col++) {
                if (pixel[col].getHitCount() != 0) {
                    pixel[col].setNormal(Math.log10(pixel[col].getHitCount()));
                    if (pixel[col].getNormal() > max) {
                        max = pixel[col].getNormal();
                    }
                }
            }
        }
        for (Pixel[] pixel : pixels) {
            for (int col = 0; col < pixels[0].length; col++) {
                pixel[col].setNormal(pixel[col].getNormal() / max);
                pixel[col].setRed(
                    pixel[col].getRed() * Math.pow(pixel[col].getNormal(), (1.0 / gamma)))
                ;
                pixel[col].setGreen(
                    pixel[col].getGreen() * Math.pow(pixel[col].getNormal(), (1.0 / gamma))
                );
                pixel[col].setBlue(
                    pixel[col].getBlue() * Math.pow(pixel[col].getNormal(), (1.0 / gamma))
                );
            }
        }
    }

    public static void generateImage(BufferedImage image, Pixel[][] pixels) {
        for (int i = 0; i < pixels.length; i++) {
            for (int j = 0; j < pixels[0].length; j++) {
                int red = (int) (pixels[i][j].getRed() * 255);
                int green = (int) (pixels[i][j].getGreen() * 255);
                int blue = (int) (pixels[i][j].getBlue() * 255);
                Color color = new Color(red, green, blue);
                image.setRGB(i, j, color.getRGB());
            }
        }
    }

    public static void saveImage(BufferedImage image, Path filename, ImageFormat format) {
        try {
            File output = new File(filename.toString());
            ImageIO.write(image, format.name(), output);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
