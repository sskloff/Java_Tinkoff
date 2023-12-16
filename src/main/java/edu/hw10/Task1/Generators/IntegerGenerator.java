package edu.hw10.Task1.Generators;

import java.util.Random;

public class IntegerGenerator implements Generator {
    private static final Random RANDOM = new Random();

    private final int min;
    private final int max;

    public IntegerGenerator(int min, int max) {
        this.min = min;
        this.max = max;
    }

    @Override
    public Object generate() {
        return RANDOM.nextInt(min, max);
    }
}
