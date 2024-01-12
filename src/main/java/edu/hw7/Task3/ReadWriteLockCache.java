package edu.hw7.Task3;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReadWriteLockCache implements PersonDatabase {
    private static final Map<Integer, Person> ID_CACHE = new HashMap<>();
    private static final Map<String, Person> NAME_CACHE = new HashMap<>();
    private static final Map<String, Person> ADDRESS_CACHE = new HashMap<>();
    private static final Map<String, Person> PHONE_CACHE = new HashMap<>();
    private static final ReadWriteLock LOCKER = new ReentrantReadWriteLock();

    @Override
    public void add(Person person) {
        LOCKER.writeLock().lock();
        try {
            ID_CACHE.put(person.id(), person);
            NAME_CACHE.put(person.name(), person);
            ADDRESS_CACHE.put(person.address(), person);
            PHONE_CACHE.put(person.phoneNumber(), person);
        } finally {
            LOCKER.writeLock().unlock();
        }
    }

    @Override
    public void delete(int id) {
        LOCKER.writeLock().lock();
        try {
            Person person = ID_CACHE.remove(id);
            if (person != null) {
                NAME_CACHE.remove(person.name());
                ADDRESS_CACHE.remove(person.address());
                PHONE_CACHE.remove(person.phoneNumber());
            }
        } finally {
            LOCKER.writeLock().unlock();
        }
    }

    @Override
    public Person findByName(String name) {
        LOCKER.readLock().lock();
        try {
            return NAME_CACHE.get(name);
        } finally {
            LOCKER.readLock().unlock();
        }
    }

    @Override
    public Person findByAddress(String address) {
        LOCKER.readLock().lock();
        try {
            return ADDRESS_CACHE.get(address);
        } finally {
            LOCKER.readLock().unlock();
        }
    }

    @Override
    public Person findByPhone(String phone) {
        LOCKER.readLock().lock();
        try {
            return PHONE_CACHE.get(phone);
        } finally {
            LOCKER.readLock().unlock();
        }
    }
}
