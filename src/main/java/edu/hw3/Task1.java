package edu.hw3;

import org.jetbrains.annotations.NotNull;

public class Task1 {
    private Task1() {
    }

    public static String atbash(@NotNull String input) {
        StringBuilder str = new StringBuilder();
        for (char ch : input.toCharArray()) {
            if ('a' <= ch && ch <= 'z') {
                str.append((char) ('z' - (ch - 'a')));
            } else if ('A' <= ch && ch <= 'Z') {
                str.append((char) ('Z' - (ch - 'A')));
            } else {
                str.append(ch);
            }
        }
        return str.toString();
    }
}
