package edu.hw7;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.concurrent.atomic.AtomicInteger;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Task1Test {

    public static final int CORES_COUNT = Runtime.getRuntime().availableProcessors();

    @Test
    @DisplayName("Тест счетчика на потокобезопасность")
    void whenExpected1000ThenResult1000() throws InterruptedException {
        //given
        int expected = 8000;
        Thread[] threads = new Thread[CORES_COUNT];
        AtomicInteger result = new AtomicInteger();
        for (int i = 0; i < CORES_COUNT; ++i) {
            Thread thread = new Thread(() -> {
                try {
                    result.addAndGet(Task1.incrementWithConcurrency(expected / CORES_COUNT));
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            });
            thread.start();
            threads[i] = thread;
        }
        for (Thread thread : threads) {
            thread.join();
        }

        //then
        assertEquals(result.get(), expected);
    }
}
