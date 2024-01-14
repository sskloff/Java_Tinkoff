package edu.project4.Variations;

public class PolarVariation implements Variation {

    public PolarVariation() {
    }

    @Override
    public double applyX(double x, double y) {
        return Math.atan(y / x) / Math.PI;
    }

    @Override
    public double applyY(double x, double y) {
        return Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2)) - 1;
    }
}
