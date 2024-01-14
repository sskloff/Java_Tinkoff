package edu.project4.Variations;

public class SinusoidalVariation implements Variation {

    public SinusoidalVariation() {
    }

    @Override
    public double applyX(double x, double y) {
        return Math.sin(x);
    }

    @Override
    public double applyY(double x, double y) {
        return Math.sin(y);
    }
}
