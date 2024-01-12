package edu.hw6;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class DiskMap implements Map<String, String> {
    private final String filePath;
    private final Map<String, String> cachedMap;

    public DiskMap(String path) {
        filePath = path;
        cachedMap = new HashMap<>();
    }

    public void getMapFromFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] pairKV = line.split(":");
                if (pairKV.length != 2) {
                    throw new RuntimeException("Данные невозможно поместить в Map");
                }
                cachedMap.put(pairKV[0], pairKV[1]);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void saveMapToAFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (Map.Entry<String, String> entry : cachedMap.entrySet()) {
                writer.write(entry.getKey() + ":" + entry.getValue());
                writer.newLine();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int size() {
        return cachedMap.size();
    }

    @Override
    public boolean isEmpty() {
        return cachedMap.isEmpty();
    }

    @Override
    public boolean containsKey(Object key) {
        return cachedMap.containsKey(key);
    }

    @Override
    public boolean containsValue(Object value) {
        return cachedMap.containsValue(value);
    }

    @Override
    public String get(Object key) {
        return cachedMap.get(key);
    }

    @Nullable
    @Override
    public String put(String key, String value) {
        return cachedMap.put(key, value);
    }

    @Override
    public String remove(Object key) {
        return cachedMap.remove(key);
    }

    @Override
    public void putAll(@NotNull Map<? extends String, ? extends String> m) {
        cachedMap.putAll(m);
    }

    @Override
    public void clear() {
        cachedMap.clear();
    }

    @NotNull
    @Override
    public Set<String> keySet() {
        return cachedMap.keySet();
    }

    @NotNull
    @Override
    public Collection<String> values() {
        return cachedMap.values();
    }

    @NotNull
    @Override
    public Set<Entry<String, String>> entrySet() {
        return cachedMap.entrySet();
    }
}
