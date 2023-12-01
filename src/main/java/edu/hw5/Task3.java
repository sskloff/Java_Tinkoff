package edu.hw5;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

public class Task3 {

    private Task3() {
    }

    private static final List<DateTimeFormatter> FORMATTERS = List.of(
        DateTimeFormatter.ofPattern("yyyy-MM-dd"),
        DateTimeFormatter.ofPattern("yyyy-M-d"),
        DateTimeFormatter.ofPattern("yyyy-dd-MM"),
        DateTimeFormatter.ofPattern("yyyy-d-M"),
        DateTimeFormatter.ofPattern("d/M/yyyy"),
        DateTimeFormatter.ofPattern("dd/MM/yyyy"),
        DateTimeFormatter.ofPattern("d/M/yy"),
        DateTimeFormatter.ofPattern("dd/MM/yy")
    );

    @SuppressWarnings("ReturnCount")
    public static Optional<LocalDate> parseDate(String string) {
        Pattern pattern1 = Pattern.compile("^(tomorrow|today|yesterday|\\d+ day(s)? ago)$");
        if (pattern1.matcher(string).matches()) {
            if (string.equals("tomorrow")) {
                return Optional.of(LocalDate.now().plusDays(1));
            }
            if (string.equals("today")) {
                return Optional.of(LocalDate.now());
            }
            if (string.equals("yesterday")) {
                return Optional.of(LocalDate.now().minusDays(1));
            } else {
                String[] parts = string.split(" ");
                return Optional.of(LocalDate.now().minusDays(Integer.parseInt(parts[0])));
            }
        }
        for (DateTimeFormatter formatter : FORMATTERS) {
            try {
                return Optional.of(LocalDate.parse(string, formatter));
            } catch (Exception ignored) {
            }
        }
        return Optional.empty();
    }
}
