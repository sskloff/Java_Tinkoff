package edu.hw5;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Task1Test {

    @Test
    @DisplayName("Отработка с корректным набором")
    void whenCorrectListThen3h40min() {
        //given
        List<String> dates = Arrays.asList(
            "2022-03-12, 20:20 - 2022-03-12, 23:50",
            "2022-04-01, 21:30 - 2022-04-02, 01:20"
        );

        //when
        String answer = Task1.getSessionAverageTime(dates);

        //then
        assertEquals("3ч 40м", answer);
    }

    @Test
    @DisplayName("Пустой список")
    void whenEmptyListThen0h0min() {
        //given
        List<String> dates = new ArrayList<>();

        //when
        String answer = Task1.getSessionAverageTime(dates);

        //then
        assertEquals("0ч 0м", answer);
    }
}
