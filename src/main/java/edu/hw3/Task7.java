package edu.hw3;

import java.util.Comparator;

public class Task7<T extends Comparable<T>> implements Comparator<T> {
    @Override
    public int compare(T el1, T el2) {
        if (el1 == null && el2 == null) {
            return 0;
        } else if (el1 == null) {
            return -1;
        } else if (el2 == null) {
            return 1;
        } else {
            return el1.compareTo(el2);
        }
    }
}
