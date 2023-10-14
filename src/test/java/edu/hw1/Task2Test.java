package edu.hw1;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Task2Test {
    @Test
    void when4666ThenResult4() {
        // given
        int number = 4666;

        // when
        int result = Task2.countDigits(number);

        // then
        assertEquals(4, result);
    }

    @Test
    void when544ThenResult3() {
        // given
        int number = 544;

        // when
        int result = Task2.countDigits(number);

        // then
        assertEquals(3, result);
    }

    @Test
    void when0ThenResult1() {
        // given
        int number = 0;

        // when
        int result = Task2.countDigits(number);

        // then
        assertEquals(1, result);
    }

    @Test
    void whenMinus25ThenResult2() {
        // given
        int number = -25;

        // when
        int result = Task2.countDigits(number);

        // then
        assertEquals(2, result);
    }
}
