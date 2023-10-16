package edu.hw1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Task2Test {
    @Test
    @DisplayName("Обычная отработка метода")
    void when4666ThenResult4() {
        // given
        int number = 4666;

        // when
        int result = Task2.countDigits(number);

        // then
        assertEquals(4, result);
    }

    @Test
    @DisplayName("Обычная отработка метода")
    void when544ThenResult3() {
        // given
        int number = 544;

        // when
        int result = Task2.countDigits(number);

        // then
        assertEquals(3, result);
    }

    @Test
    @DisplayName("Отработка с нулем")
    void when0ThenResult1() {
        // given
        int number = 0;

        // when
        int result = Task2.countDigits(number);

        // then
        assertEquals(1, result);
    }

    @Test
    @DisplayName("Отработка с отрицательным числом")
    void whenMinus25ThenResult2() {
        // given
        int number = -25;

        // when
        int result = Task2.countDigits(number);

        // then
        assertEquals(2, result);
    }
}
