package edu.hw3;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.TreeMap;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Task7Test {

    @Test
    @DisplayName("Null помещается в дерево")
    void comparatorTest() {
        //given
        TreeMap<String, String> tree = new TreeMap<>(new Task7<>());

        //when
        tree.put("aa", "bb");
        tree.put(null, "cc");

        //then
        assertTrue(tree.containsKey(null));
    }
}
