package edu.hw1;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Task7Test {

    @Test
    void whenRotateRight8And1ThenResult4() {
        // given
        int n = 8;
        int shift = 1;

        // when
        int result = Task7.rotateRight(n, shift);

        // then
        assertEquals(4, result);
    }

    @Test
    void whenRotateLeft16And1ThenResult1() {
        // given
        int n = 16;
        int shift = 1;

        // when
        int result = Task7.rotateLeft(n, shift);

        // then
        assertEquals(1, result);
    }

    @Test
    void whenRotateLeft17And2ThenResult6() {
        // given
        int n = 17;
        int shift = 2;

        // when
        int result = Task7.rotateLeft(n, shift);

        // then
        assertEquals(6, result);
    }

    @Test
    void whenRotateLeft1337228And3ThenResult424138() {
        // given
        int n = 1337228;
        int shift = 4;

        // when
        int result = Task7.rotateLeft(n, shift);

        // then
        assertEquals(424138, result);
    }

    @Test
    void whenRotateRight1337228And3ThenResult1656440() {
        // given
        int n = 1337228;
        int shift = 4;

        // when
        int result = Task7.rotateRight(n, shift);

        // then
        assertEquals(1656440, result);
    }
}
