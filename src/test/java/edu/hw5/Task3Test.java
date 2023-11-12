package edu.hw5;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Task3Test {

    @Test
    @DisplayName("Отработка формата вида 2020-10-10")
    void parseFormat1ThenEqualsExpected() {
        //when
        Optional<LocalDate> answer = Task3.parseDate("2020-10-10");

        //then
        assertEquals(Optional.of(LocalDate.of(2020, 10, 10)), answer);
    }

    @Test
    @DisplayName("Отработка формата вида 2020-12-2")
    void parseFormat2ThenEqualsExpected() {
        //when
        Optional<LocalDate> answer = Task3.parseDate("2020-12-2");

        //then
        assertEquals(Optional.of(LocalDate.of(2020, 12, 2)), answer);
    }

    @Test
    @DisplayName("Отработка формата вида 1/3/1976")
    void parseFormat3ThenEqualsExpected() {
        //when
        Optional<LocalDate> answer = Task3.parseDate("1/3/1976");

        //then
        assertEquals(Optional.of(LocalDate.of(1976, 3, 1)), answer);
    }

    @Test
    @DisplayName("Отработка формата вида 1/3/20")
    void parseFormat4ThenEqualsExpected() {
        //when
        Optional<LocalDate> answer = Task3.parseDate("1/3/20");

        //then
        assertEquals(Optional.of(LocalDate.of(2020, 3, 1)), answer);
    }

    @Test
    @DisplayName("Отработка today")
    void parseTodayThenEqualsExpected() {
        //when
        Optional<LocalDate> answer = Task3.parseDate("today");

        //then
        assertEquals(Optional.of(LocalDate.now()), answer);
    }

    @Test
    @DisplayName("Отработка yesterday")
    void parseYesterdayThenEqualsExpected() {
        //when
        Optional<LocalDate> answer = Task3.parseDate("yesterday");

        //then
        assertEquals(Optional.of(LocalDate.now().minusDays(1)), answer);
    }

    @Test
    @DisplayName("Отработка tomorrow")
    void parseTomorrowThenEqualsExpected() {
        //when
        Optional<LocalDate> answer = Task3.parseDate("tomorrow");

        //then
        assertEquals(Optional.of(LocalDate.now().plusDays(1)), answer);
    }

    @Test
        @DisplayName("Отработка N days ago")
    void parseDaysAgoThenEqualsExpected() {
        //when
        Optional<LocalDate> answer = Task3.parseDate("2 days ago");

        //then
        assertEquals(Optional.of(LocalDate.now().minusDays(2)), answer);
    }

    @Test
    @DisplayName("Отработка некорректного формата даты")
    void whenParseIncorrectFormatThenEmpty() {
        //when
        Optional<LocalDate> answer = Task3.parseDate("lmao");

        //then
        assertEquals(Optional.empty(), answer);
    }
}
