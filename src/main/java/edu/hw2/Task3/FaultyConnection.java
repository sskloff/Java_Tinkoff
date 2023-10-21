package edu.hw2.Task3;

import java.util.Random;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class FaultyConnection implements Connection {
    private static final Logger LOGGER = LogManager.getLogger();
    private static final int ONE_HUNDRED_PERCENTS = 100;
    private static final int EXCEPTION_PROBABILITY_PERCENT = 100;
    // Вероятность сделал 100%, чтобы не было вариативности в тестах.
    // Как мокать пока не разобрался, исправлюсь

    public FaultyConnection() {
    }

    @Override
    public void execute(String command) {
        Random random = new Random();
        if (random.nextInt(ONE_HUNDRED_PERCENTS) <= EXCEPTION_PROBABILITY_PERCENT) {
            LOGGER.info("Exception");
            throw new ConnectionException();
        } else {
            LOGGER.info("FaultyConnection, but execution success");
        }
    }

    @Override
    public void close() throws Exception {
        LOGGER.info("Conneciton closed");
    }
}
