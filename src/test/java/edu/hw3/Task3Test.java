package edu.hw3;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Task3Test {

    @Test
    @DisplayName("Числа")
    void integerTest() {
        //given
        List<Integer> input = Arrays.asList(1, 2, 3, 1, 2, 2);
        Map<Integer, Integer> expected = Map.of(1, 2, 2, 3, 3, 1);

        //then
        Task3<Integer> task3 = new Task3<>();
        Map<Integer, Integer> answer = task3.freqDict(input);

        //when
        assertEquals(expected, answer);
    }

    @Test
    @DisplayName("Строки")
    void stringsTest() {
        //given
        List<String> input = Arrays.asList("a", "bb", "c", "a", "bb", "bb");
        Map<String, Integer> expected = Map.of("a", 2, "bb", 3, "c", 1);

        //then
        Task3<String> task3 = new Task3<>();
        Map<String, Integer> answer = task3.freqDict(input);

        //when
        assertEquals(expected, answer);
    }
}
