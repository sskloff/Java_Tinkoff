package edu.hw5;

import net.bytebuddy.asm.Advice;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class Task2Test {

    @Test
    @DisplayName("Все пятницы 13 в заданном году")
    void when1925YearThenExpectedDates() {
        //given
        List<LocalDate> expectedDates = Arrays.asList(
            LocalDate.of(1925, 2, 13),
            LocalDate.of(1925, 3, 13),
            LocalDate.of(1925, 11, 13)
        );

        //when
        List<LocalDate> answer = Task2.getAllFridays13thInAYear(1925);

        //then
        assertEquals(expectedDates, answer);
    }

    @Test
    @DisplayName("Поиск следующей пятницы 13")
    void whenDecember1991ThenMarch1992() {
        //when
        LocalDate answer = Task2.getNextFriday13th(LocalDate.of(1991, 12, 13));

        //then
        assertEquals(LocalDate.of(1992, 3, 13), answer);
    }

    @Test
    @DisplayName("Некорректный ввод")
    void whenIncorrectInputThenNull() {
        assertNull(Task2.getAllFridays13thInAYear(-1));
    }
}
