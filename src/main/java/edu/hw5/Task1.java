package edu.hw5;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class Task1 {

    private Task1() {
    }

    @SuppressWarnings("MagicNumber")
    public static String getSessionAverageTime(List<String> timeRanges) {
        if (timeRanges.isEmpty()) {
            return "0ч 0м";
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd, HH:mm");
        Duration totalDuration = Duration.ZERO;
        for (String timeRange : timeRanges) {
            String[] session = timeRange.split(" - ");
            LocalDateTime startOfSession = LocalDateTime.parse(session[0], formatter);
            LocalDateTime endOfSession = LocalDateTime.parse(session[1], formatter);
            totalDuration = totalDuration.plus(Duration.between(startOfSession, endOfSession));
        }
        long hours = totalDuration.toHours() / timeRanges.size();
        long minutes = (totalDuration.toMinutes() / timeRanges.size()) % 60;
        return String.format("%dч %dм", hours, minutes);
    }
}
