package edu.project1;

import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;

public final class Main {

    static Scanner scanner = new Scanner(System.in);

    private Main() {
    }

    @SuppressWarnings({"RegexpSinglelineJava"})
    public static char input() {
        String input;
        do {
            try {
                input = scanner.nextLine().toLowerCase();
                if (input.length() != 1) {
                    System.out.println("Пустой ввод или введено несколько символов. Повторите попытку");
                }
            } catch (NoSuchElementException e) {
                System.out.println("Ввод прерван");
                scanner.close();
                return '#';
            }
        } while (input.length() != 1);
        return input.charAt(0);
    }

    @SuppressWarnings({"RegexpSinglelineJava"})
    public static void main(String[] args) {
        ArrayList<String> dictionary = Dictionary.loadDictionary();
        while (true) {
            System.out.println("Для начала новой партии введите (N)\n"
                + "Для выхода из игры введите (#)");
            char input = input();
            if (input == 'n') {
                Game.run(dictionary);
            } else if (input == '#') {
                break;
            } else {
                System.out.println("Введен некорректный символ, повторите попытку");
            }
        }
    }
}
