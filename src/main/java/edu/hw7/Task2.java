package edu.hw7;

import java.util.ArrayList;
import java.util.List;

public class Task2 {

    private Task2() {
    }

    public static int calculateFactorialWithConcurrency(int value) {
        List<Integer> values = new ArrayList<>();
        for (int i = 1; i - 1 < value; i++) {
            values.add(i);
        }
        return values.parallelStream().reduce(1, (a, b) -> a * b);
    }
}
