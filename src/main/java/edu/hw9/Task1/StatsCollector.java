package edu.hw9.Task1;

import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

public class StatsCollector {

    public static final int CORES_COUNT = Runtime.getRuntime().availableProcessors();
    private final ExecutorService executorService = Executors.newFixedThreadPool(CORES_COUNT);
    private final Map<String, Stat> stats = new ConcurrentHashMap<>();
    private final AtomicInteger counter = new AtomicInteger(0);
    private final BlockingQueue<Metric> queue = new LinkedBlockingQueue<>();

    public StatsCollector() {
        for (int i = 0; i < CORES_COUNT; i++) {
            executorService.submit(this::aggregate);
        }
    }

    public void push(String name, double[] values) {
        if (executorService.isShutdown()) {
            throw new IllegalStateException();
        }
        try {
            counter.incrementAndGet();
            queue.put(new Metric(name, values));
        } catch (InterruptedException e) {
            throw new IllegalStateException();
        }
    }

    public void aggregate() {
        try {
            while (!executorService.isShutdown()) {
                Metric metric = queue.take();
                Stat stat = new Stat(metric.values());
                stats.put(metric.name(), stat);
                counter.decrementAndGet();
            }
        } catch (InterruptedException e) {
            throw new IllegalStateException();
        }
    }


    @SuppressWarnings("RegexpSinglelineJava")
    public Map<String, Stat> getStats() {
        executorService.shutdown();
        while (true) {
            if (counter.get() == 0) {
                break;
            }
        }
        return stats;
    }
}
