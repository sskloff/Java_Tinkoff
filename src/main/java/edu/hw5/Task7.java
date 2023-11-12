package edu.hw5;

public class Task7 {

    private Task7() {
    }

    public static boolean containsMoreThen2SymbolsAnd3rdIs0(String str) {
        return str.matches("^[01]{2}0[01]*$");
    }

    public static boolean startsAndEndsWithSameSymbol(String str) {
        return str.matches("^([01]).*\\1$");
    }

    public static boolean isLengthFrom1To3(String str) {
        return str.matches("^[01]{1,3}$");
    }
}
