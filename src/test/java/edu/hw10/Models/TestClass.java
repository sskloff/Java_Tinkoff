package edu.hw10.Models;

import edu.hw10.Task1.Annotations.Max;
import edu.hw10.Task1.Annotations.Min;
import edu.hw10.Task1.Annotations.NotNull;

public class TestClass {
    public final int value;
    public final String str;

    public TestClass(@Max(value = 10) @Min(value = -10) int value, @NotNull String str) {
        this.value = value;
        this.str = str;
    }

    public static TestClass create(@Max(value = 10) @Min(value = -10) int value, @NotNull String str) {
        return new TestClass(value, str);
    }
}
