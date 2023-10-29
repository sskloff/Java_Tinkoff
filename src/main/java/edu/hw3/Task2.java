package edu.hw3;

import java.util.ArrayList;
import java.util.List;
import org.jetbrains.annotations.NotNull;

public class Task2 {
    private Task2() {
    }

    @SuppressWarnings("ReturnCount")
    public static List<String> clusterize(@NotNull String input) {
        if (input.isEmpty()) {
            return null;
        }
        int bracketCount = 0;
        StringBuilder str = new StringBuilder();
        ArrayList<String> answer = new ArrayList<>();
        for (char ch : input.toCharArray()) {
            if (ch == '(') {
                bracketCount++;
            } else if (ch == ')') {
                bracketCount--;
            } else {
                return null;
            }
            str.append(ch);
            if (bracketCount == 0) {
                answer.add(str.toString());
                str.delete(0, str.length());
            } else if (bracketCount < 0) {
                return null;
            }
        }
        if (!str.isEmpty()) {
            return null;
        }
        return answer;
    }
}
