package edu.hw1;

public class Task2 {

    private Task2() {
    }

    @SuppressWarnings("MagicNumber")
    public static int countDigits(int number) {
        int count = 0;
        int num = number;
        do {
            num = num / 10;
            count++;
        } while (num != 0);
        return count;
    }
}
