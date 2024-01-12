package edu.hw7.Task4;

import java.util.ArrayList;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Difference {

    public final static Logger LOGGER = LogManager.getLogger();
    public static final int[] ITERATIONS = {10_000_000, 100_000_000, 1_000_000_000};

    private Difference() {
    }

    enum Solution {
        SingleThread,
        MultiThread
    }

    public static float calculateAverageDifferenceBetweenSingleAndMultiThreadSolutions(int totalCount)
        throws InterruptedException {
        List<Float> differences = new ArrayList<>();
        LOGGER.info("Difference between Single- and Multi- ("
            + MultiThreadSolution.CORES_COUNT + ") Thread solutions\n");
        for (int iterationsCount : ITERATIONS) {
            float difference = (float) executionTime(totalCount, Solution.MultiThread)
                / executionTime(totalCount, Solution.SingleThread);
            LOGGER.info("Iterations count: " + iterationsCount);
            LOGGER.info("MultiThread solution faster by "
                + difference + " times than SingleThread solution\n");
            differences.add(difference);
        }
        float sum = 0;
        for (Float difference : differences) {
            sum += difference;
        }
        return sum / differences.size();
    }

    private static long executionTime(int totalCount, Solution solution) throws InterruptedException {
        long start;
        long finish;
        if (solution.equals(Solution.SingleThread)) {
            start = System.nanoTime();
            SingleThreadSolution.calculatePiWithSingleThread(totalCount);
            finish = System.nanoTime();
            return finish - start;
        }
        start = System.nanoTime();
        MultiThreadSolution.calculatePiWithMultiThread(totalCount);
        finish = System.nanoTime();
        return finish - start;
    }
}
