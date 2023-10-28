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

    @SuppressWarnings("ReturnCount")
    public static TurnResult guessChar(Game thisGame, char input) {
        if (input == '#') {
            return TurnResult.gameInterrupted;
        }
        if (thisGame.hiddenWord.contains(String.valueOf(input))) {
            if (thisGame.usedChar.contains(input)) {
                return TurnResult.letterWasGuessedPreviously;
            } else {
                thisGame.guessedWord = openChar(thisGame, input);
                if (!thisGame.guessedWord.contains("*")) {
                    thisGame.winnerStatus = true;
                }
                return TurnResult.letterOpens;
            }
        } else {
            if (thisGame.usedChar.contains(input)) {
                return TurnResult.wrongLetterWasEnteredPreviously;
            } else {
                thisGame.usedChar.add(input);
                thisGame.mistakeCount++;
                return TurnResult.wrongLetterEntered;
            }
        }
    }

    public static GameResult turnResult(Game thisGame) {
        thisGame.gallows = Gallows.valueOf(STEPS[thisGame.mistakeCount]);
        if (thisGame.mistakeCount == MISTAKE_MAX) {
            thisGame.winnerStatus = true;
            return GameResult.lose;
        } else if (thisGame.winnerStatus) {
            return GameResult.win;
        }
        return GameResult.gameContinues;
    }

    @SuppressWarnings("RegexpSinglelineJava")
    public static void run(ArrayList<String> dictionary) {
        System.out.println("\nВведите (#), чтобы сдаться");
        Game thisGame = new Game(dictionary);
        TurnResult turnResult;
        GameResult gameStatus;
        while (!thisGame.winnerStatus) {
            System.out.println(thisGame.gallows);
            System.out.println("Загаданное слово: " + thisGame.guessedWord);
            System.out.println("Допущено ошибок: " + thisGame.mistakeCount + "/" + MISTAKE_MAX);
            System.out.println("Вы уже вводили буквы: " + thisGame.usedChar);
            System.out.print("Введите букву: ");
            char input = Main.input();
            turnResult = guessChar(thisGame, input);
            if (turnResult.equals(TurnResult.gameInterrupted)) {
                System.exit(0);
            }
            if (turnResult.equals(TurnResult.letterWasGuessedPreviously)) {
                System.out.println("\nРанее Вы уже угадывали эту буквы");
            } else if (turnResult.equals(TurnResult.wrongLetterWasEnteredPreviously)) {
                System.out.println("\nБезумие - это повторение одних и тех же действий "
                    + "раз за разом, в надежде на изменение :^)");
            }
            gameStatus = turnResult(thisGame);
            if (gameStatus.equals(GameResult.win)) {
                System.out.println("\nВЫ ВЫИГРАЛИ!!!\n");
            } else if (gameStatus.equals(GameResult.lose)) {
                System.out.println(thisGame.gallows);
                System.out.println("Поздравляем! Вы - вишня :^)");
                System.out.println("Было загадано слово " + thisGame.hiddenWord + "\n");
            }
        }
    }
}
