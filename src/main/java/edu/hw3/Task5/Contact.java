package edu.hw3.Task5;

import java.util.Objects;
import org.jetbrains.annotations.NotNull;

public record Contact(String firstName, String surname) implements Comparable<Contact> {

    public Contact(String firstName) {
        this(firstName, "");
    }

    @Override
    public int compareTo(@NotNull Contact cont) {
        if (Objects.equals(this.surname, "") || Objects.equals(cont.surname, "")
            || this.surname.compareTo(cont.surname) == 0) {
            return this.firstName.compareTo(cont.firstName);
        } else {
            return this.surname.compareTo(cont.surname);
        }
    }
}
