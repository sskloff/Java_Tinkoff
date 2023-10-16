package edu.hw1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Task5Test {
    @Test
    @DisplayName("Обычная положительная отработка метода")
    void when11211230ThenResultTrue() {
        // given
        int number = 11211230;

        // when
        boolean result = Task5.isPalindromeDescendant(number);

        // then
        assertTrue(result);
    }

    @Test
    @DisplayName("Обычная отрицательная отработка метода")
    void when11211231ThenResultFalse() {
        // given
        int number = 11211231;

        // when
        boolean result = Task5.isPalindromeDescendant(number);

        // then
        assertFalse(result);
    }

    @Test
    @DisplayName("Обычная положительная отработка метода")
    void when13001120ThenResultTrue() {
        // given
        int number = 13001120;

        // when
        boolean result = Task5.isPalindromeDescendant(number);

        // then
        assertTrue(result);
    }

    @Test
    @DisplayName("Обычная положительная отработка метода")
    void when23336014ThenResultTrue() {
        // given
        int number = 23336014;

        // when
        boolean result = Task5.isPalindromeDescendant(number);

        // then
        assertTrue(result);
    }

    @Test
    @DisplayName("Обычная положительная отработка метода")
    void when533671ThenResultTrue() {
        // given
        int number = 533671;

        // when
        boolean result = Task5.isPalindromeDescendant(number);

        // then
        assertTrue(result);
    }

    @Test
    @DisplayName("Отработка двузначного числа, являющегося палиндромом")
    void when11ThenResultTrue() {
        // given
        int number = 11;

        // when
        boolean result = Task5.isPalindromeDescendant(number);

        // then
        assertTrue(result);
    }

    @Test
    @DisplayName("Отработка метода на числе с нечетным количеством цифр")
    void when123ThenResultTrue() {
        // given
        int number = 123;

        // when
        boolean result = Task5.isPalindromeDescendant(number);

        // then
        assertTrue(result);
    }

    @Test
    @DisplayName("Введено однозначное число, оно тоже палиндром")
    void when5ThenResultTrue() {
        // given
        int number = 5;

        // when
        boolean result = Task5.isPalindromeDescendant(number);

        // then
        assertTrue(result);
    }

    @Test
    @DisplayName("Отработка двузначного числа, сумма цифр 11 -> тоже палиндром")
    void when56ThenResultTrue() {
        // given
        int number = 56;

        // when
        boolean result = Task5.isPalindromeDescendant(number);

        // then
        assertTrue(result);
    }

    @Test
    @DisplayName("Сумма цифр не является палиндромом")
    void when67ThenResultFalse() {
        // given
        int number = 67;

        // when
        boolean result = Task5.isPalindromeDescendant(number);

        // then
        assertFalse(result);
    }
}
