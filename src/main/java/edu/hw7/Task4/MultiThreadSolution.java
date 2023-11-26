package edu.hw7.Task4;

import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicInteger;

public class MultiThreadSolution {

    public static final int CORES_COUNT = Runtime.getRuntime().availableProcessors();

    private MultiThreadSolution() {
    }

    @SuppressWarnings("MagicNumber")
    public static float calculatePiWithMultiThread(int totalCount) throws InterruptedException {
        AtomicInteger circleCount = new AtomicInteger(0);
        Thread[] threads = new Thread[CORES_COUNT];
        for (int i = 0; i < threads.length; i++) {
            Thread thread = new Thread(() -> {
                for (int j = 0; j < (totalCount / threads.length); j++) {
                    double x = ThreadLocalRandom.current().nextDouble(0, 1);
                    double y = ThreadLocalRandom.current().nextDouble(0, 1);
                    if (Math.pow(x, 2) + Math.pow(y, 2) < 1) {
                        circleCount.incrementAndGet();
                    }
                }
            });
            thread.start();
            threads[i] = thread;
        }
        for (Thread thread : threads) {
            thread.join();
        }
        return 4 * ((float) circleCount.get() / totalCount);
    }
}
