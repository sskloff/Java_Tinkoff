package edu.hw10.Models;

import edu.hw10.Task1.Annotations.Max;
import edu.hw10.Task1.Annotations.Min;
import edu.hw10.Task1.Annotations.NotNull;

public record TestRecord(@Max(value = 10) @Min(value = -10) int value, @NotNull String str) {
}
