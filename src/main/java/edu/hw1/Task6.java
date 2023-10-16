package edu.hw1;

import java.util.ArrayList;
import java.util.Collections;

public class Task6 {

    private Task6() {
    }

    public static final int KAPREKARS_CONSTANT = 6174;

    private static boolean identicalCheck(int n) {
        String num = String.valueOf(n);
        String ch = num.substring(0, 1);
        for (int i = 0; i < num.length(); i++) {
            if (!num.substring(i, i + 1).equals(ch)) {
                return false;
            }
        }
        return true;
    }

    @SuppressWarnings("MagicNumber")
    public static int countK(int n) {
        int answer = 1;
        int num = n;
        StringBuilder newNumber1 = new StringBuilder();
        StringBuilder newNumber2 = new StringBuilder();
        int count = 0;
        if (num <= 1000 || identicalCheck(n) || num > 9999) {
            return -1; //Не прописано как отлавливать подобные вводы
        }
        ArrayList<Integer> numbers = new ArrayList<>();
        do {
            numbers.add(num % 10);
            num = num / 10;
            count++;
        } while (num != 0);
        Collections.sort(numbers);
        for (int i = 0; i < count; i++) {
            newNumber1.append(numbers.get(count - 1 - i));
            newNumber2.append(numbers.get(i));
        }
        num = Integer.parseInt(String.valueOf(newNumber1)) - Integer.parseInt(String.valueOf(newNumber2));
        if (num != KAPREKARS_CONSTANT) {
            answer += countK(num);
        }
        return answer;
    }
}
