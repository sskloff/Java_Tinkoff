package edu.hw5;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Task5Test {

    @Test
    @DisplayName("Корректный номерной знак с двузначным регионом")
    void whenCorrectPlateWithTwoDigitRegionThenTrue() {
        assertTrue(Task5.isLicensePlateValid("А231МВ76"));
    }

    @Test
    @DisplayName("Корректный номерной знак с трехзначным регионом")
    void whenCorrectPlateWithThreeDigitRegionThenTrue() {
        assertTrue(Task5.isLicensePlateValid("А231МВ763"));
    }

    @Test
    @DisplayName("Корректный номер, но маленькая буква -> некорректный")
    void whenLowerCaseThenFalse() {
        assertFalse(Task5.isLicensePlateValid("А231мВ763"));
    }

    @Test
    @DisplayName("Неправильный порядок")
    void whenWrongOrderThenFalse() {
        assertFalse(Task5.isLicensePlateValid("АМ23В1763"));
    }

    @Test
    @DisplayName("Неподходящая буква Г")
    void whenWrongLetterThenFalse() {
        assertFalse(Task5.isLicensePlateValid("А231ГВ763"));
    }
}
