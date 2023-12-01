package edu.hw5;

public class Task8 {

    private Task8() {
    }

    public static boolean isLengthOdd(String str) {
        return str.matches("^[01]([01]{2})*$");
    }

    public static boolean startsWith0AndOddLengthOrStartsWith1AndEvenLength(String str) {
        return str.matches("^0([01]{2})*$|^1[01]([01]{2})*$");
    }

    public static boolean everyOddSymbolIs1(String str) {
        return str.matches("^1([01]1)*[01]?$");
    }

    public static boolean haveMoreThen1ZeroAndLessThen2Ones(String str) {
        return str.matches("^0*(100|010|001|00)0*$");
    }
}
