package edu.hw10.Task2.FibCalculator;

public class SimpleFibCalculator implements FibCalculator {

    @Override
    public long calculate(int number) {
        if (number <= 1) {
            return number;
        }
        int num1 = 0;
        int num2 = 1;
        int num3 = 0;
        for (int i = 2; i <= number; i++) {
            num3 = num1 + num2;
            num1 = num2;
            num2 = num3;
        }
        return num3;
    }
}
