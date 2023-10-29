package edu.hw3;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Task1Test {
    @Test
    @DisplayName("Тестирование отработки метода")
    void test() {
        //given
        String str = "AbC12ZX";

        //when
        String answer = Task1.atbash(str);

        //then
        assertEquals("ZyX12AC", answer);
    }

    @Test
    @DisplayName("Пустая строка")
    void emptyString() {
        //given
        String str = "";

        //when
        String answer = Task1.atbash(str);

        //then
        assertEquals("", answer);
    }
}
