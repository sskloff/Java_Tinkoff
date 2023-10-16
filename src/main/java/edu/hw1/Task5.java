package edu.hw1;

public class Task5 {

    private Task5() {
    }

    private static String reverse(String str) {
        return new StringBuilder(str).reverse().toString();
    }

    private static int charSum(String chars) {
        int num1 = Integer.parseInt(chars.substring(0, 1));
        int num2 = Integer.parseInt(chars.substring(1, 2));
        return num1 + num2;
    }

    public static boolean isPalindromeDescendant(int number) {
        String num = String.valueOf(number);
        if (num.length() == 1) {
            return true;
        } else if (num.length() % 2 == 1) {
            num = num + "0";
        }
        StringBuilder newNum;
        while ((!num.equals(reverse(num)) && (num.length() > 2))) {
            newNum = new StringBuilder();
            for (int i = 0; i < num.length(); i += 2) {
                newNum.append(charSum(num.substring(i, i + 2)));
            }
            num = String.valueOf(newNum);
        }
        if (num.equals(reverse(num))) {
            return true;
        } else {
            num = String.valueOf(charSum(num.substring(0, 2)));
            if (num.length() == 2) {
                return num.equals(reverse(num));
            }
            return false;
        }
    }
}
