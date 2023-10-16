package edu.hw1;

import java.util.Objects;

public class Task1 {

    private Task1() {
    }

    public static final int SECONDS_IN_MINUTE = 60;

    public static int minutesToSeconds(String time) {
        int secondsFromMinutes;
        int seconds;
        if (Objects.isNull(time)) {
            return -1;
        }
        String[] numbers = time.split(":");
        try {
            secondsFromMinutes = Integer.parseInt(numbers[0]) * SECONDS_IN_MINUTE;
            seconds = Integer.parseInt(numbers[1]);
        } catch (NumberFormatException e) {
            return -1;
        }
        if (seconds < SECONDS_IN_MINUTE) {
            return secondsFromMinutes + seconds;
        } else {
            return -1;
        }
    }
}
