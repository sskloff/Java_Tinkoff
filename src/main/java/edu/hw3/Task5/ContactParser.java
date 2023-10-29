package edu.hw3.Task5;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import org.jetbrains.annotations.NotNull;

public class ContactParser {

    private ContactParser() {
    }

    public static List<Contact> parseContacts(@NotNull List<String> names, SortingMethod method) {
        List<Contact> answer = new ArrayList<>();
        if (names.isEmpty()) {
            return answer;
        }
        for (String name : names) {
            String[] separatedName = name.split(" ");
            if (separatedName.length == 1) {
                answer.add(new Contact(separatedName[0]));
            } else if (separatedName.length >= 2) {
                answer.add(new Contact(separatedName[0], separatedName[1]));
            }
        }
        Comparator<Contact> comparator = Contact::compareTo;
        if (method.equals(SortingMethod.ASC)) {
            answer.sort(comparator);
        } else if (method.equals(SortingMethod.DESC)) {
            answer.sort(comparator.reversed());
        }
        return answer;
    }
}
