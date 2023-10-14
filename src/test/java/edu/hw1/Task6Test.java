package edu.hw1;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Task6Test {

    @Test
    void when998ThenResultMinus1() {
        // given
        int n = 998;

        // when
        int result = Task6.countK(n);

        // then
        assertEquals(-1, result);
    }

    @Test
    void when7777ThenResultMinus1() {
        // given
        int n = 7777;

        // when
        int result = Task6.countK(n);

        // then
        assertEquals(-1, result);
    }

    @Test
    void when6621ThenResultMinus5() {
        // given
        int n = 6621;

        // when
        int result = Task6.countK(n);

        // then
        assertEquals(5, result);
    }

    @Test
    void when6554ThenResult4() {
        // given
        int n = 6554;

        // when
        int result = Task6.countK(n);

        // then
        assertEquals(4, result);
    }

    @Test
    void when1234ThenResult3() {
        // given
        int n = 1234;

        // when
        int result = Task6.countK(n);

        // then
        assertEquals(3, result);
    }

    @Test
    void when6382ThenResult3() {
        // given
        int n = 6382;

        // when
        int result = Task6.countK(n);

        // then
        assertEquals(3, result);
    }

    @Test
    void when11123ThenResultMinus1() {
        // given
        int n = 11123;

        // when
        int result = Task6.countK(n);

        // then
        assertEquals(-1, result);
    }

    @Test
    void when6174ThenResult1() {
        // given
        int n = 6174;

        // when
        int result = Task6.countK(n);

        // then
        assertEquals(1, result);
    }

    @Test
    void when8374ThenResult5() {
        // given
        int n = 8374;

        // when
        int result = Task6.countK(n);

        // then
        assertEquals(5, result);
    }
}
