package edu.hw9;

import edu.hw9.Task1.Metric;
import edu.hw9.Task1.Stat;
import edu.hw9.Task1.StatsCollector;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;

import static java.util.concurrent.Executors.newFixedThreadPool;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Task1Test {

    private static final int CORES_COUNT = Runtime.getRuntime().availableProcessors();

    @Test
    @DisplayName("Проверка многопоточного сбора статистики")
    void whenExpectedStatsEqualsReceivedThenTrue() throws InterruptedException {
        //given
        List<Metric> metrics = createListOfMetrics();
        ExecutorService executorService = newFixedThreadPool(CORES_COUNT);
        StatsCollector collector = new StatsCollector();

        //when
        for (Metric metric : metrics) {
            executorService.submit(() -> collector.push(metric.name(), metric.values()));
        }
        Thread.sleep(10);
        executorService.shutdown();
        Map<String, Stat> received = collector.getStats();

        //then
        assertEquals(received.get("metric1").getMax(), 1.5, 0.01);
        assertEquals(received.get("metric2").getAvg(), 2.3, 0.01);
        assertEquals(received.get("metric3").getMin(), 3.1, 0.01);
        assertEquals(received.get("metric1").getSum(), 6.5, 0.01);
    }

    public static List<Metric> createListOfMetrics() {
        List<Metric> metrics = new ArrayList<>();
        Metric metric1 = new Metric("metric1", new double[] {1.1, 1.2, 1.3, 1.4, 1.5});
        Metric metric2 = new Metric("metric2", new double[] {2.1, 2.2, 2.3, 2.4, 2.5});
        Metric metric3 = new Metric("metric3", new double[] {3.1, 3.2, 3.3, 3.4, 3.5});
        metrics.add(metric1);
        metrics.add(metric2);
        metrics.add(metric3);
        return metrics;
    }
}
