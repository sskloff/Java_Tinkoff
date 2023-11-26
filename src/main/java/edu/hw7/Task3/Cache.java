package edu.hw7.Task3;

import java.util.HashMap;
import java.util.Map;

public class Cache implements PersonDatabase {
    private static final Map<Integer, Person> ID_CACHE = new HashMap<>();
    private static final Map<String, Person> NAME_CACHE = new HashMap<>();
    private static final Map<String, Person> ADDRESS_CACHE = new HashMap<>();
    private static final Map<String, Person> PHONE_CACHE = new HashMap<>();

    @Override
    public synchronized void add(Person person) {
        ID_CACHE.put(person.id(), person);
        NAME_CACHE.put(person.name(), person);
        ADDRESS_CACHE.put(person.address(), person);
        PHONE_CACHE.put(person.phoneNumber(), person);
    }

    @Override
    public synchronized void delete(int id) {
        Person person = ID_CACHE.remove(id);
        if (person != null) {
            NAME_CACHE.remove(person.name());
            ADDRESS_CACHE.remove(person.address());
            PHONE_CACHE.remove(person.phoneNumber());
        }
    }

    @Override
    public synchronized Person findByName(String name) {
        return NAME_CACHE.get(name);
    }

    @Override
    public Person findByAddress(String address) {
        return ADDRESS_CACHE.get(address);
    }

    @Override
    public Person findByPhone(String phone) {
        return PHONE_CACHE.get(phone);
    }
}
