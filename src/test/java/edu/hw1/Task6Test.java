package edu.hw1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Task6Test {

    @Test
    @DisplayName("Введено трехзначное число")
    void when998ThenResultMinus1() {
        // given
        int n = 998;

        // when
        int result = Task6.countK(n);

        // then
        assertEquals(-1, result);
    }

    @Test
    @DisplayName("Введено четырехзначное число, но не больше 1000")
    void when1000ThenResultMinus1() {
        // given
        int n = 1000;

        // when
        int result = Task6.countK(n);

        // then
        assertEquals(-1, result);
    }

    @Test
    @DisplayName("Введено четырехзначное число из одинаковых цифр")
    void when7777ThenResultMinus1() {
        // given
        int n = 7777;

        // when
        int result = Task6.countK(n);

        // then
        assertEquals(-1, result);
    }

    @Test
    @DisplayName("Обычная отработка метода")
    void when6621ThenResultMinus5() {
        // given
        int n = 6621;

        // when
        int result = Task6.countK(n);

        // then
        assertEquals(5, result);
    }

    @Test
    @DisplayName("Обычная отработка метода")
    void when6554ThenResult4() {
        // given
        int n = 6554;

        // when
        int result = Task6.countK(n);

        // then
        assertEquals(4, result);
    }

    @Test
    @DisplayName("Обычная отработка метода")
    void when1234ThenResult3() {
        // given
        int n = 1234;

        // when
        int result = Task6.countK(n);

        // then
        assertEquals(3, result);
    }

    @Test
    @DisplayName("Обычная отработка метода")
    void when6382ThenResult3() {
        // given
        int n = 6382;

        // when
        int result = Task6.countK(n);

        // then
        assertEquals(3, result);
    }

    @Test
    @DisplayName("Введено не четырехзначное число")
    void when11123ThenResultMinus1() {
        // given
        int n = 11123;

        // when
        int result = Task6.countK(n);

        // then
        assertEquals(-1, result);
    }

    @Test
    @DisplayName("Отработка метода с числом Капрекара")
    void when6174ThenResult1() {
        // given
        int n = 6174;

        // when
        int result = Task6.countK(n);

        // then
        assertEquals(1, result);
    }

    @Test
    @DisplayName("Обычная отработка метода")
    void when8374ThenResult5() {
        // given
        int n = 8374;

        // when
        int result = Task6.countK(n);

        // then
        assertEquals(5, result);
    }
}
