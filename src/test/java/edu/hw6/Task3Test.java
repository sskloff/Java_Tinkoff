package edu.hw6;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import static edu.hw6.AbstractFilter.globMatches;
import static edu.hw6.AbstractFilter.isDirectory;
import static edu.hw6.AbstractFilter.isExecutable;
import static edu.hw6.AbstractFilter.largerThan;
import static edu.hw6.AbstractFilter.readable;
import static edu.hw6.AbstractFilter.regexContains;
import static edu.hw6.AbstractFilter.writable;
import static java.nio.file.Files.createDirectory;
import static java.nio.file.Files.createFile;
import static java.nio.file.Files.deleteIfExists;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Task3Test {

    @Test
    @DisplayName("Проверка множественной фильтрации")
    void whenReadableRegexGlobWritableFilterThenOnlyTxtInArray() throws IOException {
        //given
        Path directoryPath = Path.of("src/test/java/edu/hw6/test/");
        Path txtFilePath = Paths.get(directoryPath.toString(), "test.txt");
        Path txt123FilePath = Paths.get(directoryPath.toString(), "test123.txt");
        Path png123FilePath = Paths.get(directoryPath.toString(), "test123.png");
        createDirectory(directoryPath);
        createFile(txtFilePath);
        createFile(txt123FilePath);
        createFile(png123FilePath);

        //when
        DirectoryStream.Filter<Path> filter = readable()
            .and(regexContains("123"))
            .and(globMatches(".txt"))
            .and(writable());
        List<String> answer = new ArrayList<>();
        try (DirectoryStream<Path> entries = Files.newDirectoryStream(directoryPath, filter)) {
            entries.forEach(path -> answer.add(path.getFileName().toString()));
        }

        //then
        deleteIfExists(txtFilePath);
        deleteIfExists(txt123FilePath);
        deleteIfExists(png123FilePath);
        deleteIfExists(directoryPath);
        assertEquals(1, answer.size());
    }

    @Test
    @DisplayName("Проверка largerThan фильтрации")
    void onlyNotEmptyFileLargerThan0() throws IOException {
        Path directoryPath = Path.of("src/test/java/edu/hw6/test/");
        Path emptyFilePath = Paths.get(directoryPath.toString(), "empty.txt");
        Path notEmptyFilePath = Paths.get(directoryPath.toString(), "notEmpty.txt");
        createDirectory(directoryPath);
        createFile(emptyFilePath);
        createFile(notEmptyFilePath);
        try (FileWriter writer = new FileWriter(notEmptyFilePath.toString(), true)) {
            writer.append("Hello, World!\n");
        }

        //when
        long size = 0L;
        DirectoryStream.Filter<Path> filter = largerThan(size);
        List<String> answer = new ArrayList<>();
        try (DirectoryStream<Path> entries = Files.newDirectoryStream(directoryPath, filter)) {
            entries.forEach(path -> answer.add(path.getFileName().toString()));
        }

        //then
        assertEquals(1, answer.size());
        deleteIfExists(emptyFilePath);
        deleteIfExists(notEmptyFilePath);
        deleteIfExists(directoryPath);
    }

    @Test
    @DisplayName("Проверка isDirectory фильтрации")
    void whenIsDirectoryThanArraySizeIs1() throws IOException {
        //given
        Path directoryPath = Path.of("src/test/java/edu/hw6/test/");
        Path txtFilePath = Paths.get(directoryPath.toString(), "test.txt");
        Path testDirectoryPath = Path.of("src/test/java/edu/hw6/test/directory");
        createDirectory(directoryPath);
        createDirectory(testDirectoryPath);
        createFile(txtFilePath);

        //when
        DirectoryStream.Filter<Path> filter = isDirectory();
        List<String> answer = new ArrayList<>();
        try (DirectoryStream<Path> entries = Files.newDirectoryStream(directoryPath, filter)) {
            entries.forEach(path -> answer.add(path.getFileName().toString()));
        }

        //then
        deleteIfExists(txtFilePath);
        deleteIfExists(testDirectoryPath);
        deleteIfExists(directoryPath);
        assertEquals(1, answer.size());
    }

    @Test
    @DisplayName("Проверка isExecutable фильтрации")
    void whenIsExecutableThanArraySizeIs1() throws IOException {
        //given
        Path directoryPath = Path.of("src/test/java/edu/hw6/test/");
        Path txtFilePath = Paths.get(directoryPath.toString(), "test.txt");
        Path testDirectoryPath = Path.of("src/test/java/edu/hw6/test/directory");
        createDirectory(directoryPath);
        createDirectory(testDirectoryPath);
        createFile(txtFilePath);

        //when
        DirectoryStream.Filter<Path> filter = isExecutable();
        List<String> answer = new ArrayList<>();
        try (DirectoryStream<Path> entries = Files.newDirectoryStream(directoryPath, filter)) {
            entries.forEach(path -> answer.add(path.getFileName().toString()));
        }

        //then
        deleteIfExists(txtFilePath);
        deleteIfExists(testDirectoryPath);
        deleteIfExists(directoryPath);
        assertEquals(1, answer.size()); //Директория вроде как считается executable :^)
    }
}
