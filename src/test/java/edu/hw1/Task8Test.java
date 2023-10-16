package edu.hw1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Task8Test {

    @Test
    @DisplayName("Отработка случая, когда кони НЕ бьют друг друга")
    void whenResultTrue1() {
        // given
        int[][] matrix = {{0, 0, 0, 1, 0, 0, 0, 0},
                          {0, 0, 0, 0, 0, 0, 0, 0},
                          {0, 1, 0, 0, 0, 1, 0, 0},
                          {0, 0, 0, 0, 1, 0, 1, 0},
                          {0, 1, 0, 0, 0, 1, 0, 0},
                          {0, 0, 0, 0, 0, 0, 0, 0},
                          {0, 1, 0, 0, 0, 0, 0, 1},
                          {0, 0, 0, 0, 1, 0, 0, 0}};

        // when
        boolean result = Task8.knightBoardCapture(matrix);

        // then
        assertTrue(result);
    }

    @Test
    @DisplayName("Отработка случая, когда кони БЬЮТ друг друга")
    void whenResultFalse1() {
        // given
        int[][] matrix = {{1, 0, 1, 0, 1, 0, 1, 0},
                          {0, 1, 0, 1, 0, 1, 0, 1},
                          {0, 0, 0, 0, 1, 0, 1, 0},
                          {0, 0, 1, 0, 0, 1, 0, 1},
                          {1, 0, 0, 0, 1, 0, 1, 0},
                          {0, 0, 0, 0, 0, 1, 0, 1},
                          {1, 0, 0, 0, 1, 0, 1, 0},
                          {0, 0, 0, 1, 0, 1, 0, 1}};

        // when
        boolean result = Task8.knightBoardCapture(matrix);

        // then
        assertFalse(result);
    }

    @Test
    @DisplayName("Отработка случая, когда кони БЬЮТ друг друга")
    void whenResultFalse2() {
        // given
        int[][] matrix = {{0, 0, 0, 0, 1, 0, 0, 0},
                          {0, 0, 0, 0, 0, 1, 0, 0},
                          {0, 0, 0, 1, 0, 0, 0, 0},
                          {1, 0, 0, 0, 0, 0, 0, 0},
                          {0, 0, 0, 0, 1, 0, 0, 0},
                          {0, 0, 0, 0, 0, 1, 0, 0},
                          {0, 0, 0, 0, 0, 1, 0, 0},
                          {1, 0, 0, 0, 0, 0, 0, 0}};

        // when
        boolean result = Task8.knightBoardCapture(matrix);

        // then
        assertFalse(result);
    }

    @Test
    @DisplayName("Отработка случая, когда кони НЕ бьют друг друга")
    void whenResultTrue2() {
        // given
        int[][] matrix = {{1, 0, 0, 0, 1, 0, 0, 0},
                          {0, 0, 0, 0, 0, 1, 0, 1},
                          {0, 0, 0, 0, 0, 0, 0, 0},
                          {1, 1, 0, 0, 0, 0, 0, 0},
                          {0, 0, 0, 0, 1, 0, 0, 0},
                          {0, 0, 0, 0, 0, 1, 0, 0},
                          {0, 1, 0, 0, 0, 0, 0, 0},
                          {1, 0, 0, 0, 0, 0, 0, 1}};

        // when
        boolean result = Task8.knightBoardCapture(matrix);

        // then
        assertTrue(result);
    }
}
