package edu.project4.Variations;

public class SphericalVariation implements Variation {

    public SphericalVariation() {
    }

    @Override
    public double applyX(double x, double y) {
        return x / (Math.pow(x, 2) + Math.pow(y, 2));
    }

    @Override
    public double applyY(double x, double y) {
        return y / (Math.pow(x, 2) + Math.pow(y, 2));
    }
}
