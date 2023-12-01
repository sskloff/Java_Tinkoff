package edu.hw5;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Task4Test {

    @Test
    @DisplayName("Пароль не содержит нужного символа")
    void whenABC23ThenFalse() {
        assertFalse(Task4.isPasswordValid("ABC23"));
    }

    @Test
    @DisplayName("Пароль содержит нужный символ 1")
    void whenPasswordContainsSymbolThenTrue1() {
        assertTrue(Task4.isPasswordValid("ABC23!"));
    }

    @Test
    @DisplayName("Пароль содержит нужный символ 2")
    void whenPasswordContainsSymbolThenTrue2() {
        assertTrue(Task4.isPasswordValid("ABC|^23"));
    }
}
