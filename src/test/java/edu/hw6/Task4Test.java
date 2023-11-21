package edu.hw6;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Task4Test {


    @Test
    @DisplayName("Проверка записи строчки в файл")
    void whenWriteTextThenTextAppearsInFile() throws IOException {
        //given
        File testFile = new File("test.txt");

        //when
        OutputStreamComposition.writeText("test.txt", "BlaBla");
        String response;
        try (BufferedReader br = new BufferedReader(new FileReader(testFile))) {
            response = br.readLine();
        }

        //then
        assertEquals("BlaBla", response);
        testFile.delete();
    }
}
