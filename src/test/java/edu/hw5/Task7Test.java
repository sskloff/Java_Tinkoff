package edu.hw5;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Task7Test {

    @Test
    @DisplayName("True в методе containsMoreThen2SymbolsAnd3rdIs0")
    void when1101InContainsMoreThen2SymbolsAnd3rdIs0ThenTrue() {
        assertTrue(Task7.containsMoreThen2SymbolsAnd3rdIs0("1101"));
    }

    @Test
    @DisplayName("False в методе containsMoreThen2SymbolsAnd3rdIs0")
    void when1111InContainsMoreThen2SymbolsAnd3rdIs0ThenFalse() {
        assertFalse(Task7.containsMoreThen2SymbolsAnd3rdIs0("1111"));
    }

    @Test
    @DisplayName("True в методе startsAndEndsWithSameSymbol")
    void when1001InStartsAndEndsWithSameSymbolThenTrue() {
        assertTrue(Task7.startsAndEndsWithSameSymbol("1001"));
    }

    @Test
    @DisplayName("False в методе startsAndEndsWithSameSymbol")
    void when1110InStartsAndEndsWithSameSymbolThenFalse() {
        assertFalse(Task7.startsAndEndsWithSameSymbol("1110"));
    }

    @Test
    @DisplayName("True в методе isLengthFrom1To3")
    void when111InIsLengthFrom1To3ThenTrue() {
        assertTrue(Task7.isLengthFrom1To3("111"));
    }

    @Test
    @DisplayName("False в методе isLengthFrom1To3 1")
    void whenEmptyInputInIsLengthFrom1To3ThenFalse() {
        assertFalse(Task7.isLengthFrom1To3(""));
    }

    @Test
    @DisplayName("False в методе isLengthFrom1To3 2")
    void when1000InIsLengthFrom1To3ThenFalse() {
        assertFalse(Task7.isLengthFrom1To3("1000"));
    }
}
