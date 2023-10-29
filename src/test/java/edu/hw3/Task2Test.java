package edu.hw3;

import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class Task2Test {
    @Test
    @DisplayName("Введена корректная строка")
    void enteredCorrectString() {
        //given
        String input = "(()())()((()))()(())";
        List<String> expected = Arrays.asList("(()())", "()", "((()))", "()", "(())");

        //when
        List<String> answer = Task2.clusterize(input);

        //then
        assertEquals(expected, answer);
    }

    @Test
    @DisplayName("Введена некорректная строка")
    void enteredIncorrectString() {
        //given
        String input = ")(()";

        //when
        List<String> answer = Task2.clusterize(input);

        //then
        assertNull(answer);
    }

    @Test
    @DisplayName("Введена некорректная строка 2")
    void enteredIncorrectString2() {
        //given
        String input = "(";

        //when
        List<String> answer = Task2.clusterize(input);

        //then
        assertNull(answer);
    }

    @Test
    @DisplayName("Введена некорректная строка 3")
    void enteredIncorrectString3() {
        //given
        String input = "(())())";

        //when
        List<String> answer = Task2.clusterize(input);

        //then
        assertNull(answer);
    }

    @Test
    @DisplayName("Лишние символы")
    void extraChars() {
        //given
        String input = "a((aa))(a)a";

        //when
        List<String> answer = Task2.clusterize(input);

        //then
        assertNull(answer);
    }

    @Test
    @DisplayName("Пустая строка")
    void emptyString() {
        //given
        String input = "";

        //when
        List<String> answer = Task2.clusterize(input);

        //then
        assertNull(answer);
    }
}
