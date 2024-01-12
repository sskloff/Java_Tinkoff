package edu.project3;

import edu.project3.Models.Log;
import edu.project3.Models.ReportFileFormat;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class LogAnalyzer {
    private String path = null;
    private OffsetDateTime from = OffsetDateTime.MIN;
    private OffsetDateTime to = OffsetDateTime.MAX;
    private ReportFileFormat reportFormat = ReportFileFormat.MARKDOWN;
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    @SuppressWarnings("MultipleStringLiterals")
    private LogAnalyzer(String input) {
        String date;
        if (input.contains("--path")) {
            path = parseCommand("--path", input);
        }
        if (input.contains("--from")) {
            date = parseCommand("--from", input);
            from = LocalDate.parse(date, DATE_FORMATTER).atStartOfDay(ZoneOffset.UTC).toOffsetDateTime();
        }
        if (input.contains("--to")) {
            date = parseCommand("--to", input);
            to = LocalDate.parse(date, DATE_FORMATTER).atStartOfDay(ZoneOffset.UTC).toOffsetDateTime();
        }
        if (input.contains("--format adoc")) {
            reportFormat = ReportFileFormat.ADOC;
        }
    }

    public static void run(String input) {
        LogAnalyzer logAnalyzer = new LogAnalyzer(input);
        List<String> logs;
        try {
            logs = LogLoader.getLogFromURL(logAnalyzer.path);
        } catch (RuntimeException e) {
            logs = LogLoader.getLogFromFile(Path.of(logAnalyzer.path));
        }

        List<Log> parsedLogs = new ArrayList<>();
        for (String log : logs) {
            parsedLogs.add(LogParser.parseLog(log));
        }
        Report report = Report.createLogReport(logAnalyzer, parsedLogs);
        ReportGenerator.generate(report);
    }

    public static String parseCommand(String command, String input) {
        int start = input.indexOf(command) + command.length();
        return input.substring(
            start,
            start + input.substring(start).indexOf(" ")
        ).trim();
    }

    public static LogAnalyzer fabricLogAnalyzerCreator(
        String path, OffsetDateTime from,
        OffsetDateTime to, ReportFileFormat format
    ) {
        LogAnalyzer logAnalyzer = new LogAnalyzer("");
        logAnalyzer.path = path;
        logAnalyzer.from = from;
        logAnalyzer.to = to;
        logAnalyzer.reportFormat = format;
        return logAnalyzer;
    }

    public OffsetDateTime getFrom() {
        return from;
    }

    public OffsetDateTime getTo() {
        return to;
    }

    public ReportFileFormat getReportFormat() {
        return reportFormat;
    }
}
