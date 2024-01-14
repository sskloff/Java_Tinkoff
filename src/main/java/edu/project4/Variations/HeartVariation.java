package edu.project4.Variations;

public class HeartVariation implements Variation {

    public HeartVariation() {
    }

    @Override
    public double applyX(double x, double y) {
        return Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2))
            * Math.sin(Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2)) * Math.atan(y / x));
    }

    @Override
    public double applyY(double x, double y) {
        return -1 * Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2))
            * Math.cos(Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2)) * Math.atan(y / x));
    }
}
