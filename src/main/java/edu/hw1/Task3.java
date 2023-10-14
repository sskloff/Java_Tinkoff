package edu.hw1;

public class Task3 {

    private Task3() {
    }

    public static boolean isNestable(int[] array1, int[] array2) {
        boolean answer = false;
        int min1 = array1[0];
        int min2 = array2[0];
        int max1 = array1[0];
        int max2 = array2[0];
        for (int el : array1) {
            if (el < min1) {
                min1 = el;
            }
            if (el > max1) {
                max1 = el;
            }
        }
        for (int el : array2) {
            if (el < min1) {
                min2 = el;
            }
            if (el > max2) {
                max2 = el;
            }
        }
        if (min1 > min2 && max1 < max2) {
            answer = true;
        }
        return answer;
    }
}
