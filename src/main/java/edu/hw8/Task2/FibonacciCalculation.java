package edu.hw8.Task2;

public class FibonacciCalculation {

    private FibonacciCalculation() {
    }

    public static int calculate(int n) {
        if (n <= 1) {
            return n;
        }
        int num1 = 0;
        int num2 = 1;
        int num3 = 0;
        for (int i = 2; i <= n; i++) {
            num3 = num1 + num2;
            num1 = num2;
            num2 = num3;
        }
        return num3;
    }
}
