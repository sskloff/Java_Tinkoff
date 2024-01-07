package edu.project3;

import edu.project3.Models.Log;
import edu.project3.Models.ReportFileFormat;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import static java.nio.file.Files.createDirectory;
import static java.nio.file.Files.createFile;
import static java.nio.file.Files.deleteIfExists;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class Project3Test {

    private static final Path DIRECTORY_PATH = Path.of("src/test/java/edu/project3/test");
    private static final Path FILE_PATH = Paths.get(DIRECTORY_PATH.toString(), "test.txt");
    private static final List<String> LOGS = List.of(
        "72.32.152.84 - - [03/Jun/2015:06:06:24 +0000] \"GET /downloads/product_1 HTTP/1.1\" 304 1000 \"-\" \"Debian APT-HTTP/1.3 (0.9.7.9)\"",
        "119.252.76.162 - - [03/Jun/2015:06:06:19 +0000] \"GET /downloads/product_2 HTTP/1.1\" 404 2000 \"-\" \"Debian APT-HTTP/1.3 (1.0.1ubuntu2)\"",
        "54.208.16.21 - - [03/Jun/2015:06:06:41 +0000] \"POST /downloads/product_1 HTTP/1.1\" 200 3000 \"-\" \"Wget/1.13.4 (linux-gnu)\"",
        "54.208.16.21 - - [03/Jun/2020:06:06:41 +0000] \"DELETE /downloads/product_1 HTTP/1.1\" 200 3000 \"-\" \"Wget/1.13.4 (linux-gnu)\""
    );
    private static final String URL = "https://raw.githubusercontent.com/elastic/examples/master/Common%20Data%20Formats/nginx_logs/nginx_logs";

    @BeforeEach
    void createFiles() throws IOException {
        createDirectory(DIRECTORY_PATH);
        createFile(FILE_PATH);
    }

    @AfterEach
    void deleteFiles() throws IOException {
        deleteIfExists(FILE_PATH);
        deleteIfExists(DIRECTORY_PATH);
    }

    @Test
    @DisplayName("Загрузка логов из файла")
    void loaderFromFileTest() throws IOException {
        //given
        Files.write(FILE_PATH, LOGS);

        //when
        List<String> logs = LogLoader.getLogFromFile(FILE_PATH);

        //then
        assertEquals(LOGS, logs);
    }

    @Test
    @DisplayName("Загрузка логов по ссылке")
    void loaderFromUTLTest() throws IOException {
        //when
        List<String> logs = LogLoader.getLogFromURL(URL);

        //then
        assertEquals(51462, logs.size());
    }

    @Test
    @DisplayName("Парсинг лога")
    void parsingLogTest(){
        //given
        String log = "72.32.152.84 - - [03/Jun/2015:06:06:24 +0000] \"GET /downloads/product_1 HTTP/1.1\" 304 0 \"-\" \"Debian APT-HTTP/1.3 (0.9.7.9)\"";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MMM/yyyy:HH:mm:ss Z", Locale.US);

        //when
        Log parsedLog = LogParser.parseLog(log);

        //then
        assertEquals("72.32.152.84", parsedLog.remoteAddress());
        assertEquals(OffsetDateTime.parse("03/Jun/2015:06:06:24 +0000", formatter),
            parsedLog.timeLocal());
        assertEquals("GET", parsedLog.method());
        assertEquals(304, parsedLog.status());
        assertEquals("Debian APT-HTTP/1.3 (0.9.7.9)", parsedLog.httpUserAgent());
    }

    @Test
    @DisplayName("Подготовка всей статистики к созданию отчета")
    void reportPreparingTest() throws IOException {
        //given
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        Files.write(FILE_PATH, LOGS);
        List<Log> parsedLogs = new ArrayList<>();

        LogAnalyzer logAnalyzer = LogAnalyzer.fabricLogAnalyzerCreator(
            FILE_PATH.toString(),
            LocalDate.parse("2014-01-01", formatter).atStartOfDay(ZoneOffset.UTC).toOffsetDateTime(),
            LocalDate.parse("2016-01-01", formatter).atStartOfDay(ZoneOffset.UTC).toOffsetDateTime(),
            ReportFileFormat.MARKDOWN
        );

        //when
        List<String> logs = LogLoader.getLogFromFile(FILE_PATH);
        for (String log : logs) {
            parsedLogs.add(LogParser.parseLog(log));
        }
        Report report = Report.createLogReport(logAnalyzer, parsedLogs);

        //then
        assertEquals(1, report.getResponseCodes().get(200));
        assertEquals(1, report.getResponseCodes().get(404));
        assertEquals(2, report.getMethods().get("GET"));
        assertNull(report.getMethods().get("DELETE")); // лог не вошел в диапазон по датам
        assertEquals(1, report.getRemoteAddresses().get("54.208.16.21"));
        assertEquals(2000, report.getAverageResponseSize());
    }
}
