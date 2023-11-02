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
    void clusterizeWhenEnteredCorrectString() {
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
    void clusterizeWhenEnteredIncorrectStringThenNull() {
        //given
        String input = ")(()";

        //when
        List<String> answer = Task2.clusterize(input);

        //then
        assertNull(answer);
    }

    @Test
    @DisplayName("Введена некорректная строка 2")
    void clusterizeWhenEnteredIncorrectStringThenNull2() {
        //given
        String input = "(";

        //when
        List<String> answer = Task2.clusterize(input);

        //then
        assertNull(answer);
    }

    @Test
    @DisplayName("Введена некорректная строка 3")
    void clusterizeWhenEnteredIncorrectStringThenNull3() {
        //given
        String input = "(())())";

        //when
        List<String> answer = Task2.clusterize(input);

        //then
        assertNull(answer);
    }

    @Test
    @DisplayName("Введена строка, содержащая лишние символы")
    void clusterizeWhenEnteredStringWithExtraCharsThenNull() {
        //given
        String input = "a((aa))(a)a";

        //when
        List<String> answer = Task2.clusterize(input);

        //then
        assertNull(answer);
    }

    @Test
    @DisplayName("Введена пустая строка")
    void clusterizeWhenEnteredEmptyStringThenNull() {
        //given
        String input = "";

        //when
        List<String> answer = Task2.clusterize(input);

        //then
        assertNull(answer);
    }
}
