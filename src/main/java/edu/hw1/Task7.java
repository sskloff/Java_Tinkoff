package edu.hw1;

public class Task7 {

    private Task7() {
    }

    private static int pow(int base, int power) {
        int answer = 1;
        for (int i = 0; i < power; i++) {
            answer = answer * base;
        }
        return answer;
    }

    public static int rotateLeft(int n, int shift) {
        int m = 0;
        int tmp = n;
        int localShift = shift;
        while (tmp != 0) {
            tmp /= 2;
            m += 1;
        }
        localShift %= m;
        return (n / pow(2, m - localShift)) + (n % pow(2, m - localShift)) * pow(2, localShift);
    }

    public static int rotateRight(int n, int shift) {
        int m = 0;
        int tmp = n;
        int localShift = shift;
        if (n <= 0) {
            return -1;
        }
        while (tmp != 0) {
            tmp /= 2;
            m += 1;
        }
        localShift %= m;
        return (n / pow(2, localShift)) + (n % pow(2, localShift)) * pow(2, (m - localShift));
    }
}
