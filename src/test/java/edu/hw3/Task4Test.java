package edu.hw3;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Task4Test {

    @Test
    @DisplayName("Отработка метода на 3")
    void testOn3() {
        //when
        String answer = Task4.convertToRoman(3);

        //then
        assertEquals("III", answer);
    }

    @Test
    @DisplayName("Отработка метода на 9")
    void testOn9() {
        //when
        String answer = Task4.convertToRoman(9);

        //then
        assertEquals("IX", answer);
    }

    @Test
    @DisplayName("Отработка метода на 98")
    void testOn98() {
        //when
        String answer = Task4.convertToRoman(98);

        //then
        assertEquals("XCVIII", answer);
    }

    @Test
    @DisplayName("Отработка метода на 55")
    void testOn55() {
        //when
        String answer = Task4.convertToRoman(55);

        //then
        assertEquals("LV", answer);
    }

    @Test
    @DisplayName("Отработка метода на 614")
    void testOn614() {
        //when
        String answer = Task4.convertToRoman(614);

        //then
        assertEquals("DCXIV", answer);
    }

    @Test
    @DisplayName("Отработка метода на 2000")
    void testOn2000() {
        //when
        String answer = Task4.convertToRoman(2000);

        //then
        assertEquals("MM", answer);
    }

    @Test
    @DisplayName("Отработка метода на 3636")
    void testOn3636() {
        //when
        String answer = Task4.convertToRoman(3636);

        //then
        assertEquals("MMMDCXXXVI", answer);
    }

    @Test
    @DisplayName("Значение вне заданного промежутка")
    void illegalArgumentTest() {
        Assertions.assertThatThrownBy(() -> Task4.convertToRoman(-2))
            .isInstanceOf(IllegalArgumentException.class);
    }
}
