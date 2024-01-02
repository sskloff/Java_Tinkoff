package edu.hw7;

import edu.hw7.Task3.Cache;
import edu.hw7.Task3.Person;
import edu.hw7.Task3.ReadWriteLockCache;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Task3Test {

    @Test
    @DisplayName("Добавление записи Person, поиски")
    void addPersonAndFindTest() {
        Cache cache = new Cache();
        Person person = new Person(
            1, "Ivan", "Ivan's Address", "Ivan's Number");
        cache.add(person);
        assertEquals(cache.findByName("Ivan"), person);
        assertEquals(cache.findByAddress("Ivan's Address"), person);
        assertEquals(cache.findByPhone("Ivan's Number"), person);
    }

    @Test
    @DisplayName("RWL тест")
    void addPersonAndFindWRLTest() {
        ReadWriteLockCache cache = new ReadWriteLockCache();
        Person person = new Person(
            1, "Ivan", "Ivan's Address", "Ivan's Number");
        cache.add(person);
        assertEquals(cache.findByName("Ivan"), person);
        assertEquals(cache.findByAddress("Ivan's Address"), person);
        assertEquals(cache.findByPhone("Ivan's Number"), person);
    }
}
