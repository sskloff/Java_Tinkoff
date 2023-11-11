package edu.hw3;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Task4Test {

    @Test
    @DisplayName("Отработка метода на 3")
    void whenConvertToRoman3ThenIII() {
        //when
        String answer = Task4.convertToRoman(3);

        //then
        assertEquals("III", answer);
    }

    @Test
    @DisplayName("Отработка метода на 9")
    void whenConvertToRoman9ThenIX() {
        //when
        String answer = Task4.convertToRoman(9);

        //then
        assertEquals("IX", answer);
    }

    @Test
    @DisplayName("Отработка метода на 98")
    void whenConvertToRoman98ThenXCVIII() {
        //when
        String answer = Task4.convertToRoman(98);

        //then
        assertEquals("XCVIII", answer);
    }

    @Test
    @DisplayName("Отработка метода на 55")
    void whenConvertToRoman55ThenLV() {
        //when
        String answer = Task4.convertToRoman(55);

        //then
        assertEquals("LV", answer);
    }

    @Test
    @DisplayName("Отработка метода на 614")
    void whenConvertToRoman614ThenDCXIV() {
        //when
        String answer = Task4.convertToRoman(614);

        //then
        assertEquals("DCXIV", answer);
    }

    @Test
    @DisplayName("Отработка метода на 2000")
    void whenConvertToRoman2000ThenMM() {
        //when
        String answer = Task4.convertToRoman(2000);

        //then
        assertEquals("MM", answer);
    }

    @Test
    @DisplayName("Отработка метода на 3636")
    void whenConvertToRoman3636ThenMMMDCXXXVI() {
        //when
        String answer = Task4.convertToRoman(3636);

        //then
        assertEquals("MMMDCXXXVI", answer);
    }

    @Test
    @DisplayName("Передано значение вне заданного промежутка")
    void whenConvertToRomanIllegalArgumentThenThrowException() {
        Assertions.assertThatThrownBy(() -> Task4.convertToRoman(-2))
            .isInstanceOf(IllegalArgumentException.class);
    }
}
