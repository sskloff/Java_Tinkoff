package edu.hw1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Task4Test {

    @Test
    @DisplayName("Обычная отработка метода")
    void fixString1() {
        // given
        String s = "123456";

        // when
        String result = Task4.fixString(s);

        // then
        assertEquals("214365", result);
    }

    @Test
    @DisplayName("Обычная отработка метода")
    void fixString2() {
        // given
        String s = "badce";

        // when
        String result = Task4.fixString(s);

        // then
        assertEquals("abcde", result);
    }

    @Test
    @DisplayName("Обычная отработка метода")
    void fixString3() {
        // given
        String s = "hTsii  s aimex dpus rtni.g";

        // when
        String result = Task4.fixString(s);

        // then
        assertEquals("This is a mixed up string.", result);
    }

    @Test
    @DisplayName("Введена пустая строка")
    void fixEmptyString() {
        // given
        String s = "";

        // when
        String result = Task4.fixString(s);

        // then
        assertEquals("", result);
    }
}
