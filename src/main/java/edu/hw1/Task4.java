package edu.hw1;

public class Task4 {

    private Task4() {
    }

    public static String fixString(String s) {
        StringBuilder answer = new StringBuilder();
        for (int i = 0; i < s.length() - 1; i += 2) {
            answer.append(s.charAt(i + 1));
            answer.append(s.charAt(i));
        }
        if (s.length() % 2 == 1) {
            answer.append(s.substring(s.length() - 1));
        }
        return answer.toString();
    }
}
