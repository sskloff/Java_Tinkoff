package edu.hw1;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Task3Test {
    @Test
    void whenFirstArrayCanBeNestedThenResultTrue1() {
        // given
        int[] array1 = {1, 2, 3, 4};
        int[] array2 = {0, 6};

        // when
        boolean result = Task3.isNestable(array1, array2);

        // then
        assertTrue(result);
    }

    @Test
    void whenFirstArrayCanBeNestedThenResultTrue2() {
        // given
        int[] array1 = {3, 1};
        int[] array2 = {4, 0};

        // when
        boolean result = Task3.isNestable(array1, array2);

        // then
        assertTrue(result);
    }

    @Test
    void whenFirstArrayCantBeNestedThenResultFalse1() {
        // given
        int[] array1 = {9, 9, 8};
        int[] array2 = {8, 9};

        // when
        boolean result = Task3.isNestable(array1, array2);

        // then
        assertFalse(result);
    }

    @Test
    void whenFirstArrayCantBeNestedThenResultFalse2() {
        // given
        int[] array1 = {1, 2, 3, 4};
        int[] array2 = {2, 3};

        // when
        boolean result = Task3.isNestable(array1, array2);

        // then
        assertFalse(result);
    }
}
