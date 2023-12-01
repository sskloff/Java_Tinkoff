package edu.hw5;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Task8Test {

    @Test
    @DisplayName("True в методе haveMoreThen1ZeroAndLessThen2Ones")
    void when1000InHaveMoreThen1ZeroAndLessThen2OnesThenTrue() {
        assertTrue(Task8.haveMoreThen1ZeroAndLessThen2Ones("1000"));
    }

    @Test
    @DisplayName("False в методе haveMoreThen1ZeroAndLessThen2Ones")
    void when10Or0011InHaveMoreThen1ZeroAndLessThen2OnesThenFalse() {
        assertFalse(Task8.haveMoreThen1ZeroAndLessThen2Ones("10"));
        assertFalse(Task8.haveMoreThen1ZeroAndLessThen2Ones("0011"));
    }

    @Test
    @DisplayName("True в методе isLengthOdd")
    void when100InIsLengthOddThenTrue() {
        assertTrue(Task8.isLengthOdd("100"));
    }

    @Test
    @DisplayName("False в методе isLengthOdd")
    void when1111InIsLengthOddThenFalse() {
        assertFalse(Task8.isLengthOdd("1111"));
    }

    @Test
    @DisplayName("True в методе everyOddSymbolIs1")
    void when1011InEveryOddSymbolIs1ThenTrue() {
        assertTrue(Task8.everyOddSymbolIs1("1011"));
    }

    @Test
    @DisplayName("False в методе everyOddSymbolIs1")
    void when1001InEveryOddSymbolIs1ThenFalse() {
        assertFalse(Task8.everyOddSymbolIs1("1001"));
    }

    @Test
    @DisplayName("True в методе startsWith0AndOddLengthOrStartsWith1AndEvenLength")
    void when1001Or011InStartsWith0AndOddLengthOrStartsWith1AndEvenLengthThenTrue() {
        assertTrue(Task8.startsWith0AndOddLengthOrStartsWith1AndEvenLength("1001"));
        assertTrue(Task8.startsWith0AndOddLengthOrStartsWith1AndEvenLength("011"));
    }

    @Test
    @DisplayName("False в методе startsWith0AndOddLengthOrStartsWith1AndEvenLength")
    void when111Or01InStartsWith0AndOddLengthOrStartsWith1AndEvenLengthThenFalse() {
        assertFalse(Task8.startsWith0AndOddLengthOrStartsWith1AndEvenLength("111"));
        assertFalse(Task8.startsWith0AndOddLengthOrStartsWith1AndEvenLength("01"));
    }
}
