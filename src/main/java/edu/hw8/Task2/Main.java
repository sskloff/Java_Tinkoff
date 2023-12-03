package edu.hw8.Task2;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Main {

    private Main() {
    }

    public static final int CORES_COUNT = Runtime.getRuntime().availableProcessors();
    public static final int N = 10;
    private final static Logger LOGGER = LogManager.getLogger();

    @SuppressWarnings("MagicNumber")
    public static void main(String[] args) throws Exception {
        try (ThreadPool threadPool = FixedThreadPool.create(CORES_COUNT)) {
            threadPool.start();
            for (int i = 1; i <= N; i++) {
                int fibonacciIndex = i * 5;
                threadPool.execute(() -> {
                    int fibonacciValue = FibonacciCalculation.calculate(fibonacciIndex);
                    LOGGER.info("\nIndex: " + fibonacciIndex + "\nValue: " + fibonacciValue + "\n");
                });
            }
        }
    }
}
