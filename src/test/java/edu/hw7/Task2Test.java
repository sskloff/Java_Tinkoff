package edu.hw7;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Task2Test {

    @Test
    @DisplayName("Вычисление факториала")
    void whenGiven7ThenResult5040() {
        //given
        int expected = 5040;

        //when
        int result = Task2.calculateFactorialWithConcurrency(7);

        //then
        assertEquals(expected, result);
    }
}
