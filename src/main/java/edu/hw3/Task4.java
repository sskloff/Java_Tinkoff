package edu.hw3;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class Task4 {

    private Task4() {
    }

    private static final Map<Integer, String> ROMAN_NUMERALS = Map.ofEntries(
        Map.entry(1, "I"),
        Map.entry(4, "IV"),
        Map.entry(5, "V"),
        Map.entry(9, "IX"),
        Map.entry(10, "X"),
        Map.entry(40, "XL"),
        Map.entry(50, "L"),
        Map.entry(90, "XC"),
        Map.entry(100, "C"),
        Map.entry(400, "CD"),
        Map.entry(500, "D"),
        Map.entry(900, "CM"),
        Map.entry(1000, "M"));

    private static final List<Integer> ARABIC_NUMBERS = Arrays.asList(
        1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1);

    @SuppressWarnings("MagicNumber")
    public static String convertToRoman(int number) {
        if (number <= 0 || number >= 4000) {
            throw new IllegalArgumentException();
        }
        int newNumber = number;
        StringBuilder answer = new StringBuilder();
        for (int arabicNumber : ARABIC_NUMBERS) {
            String romanSymbol = ROMAN_NUMERALS.get(arabicNumber);
            while (newNumber >= arabicNumber) {
                answer.append(romanSymbol);
                newNumber -= arabicNumber;
            }
            if (newNumber == 0) {
                break;
            }
        }
        return answer.toString();
    }
}
