package edu.hw5;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.List;

public class Task2 {

    private Task2() {
    }

    @SuppressWarnings("MagicNumber")
    public static List<LocalDate> getAllFridays13thInAYear(int year) {
        if (year <= 0) {
            return null;
        }
        List<LocalDate> answer = new ArrayList<>();
        for (int i = 1; i < 12; i++) {
            LocalDate day = LocalDate.of(year, i, 13);
            if (day.getDayOfWeek().equals(DayOfWeek.FRIDAY)) {
                answer.add(day);
            }
        }
        return answer;
    }

    @SuppressWarnings("MagicNumber")
    public static LocalDate getNextFriday13th(LocalDate date) {
        LocalDate nextDate = date.with(TemporalAdjusters.next(DayOfWeek.FRIDAY));
        while (nextDate.getDayOfMonth() != 13) {
            nextDate = nextDate.with(TemporalAdjusters.next(DayOfWeek.FRIDAY));
        }
        return nextDate;
    }
}
