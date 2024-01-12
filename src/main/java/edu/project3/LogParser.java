package edu.project3;

import edu.project3.Models.Log;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LogParser {

    private LogParser() {
    }

    private static final Pattern NGINX_LOG_PATTERN = Pattern.compile(
        "^(.*) - (.*) \\[(.*)] \"(GET|POST|PUT|DELETE|PATCH|HEAD) (.*) (.*)\" (\\d{3}) (\\d+) \"(.*)\" \"(.*)\"$");

    private static final DateTimeFormatter DATE_TIME_FORMATTER =
        DateTimeFormatter.ofPattern("dd/MMM/yyyy:HH:mm:ss Z", Locale.US);

    @SuppressWarnings("MagicNumber")
    public static Log parseLog(String log) {
        Matcher matcher = NGINX_LOG_PATTERN.matcher(log);
        if (!matcher.matches()) {
            throw new IllegalArgumentException("Incorrect log");
        }
        return new Log(
            matcher.group(1),
            matcher.group(2),
            OffsetDateTime.parse(matcher.group(3), DATE_TIME_FORMATTER),
            matcher.group(4),
            matcher.group(5),
            matcher.group(6),
            Integer.parseInt(matcher.group(7)),
            Long.parseLong(matcher.group(8)),
            matcher.group(9),
            matcher.group(10)
        );
    }
}
