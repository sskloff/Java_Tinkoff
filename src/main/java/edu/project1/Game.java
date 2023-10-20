package edu.project1;

import java.util.ArrayList;
import java.util.HashSet;

public class Game {
    public static final int MISTAKE_MAX = 7;
    public static final String[] STEPS = {"StepZero", "StepOne", "StepTwo",
        "StepThree", "StepFour", "StepFive", "StepSix", "StepSeven"};
    int mistakeCount;
    boolean winnerStatus;
    String hiddenWord;
    String guessedWord;
    HashSet<Character> usedChar;
    Gallows gallows;

    public Game(ArrayList<String> dictionary) {
        this.mistakeCount = 0;
        this.winnerStatus = false;
        this.usedChar = new HashSet<>();
        this.hiddenWord = Dictionary.getWord(dictionary);
        this.guessedWord = "*".repeat(hiddenWord.length());
        this.gallows = Gallows.valueOf(STEPS[mistakeCount]);
    }

    public Game(// Перегруженный конструктор для тестирования
        int mistakeCount, boolean winnerStatus,
        HashSet<Character> usedChar, String hiddenWord,
        String guessedWord, Gallows gallows
    ) {
        this.mistakeCount = mistakeCount;
        this.winnerStatus = winnerStatus;
        this.usedChar = usedChar;
        this.hiddenWord = hiddenWord;
        this.guessedWord = guessedWord;
        this.gallows = gallows;
    }

    public static String openChar(Game thisGame, char input) {
        char[] tmp = thisGame.hiddenWord.toCharArray();
        char[] tmpGuessed = thisGame.guessedWord.toCharArray();
        for (int i = 0; i < thisGame.hiddenWord.length(); i++) {
            if (tmp[i] == input) {
                tmpGuessed[i] = input;
                thisGame.usedChar.add(input);
            }
        }
        thisGame.guessedWord = new String(tmpGuessed);
        return thisGame.guessedWord;
    }

    @SuppressWarnings("MagicNumber")
    public static int guessChar(Game thisGame, char input) {
        if (thisGame.hiddenWord.contains(String.valueOf(input))) {
            if (thisGame.usedChar.contains(input)) {
                return 0; // Ранее буква была отгадана
            } else {
                thisGame.guessedWord = openChar(thisGame, input);
                if (!thisGame.guessedWord.contains("*")) {
                    thisGame.winnerStatus = true;
                }
                return 1; // Была открыта буква
            }
        } else {
            if (thisGame.usedChar.contains(input)) {
                return 2; // Ранее эта неправильная буква вводилась
            } else {
                thisGame.usedChar.add(input);
                thisGame.mistakeCount++;
                return 3; // Введена неправильная буква
            }
        }
    }

    @SuppressWarnings("MagicNumber")
    public static int turnResult(Game thisGame) {
        thisGame.gallows = Gallows.valueOf(STEPS[thisGame.mistakeCount]);
        if (thisGame.mistakeCount == MISTAKE_MAX) {
            thisGame.winnerStatus = true;
            return 0; // Поражение
        } else if (thisGame.winnerStatus) {
            return 1; // Победа
        }
        return 2; // Игра продолжается
    }

    @SuppressWarnings({"MagicNumber", "RegexpSinglelineJava"})
    public static void run(ArrayList<String> dictionary) {
        System.out.println("\nНажмите Ctrl+D, чтобы сдаться");
        Game thisGame = new Game(dictionary);
        int guessCode;
        int gameResult;
        while (!thisGame.winnerStatus) {
            System.out.println(thisGame.gallows);
            System.out.println("Загаданное слово: " + thisGame.guessedWord);
            System.out.println("Допущено ошибок: " + thisGame.mistakeCount + "/" + MISTAKE_MAX);
            System.out.println("Вы уже вводили буквы: " + thisGame.usedChar);
            System.out.print("Введите букву: ");
            char input = Main.input();
            if (input == '#') {
                System.exit(3);
            }
            guessCode = guessChar(thisGame, input);
            if (guessCode == 0) {
                System.out.println("\nРанее Вы уже угадывали эту буквы");
            } else if (guessCode == 2) {
                System.out.println("\nБезумие - это повторение одних и тех же действий "
                    + "раз за разом, в надежде на изменение :^)");
            }
            gameResult = turnResult(thisGame);
            if (gameResult == 1) {
                System.out.println("\nВЫ ВЫИГРАЛИ!!!\n");
            } else if (gameResult == 0) {
                System.out.println(thisGame.gallows);
                System.out.println("Поздравляем! Вы - вишня :^)");
                System.out.println("Было загадано слово " + thisGame.hiddenWord + "\n");
            }
        }
    }
}
