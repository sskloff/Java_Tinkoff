package edu.hw3;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Task1Test {
    @Test
    @DisplayName("Отработки шифрования методом Атбаша")
    void atbashCipherSimpleTest() {
        //given
        String str = "AbC12ZX";

        //when
        String answer = Task1.atbash(str);

        //then
        assertEquals("ZyX12AC", answer);
    }

    @Test
    @DisplayName("Отработка метода при вводе пустой строки")
    void atbashCipherEnteredEmptyStringThenReturnsEmptyString() {
        //given
        String str = "";

        //when
        String answer = Task1.atbash(str);

        //then
        assertEquals("", answer);
    }
}
