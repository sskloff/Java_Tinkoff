package edu.hw1;

public class Task8 {

    private Task8() {
    }

    private static final int[][] KNIGHT_STEPS = {
        {-2, -1}, {-2, 1},
        {-1, -2}, {-1, 2},
        {1, -2}, {1, 2},
        {2, -1}, {2, 1}};

    @SuppressWarnings("MagicNumber")
    private static boolean isValid(int x, int y) {
        return x >= 0 && x < 8 && y >= 0 && y < 8;
    }

    @SuppressWarnings("MagicNumber")
    private static boolean captureCapabilityCheck(int x, int y, int[][] matrix) {
        for (int i = 0; i < 8; ++i) {
            int newX = x + KNIGHT_STEPS[i][0];
            int newY = y + KNIGHT_STEPS[i][1];
            if (isValid(newX, newY)) {
                if (matrix[newX][newY] == 1) {
                    return false;
                }
            }
        }
        return true;
    }

    public static boolean knightBoardCapture(int[][] matrix) {
        boolean answer = true;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (matrix[i][j] == 1) {
                    answer = captureCapabilityCheck(i, j, matrix);
                    if (!answer) {
                        return answer;
                    }
                }
            }
        }
        return answer;
    }
}
