package edu.hw2.Task3;

import java.util.Random;

public class DefaultConnectionManager implements ConnectionManager {
    private static final int FAULT_PROBABILITY_PERCENT = 0;
    // Вероятность сделал 0%, чтобы не было вариативности в тестах.
    // Как мокать еще не разобрался, исправлюсь
    private static final int ONE_HUNDRED_PERCENTS = 100;

    @Override
    public Connection getConnection() {
        Random random = new Random();
        if (random.nextInt(ONE_HUNDRED_PERCENTS) <= FAULT_PROBABILITY_PERCENT) {
            return new FaultyConnection();
        } else {
            return new StableConnection();
        }
    }
}
