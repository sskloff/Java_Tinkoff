package edu.hw5;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Task6Test {

    @Test
    @DisplayName("Пустая подстрока")
    void whenEmptySubstringThenTrue() {
        assertTrue(Task6.isSubstring("", "abc"));
    }

    @Test
    @DisplayName("Является подстрокой")
    void whenSubABCAndStringDDABCAAThenTrue() {
        assertTrue(Task6.isSubstring("ABC", "DDABCAA"));
    }

    @Test
    @DisplayName("Не является подстрокой")
    void whenSubEXAndStringDDABCAAThenFalse() {
        assertFalse(Task6.isSubstring("EX", "DDABCAA"));
    }
}
