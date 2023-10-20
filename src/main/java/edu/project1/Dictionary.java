package edu.project1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class Dictionary {

    private Dictionary() {
    }

    @SuppressWarnings({"RegexpSinglelineJava"})
    public static ArrayList<String> loadDictionary() {
        try {
            ArrayList<String> dictionary = new ArrayList<>();
            BufferedReader reader = new BufferedReader(
                new FileReader("./src/main/resources/dictionary.txt"));
            String line;
            while ((line = reader.readLine()) != null) {
                if (!line.isEmpty()) {
                    dictionary.add(line);
                }
            }
            reader.close();
            if (dictionary.isEmpty()) {
                System.out.println("Не удалось начать игру\nСловарь пусть");
                System.exit(2);
            } else {
                return dictionary;
            }
        } catch (IOException e) {
            System.out.println("Не удалось начать игру\nОтсутствует словарь");
            System.exit(1);
        }
        return null;
    }

    public static String getWord(ArrayList<String> dictionary) {
        int randomIndex = new Random().nextInt(dictionary.size());
        return dictionary.get(randomIndex);
    }
}
