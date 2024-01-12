package edu.hw7;

import java.util.concurrent.atomic.AtomicInteger;

public class Task1 {

    public static final int CORES_COUNT = Runtime.getRuntime().availableProcessors();

    private Task1() {
    }

    public static int incrementWithConcurrency(int expectedValue) throws InterruptedException {
        AtomicInteger value = new AtomicInteger(0);
        Thread[] threads = new Thread[CORES_COUNT];
        for (int i = 0; i < threads.length; i++) {
            Thread thread = new Thread(() -> {
                for (int j = 0; j < expectedValue / threads.length; j++) {
                    value.incrementAndGet();
                }
            });
            thread.start();
            threads[i] = thread;
        }
        for (Thread thread : threads) {
            thread.join();
        }
        return value.get();
    }
}
