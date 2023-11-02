package edu.hw3;

import edu.hw3.Task5.Contact;
import edu.hw3.Task5.ContactParser;
import edu.hw3.Task5.SortingMethod;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Task5Test {
    @Test
    @DisplayName("Отработка метода по ASC")
    void parseContactsWithASC() {
        //given
        List<String> names = Arrays.asList("Ivan Ivanov", "Ivashka", "Anton Stepanenko",
            "Krishtianu Siuualdo", "Sheldon Cooper", "Rubashka");

        //when
        List<Contact> answer = ContactParser.parseContacts(names, SortingMethod.ASC);

        //then
        assertEquals(answer.get(0), new Contact("Sheldon", "Cooper"));
        assertEquals(answer.get(1), new Contact("Ivan", "Ivanov"));
        assertEquals(answer.get(2), new Contact("Krishtianu", "Siuualdo"));
        assertEquals(answer.get(3), new Contact("Anton", "Stepanenko"));
        assertEquals(answer.get(4), new Contact("Ivashka"));
        assertEquals(answer.get(5), new Contact("Rubashka"));

    }

    @Test
    @DisplayName("Отработка метода по DESC")
    void parseContactsWithDESC() {
        //given
        List<String> names = Arrays.asList("Ivan Ivanov", "Ivashka", "Anton Stepanenko",
            "Krishtianu Siuualdo", "Sheldon Cooper", "Rubashka");

        //when
        List<Contact> answer = ContactParser.parseContacts(names, SortingMethod.DESC);

        //then
        assertEquals(answer.get(0), new Contact("Rubashka"));
        assertEquals(answer.get(1), new Contact("Ivashka"));
        assertEquals(answer.get(2), new Contact("Anton", "Stepanenko"));
        assertEquals(answer.get(3), new Contact("Krishtianu", "Siuualdo"));
        assertEquals(answer.get(4), new Contact("Ivan", "Ivanov"));
        assertEquals(answer.get(5), new Contact("Sheldon", "Cooper"));
    }

    @Test
    @DisplayName("Введен пустой лист")
    void enteredEmptyListThenReturnsEmptyList() {
        //given
        List<String> names = new ArrayList<>();

        //when
        List<Contact> answer = ContactParser.parseContacts(names, SortingMethod.ASC);

        //then
        assertEquals(0, answer.size());
    }
}
