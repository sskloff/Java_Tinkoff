package edu.project4.Variations;

public class DiscVariation implements Variation {

    public DiscVariation() {
    }

    @Override
    public double applyX(double x, double y) {
        return Math.atan(y / x) * Math.sin(Math.PI * Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2))) / Math.PI;
    }

    @Override
    public double applyY(double x, double y) {
        return Math.atan(y / x) * Math.cos(Math.PI * Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2))) / Math.PI;
    }
}
