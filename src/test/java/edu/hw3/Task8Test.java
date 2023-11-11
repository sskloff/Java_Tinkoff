package edu.hw3;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class Task8Test {

    @Test
    @DisplayName("Числа")
    void integerCollectionTest() {
        //given
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5);

        //when
        Task8<Integer> iterator = new Task8<>(list);

        //then
        assertEquals(iterator.next(), 5);
        assertEquals(iterator.next(), 4);
        assertEquals(iterator.next(), 3);
        assertEquals(iterator.next(), 2);
        assertEquals(iterator.next(), 1);
        assertFalse(iterator.hasNext());
    }

    @Test
    @DisplayName("Строки")
    void stringCollectionTest() {
        //given
        List<String> list = Arrays.asList("Aaa", "BbB", "Cc");

        //when
        Task8<String> iterator = new Task8<>(list);

        //then
        assertEquals(iterator.next(), "Cc");
        assertEquals(iterator.next(), "BbB");
        assertEquals(iterator.next(), "Aaa");
        assertFalse(iterator.hasNext());
    }
}
