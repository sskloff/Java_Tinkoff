package edu.hw10;

import edu.hw10.Task2.CacheProxy;
import edu.hw10.Task2.FibCalculator.FibCalculator;
import edu.hw10.Task2.FibCalculator.SimpleFibCalculator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import static java.nio.file.Files.deleteIfExists;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Task2Test {

    @Test
    @DisplayName("Отработка кэширования и сохранение в файл")
    void whenSaveToFileThenFileAppearsAndEqualsExpected() throws IOException {
        //given
        FibCalculator fibCalculator = new SimpleFibCalculator();
        FibCalculator proxy = CacheProxy.create(fibCalculator, fibCalculator.getClass());

        //when
        proxy.calculate(10);

        //then
        File file = new File("calculate10.cache");
        assertTrue(file.exists());
        String firstLine = Files.readAllLines(file.toPath()).getFirst();
        deleteIfExists(Path.of(file.toURI()));
        assertEquals(firstLine, "Method=calculate(int) args=[10] result=55");
    }
}
