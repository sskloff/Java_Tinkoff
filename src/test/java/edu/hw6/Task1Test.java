package edu.hw6;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import static java.nio.file.Files.createFile;
import static java.nio.file.Files.deleteIfExists;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Task1Test {


    @Test
    @DisplayName("Сохранение пары в файл")
    void whenSaveMapToAFileThenPairAppearsInFile() throws IOException {
        //given
        Path diskMapPath = Path.of("src/test/java/edu/hw6/test/");
        DiskMap diskMap = new DiskMap(diskMapPath.toString());

        //when
        diskMap.put("key", "value");
        diskMap.saveMapToAFile();
        diskMap.clear();
        BufferedReader reader = new BufferedReader(new FileReader(diskMapPath.toString()));
        String line = reader.readLine();

        //then
        assertEquals("key:value", line);
        deleteIfExists(diskMapPath);
    }

    @Test
    @DisplayName("Загрузка map из файла")
    void whenGetMapFromFileThenWeCanGetValue() throws IOException {
        //given
        Path diskMapPath = Path.of("src/test/java/edu/hw6/diskMap.txt");
        createFile(diskMapPath);
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(diskMapPath.toString()))) {
            bufferedWriter.write("key:value");
        }

        //when
        DiskMap diskMap = new DiskMap(diskMapPath.toString());

        //then
        assertThat(diskMap.isEmpty()).isTrue();

        //when
        diskMap.getMapFromFile();

        //then
        assertThat(diskMap.isEmpty()).isFalse();
        assertEquals("value", diskMap.get("key"));

        deleteIfExists(diskMapPath);
    }
}
