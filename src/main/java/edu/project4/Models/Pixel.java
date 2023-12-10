package edu.project4.Models;

public class Pixel {
    private double red;
    private double green;
    private double blue;
    private int hitCount;
    private double normal;

    public Pixel(double red, double green, double blue, int hitCount, double normal) {
        this.red = red;
        this.green = green;
        this.blue = blue;
        this.hitCount = hitCount;
        this.normal = normal;
    }

    public double getRed() {
        return red;
    }

    public void setRed(double red) {
        this.red = red;
    }

    public double getGreen() {
        return green;
    }

    public void setGreen(double green) {
        this.green = green;
    }

    public double getBlue() {
        return blue;
    }

    public void setBlue(double blue) {
        this.blue = blue;
    }

    public int getHitCount() {
        return hitCount;
    }

    public void setHitCount(int hitCount) {
        this.hitCount = hitCount;
    }

    public double getNormal() {
        return normal;
    }

    public void setNormal(double normal) {
        this.normal = normal;
    }
}
