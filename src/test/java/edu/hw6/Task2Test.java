package edu.hw6;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import static java.nio.file.Files.createDirectory;
import static java.nio.file.Files.createFile;
import static java.nio.file.Files.deleteIfExists;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Task2Test {

    @Test
    @DisplayName("Тестирование копирования файлов")
    void whenCloneFileThenCopiesNamesEqualsExpected() throws IOException {
        //given
        Path directoryPath = Path.of("src/test/java/edu/hw6/test/");
        Path txtFilePath = Paths.get(directoryPath.toString(), "test.txt");
        createDirectory(directoryPath);
        createFile(txtFilePath);

        Path firstCopyPath = Path.of("./src/test/java/edu/hw6/test/test — копия.txt");
        Path secondCopyPath = Path.of("./src/test/java/edu/hw6/test/test — копия (2).txt");
        Files.deleteIfExists(firstCopyPath);
        Files.deleteIfExists(secondCopyPath);

        //when
        CloneFile.cloneFile(txtFilePath);
        CloneFile.cloneFile(txtFilePath);

        //then
        assertTrue(Files.exists(firstCopyPath));
        assertTrue(Files.exists(secondCopyPath));

        deleteIfExists(secondCopyPath);
        deleteIfExists(firstCopyPath);
        deleteIfExists(txtFilePath);
        deleteIfExists(directoryPath);
    }
}
