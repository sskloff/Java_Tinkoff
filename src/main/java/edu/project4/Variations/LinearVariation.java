package edu.project4.Variations;

import java.util.Random;

public class LinearVariation {

    private static final Random RANDOM = new Random();
    private final double a;
    private final double b;
    private final double c;
    private final double d;
    private final double e;
    private final double f;
    private final double red;
    private final double green;
    private final double blue;

    private LinearVariation(double a, double b, double c, double d, double e, double f) {
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
        this.e = e;
        this.f = f;
        this.red = RANDOM.nextDouble(0, 1);
        this.green = RANDOM.nextDouble(0, 1);
        this.blue = RANDOM.nextDouble(0, 1);
    }

    public static LinearVariation createLinearVariation(
        double a, double b, double c, double d, double e, double f
    ) {
        if (Math.pow(a, 2) + Math.pow(d, 2) >= 1
            || Math.pow(b, 2) + Math.pow(e, 2) >= 1
            || Math.pow(a, 2) + Math.pow(b, 2) + Math.pow(d, 2)
            + Math.pow(e, 2) >= 1 + Math.pow((a * e - b * d), 2)
        ) {
            return null;
        }
        return new LinearVariation(a, b, c, d, e, f);
    }

    public double a() {
        return a;
    }

    public double b() {
        return b;
    }

    public double c() {
        return c;
    }

    public double d() {
        return d;
    }

    public double e() {
        return e;
    }

    public double f() {
        return f;
    }

    public double red() {
        return red;
    }

    public double green() {
        return green;
    }

    public double blue() {
        return blue;
    }
}
