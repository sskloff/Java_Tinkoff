package edu.hw10.Task1.Generators;

import java.util.Random;

public class StringGenerator implements Generator {

    private static final Random RANDOM = new Random();
    private static final int MAX_RANDOM_STRING_LENGTH = 10;

    public StringGenerator() {
    }

    @Override
    public Object generate() {
        int randomLength = RANDOM.nextInt(MAX_RANDOM_STRING_LENGTH);
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < randomLength; i++) {
            stringBuilder.append((char) RANDOM.nextInt('a', 'z'));
        }
        return stringBuilder.toString();
    }
}
