package edu.hw7;

import edu.hw7.Task4.Difference;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Task4Test {

    @Test
    @DisplayName("Тест на то что MultiThread быстрее однопотока")
    void multiThreadSolutionMoreThanZeroTimesFasterThanSingleThread() throws InterruptedException {
        //when
        float difference =
            Difference.calculateAverageDifferenceBetweenSingleAndMultiThreadSolutions(1000);

        //then
        assertTrue(difference > 0);
    }
}
