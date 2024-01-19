package edu.hw8;

import edu.hw8.Task2.FibonacciCalculation;
import edu.hw8.Task2.FixedThreadPool;
import edu.hw8.Task2.ThreadPool;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Task2Test {

    public static final int CORES_COUNT = Runtime.getRuntime().availableProcessors();
    public static final int N = 10;
    private final static Logger LOGGER = LogManager.getLogger();
    private final Map<Integer, Integer> FibonacciValues = Map.ofEntries(
        Map.entry(5, 5),
        Map.entry(10, 55),
        Map.entry(15, 610),
        Map.entry(20, 6765),
        Map.entry(25, 75025),
        Map.entry(30, 832040),
        Map.entry(35, 9227465),
        Map.entry(40, 102334155)
    );


    @Test
    @DisplayName("Расчет чисел Фибоначчи")
    void FibonacciThreadPoolTest() throws Exception {
        try (ThreadPool threadPool = FixedThreadPool.create(CORES_COUNT)) {
            threadPool.start();
            for (int i = 1; i <= N; i++) {
                int fibonacciIndex = i * 5;
                threadPool.execute(() -> {
                    int fibonacciValue = FibonacciCalculation.calculate(fibonacciIndex);
                    assertEquals(fibonacciValue, FibonacciValues.get(fibonacciIndex));
                    LOGGER.info("\nIndex: " + fibonacciIndex + "\nValue: " + fibonacciValue + "\n");
                });
            }
        }
    }
}
