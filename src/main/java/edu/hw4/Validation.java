package edu.hw4;

import java.util.HashSet;
import java.util.Set;

public class Validation {

    private Validation() {
    }

    public static Set<ValidationError> validateErrors(Animal animal) {
        Set<ValidationError> result = new HashSet<>();
        if (animal.name().isEmpty()) {
            result.add(ValidationError.INCORRECT_NAME);
        }
        if (animal.age() < 0) {
            result.add(ValidationError.INCORRECT_AGE);
        }
        if (animal.height() <= 0) {
            result.add(ValidationError.INCORRECT_HEIGHT);
        }
        if (animal.weight() <= 0) {
            result.add(ValidationError.INCORRECT_WEIGHT);
        }
        return result;
    }
}
