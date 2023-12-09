package edu.hw9.Task1;

public class Stat {

    private final double sum;
    private final double avg;
    private final double max;
    private final double min;

    public Stat(double[] values) {
        double localSum = 0;
        double localMin = Double.MAX_VALUE;
        double localMax = Double.MIN_VALUE;
        for (double value : values) {
            localSum += value;
            if (value > localMax) {
                localMax = value;
            }
            if (value < localMin) {
                localMin = value;
            }
        }

        this.max = localMax;
        this.min = localMin;
        this.sum = localSum;
        this.avg = localSum / values.length;
    }

    public double getSum() {
        return sum;
    }

    public double getAvg() {
        return avg;
    }

    public double getMax() {
        return max;
    }

    public double getMin() {
        return min;
    }
}
