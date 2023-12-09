package edu.hw9;

import edu.hw9.Task2.ParallelTreeHandler;
import org.assertj.core.util.Files;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import static java.nio.file.Files.createDirectory;
import static java.nio.file.Files.createFile;
import static java.nio.file.Files.deleteIfExists;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Task2Test {

    private static final Path DIRECTORY_PATH = Path.of("src/test/java/edu/hw9/test/");
    private static final Path DIRECTORY_PATH_1 = Path.of("src/test/java/edu/hw9/test/dir1");
    private static final Path DIRECTORY_PATH_2 = Path.of("src/test/java/edu/hw9/test/dir2");
    private static final List<Path> FILES = new ArrayList<>();

    @BeforeAll
    static void createFiles() throws IOException {
        createDirectory(DIRECTORY_PATH);
        createDirectory(DIRECTORY_PATH_1);
        for (int i = 0; i < 15; i++) {
            Path filePath = Paths.get(DIRECTORY_PATH_1.toString(), "testFile" + i + ".txt");
            FILES.add(filePath);
            createFile(filePath);
        }
        Path filePath = Paths.get(DIRECTORY_PATH_1.toString(), "testFile" + 15 + ".exe");
        FILES.add(filePath);
        createFile(filePath);

        createDirectory(DIRECTORY_PATH_2);
    }

    @AfterAll
    static void deleteFiles() throws IOException {
        for (Path file : FILES) {
            deleteIfExists(file);
        }
        deleteIfExists(DIRECTORY_PATH_1);
        deleteIfExists(DIRECTORY_PATH_2);
        deleteIfExists(DIRECTORY_PATH);
    }

    @Test
    @DisplayName("Тест поиска директории с более чем 10 файлов")
    void whenDirectoriesThenEquals1() {
        List<String> directories =
            ParallelTreeHandler.findDirectoriesWithMoreThan1000Files(DIRECTORY_PATH);

        assertEquals(directories.size(), 1);
    }

    @Test
    @DisplayName("Тест поиска файлов по предикату .exe")
    void whenPredicateExeThenEquals1() {
        List<String> directories =
            ParallelTreeHandler.findFilesByPredicate(
                DIRECTORY_PATH_1, predicate -> predicate.toString().contains(".exe")
            );

        assertEquals(directories.size(), 1);
    }

    @Test
    @DisplayName("Тест поиска файлов по предикату .txt")
    void whenPredicateTxtThenEquals15() {
        List<String> directories =
            ParallelTreeHandler.findFilesByPredicate(
                DIRECTORY_PATH_1, predicate -> predicate.toString().contains(".txt")
            );

        assertEquals(directories.size(), 15);
    }
}
