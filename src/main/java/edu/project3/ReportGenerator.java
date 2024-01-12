package edu.project3;

import edu.project3.Models.ReportFileFormat;
import edu.project3.Models.ResponseCodes;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

public class ReportGenerator {

    private ReportGenerator() {
    }

    private static final DateTimeFormatter DATE_TIME_FORMATTER =
        DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public static void generate(Report report) {
        Path path = Paths.get("src/main/java/edu/project3/").toAbsolutePath();
        if (report.getFormat().equals(ReportFileFormat.ADOC)) {
            path = path.resolve("report.adoc");
        } else {
            path = path.resolve("report.md");
        }
        try (PrintWriter writer = new PrintWriter(new FileWriter(path.toFile()))) {
            prettyPrintInfoAboutLog(writer, report);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @SuppressWarnings("MultipleStringLiterals")
    private static void prettyPrintInfoAboutLog(PrintWriter writer, Report report) {
        String formattedString;
        writer.println("#### Общая информация\n");
        writer.println("|        Метрика        |    Значение    |");
        writer.println("|:---------------------:|:--------------:|");

        formattedString = String.format("%-14b", report.getFrom().isEqual(OffsetDateTime.MIN)
            ? "-" : report.getFrom().format(DATE_TIME_FORMATTER));
        writer.println("|    Начальная дата     | " + formattedString + " |");

        formattedString = String.format("%-14b", report.getTo().isEqual(OffsetDateTime.MAX)
            ? "-" : report.getTo().format(DATE_TIME_FORMATTER));
        writer.println("|     Конечная дата     | " + formattedString + " |");

        formattedString = String.format("%-14d", report.getRequestsCount());
        writer.println("|  Количество запросов  | " + formattedString + " |");

        formattedString = String.format("%-14d", report.getAverageResponseSize());
        writer.println("| Средний размер ответа | " + formattedString + " |");
        writer.println();

        writer.println("#### Запрашиваемые ресурсы\n");
        writer.println("|      Ресурс       |      Количество      |");
        writer.println("|:-----------------:|:--------------------:|");
        for (Map.Entry<String, Integer> entry : report.getResources().entrySet()) {
            writer.println("| " + String.format("%-17s", entry.getKey()) + " | "
                + String.format("%-20d", entry.getValue()) + " |");
        }
        writer.println();

        writer.println("#### Коды ответа\n");
        writer.println("| Код |               Имя               |   Количество   |");
        writer.println("|:---:|:-------------------------------:|:--------------:|");
        for (Map.Entry<Integer, Integer> entry : report.getResponseCodes().entrySet()) {
            writer.println("| " + entry.getKey() + " | "
                + String.format("%-31s", ResponseCodes.RESPONSE_CODES.get(entry.getKey()))
                + " | " + String.format("%-14s", entry.getValue()) + " |");
        }
        writer.println();

        writer.println("#### Запрашиваемые методы\n");
        writer.println("|  Метод  |   Количество   |");
        writer.println("|:-------:|:--------------:|");
        for (Map.Entry<String, Integer> entry : report.getMethods().entrySet()) {
            writer.println("| " + String.format("%-7s", entry.getKey())
                + " | " + String.format("%-14s", entry.getValue()) + " |");
        }
        writer.println();

        writer.println("#### Количество логов по удаленным адресам\n");
        writer.println("|      Адрес      |   Количество   |");
        writer.println("|:---------------:|:--------------:|");
        for (Map.Entry<String, Integer> entry : report.getRemoteAddresses().entrySet()) {
            writer.println("| " + String.format("%-15s", entry.getKey())
                + " | " + String.format("%-14s", entry.getValue()) + " |");
        }
        writer.println();
    }
}
