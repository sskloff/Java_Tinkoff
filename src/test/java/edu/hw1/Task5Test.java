package edu.hw1;


import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Task5Test {
    @Test
    void when11211230ThenResultTrue() {
        // given
        int number = 11211230;

        // when
        boolean result = Task5.isPalindromeDescendant(number);

        // then
        assertTrue(result);
    }

    @Test
    void when11211231ThenResultFalse() {
        // given
        int number = 11211231;

        // when
        boolean result = Task5.isPalindromeDescendant(number);

        // then
        assertFalse(result);
    }

    @Test
    void when13001120ThenResultTrue() {
        // given
        int number = 13001120;

        // when
        boolean result = Task5.isPalindromeDescendant(number);

        // then
        assertTrue(result);
    }

    @Test
    void when23336014ThenResultTrue() {
        // given
        int number = 23336014;

        // when
        boolean result = Task5.isPalindromeDescendant(number);

        // then
        assertTrue(result);
    }

    @Test
    void when533671ThenResultTrue() {
        // given
        int number = 533671;

        // when
        boolean result = Task5.isPalindromeDescendant(number);

        // then
        assertTrue(result);
    }

    @Test
    void when11ThenResultTrue() {
        // given
        int number = 11;

        // when
        boolean result = Task5.isPalindromeDescendant(number);

        // then
        assertTrue(result);
    }

    @Test
    void when123ThenResultTrue() {
        // given
        int number = 123;

        // when
        boolean result = Task5.isPalindromeDescendant(number);

        // then
        assertTrue(result);
    }

    @Test
    void when5ThenResultTrue() {
        // given
        int number = 5;

        // when
        boolean result = Task5.isPalindromeDescendant(number);

        // then
        assertTrue(result);
    }

    @Test
    void when56ThenResultTrue() {
        // given
        int number = 56;

        // when
        boolean result = Task5.isPalindromeDescendant(number);

        // then
        assertTrue(result);
    }

    @Test
    void when67ThenResultFalse() {
        // given
        int number = 67;

        // when
        boolean result = Task5.isPalindromeDescendant(number);

        // then
        assertFalse(result);
    }
}
