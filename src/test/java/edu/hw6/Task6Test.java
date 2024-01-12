package edu.hw6;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatCode;

public class Task6Test {

    @Test
    @DisplayName("Тестирование сканера портов")
    void portScannerTest() {
        assertThatCode(PortScanner::checkPorts).doesNotThrowAnyException();
    }
}
