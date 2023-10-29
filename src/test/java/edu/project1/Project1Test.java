package edu.project1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.HashSet;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Project1Test {

    @Test
    @DisplayName("Отработка открытия буквы и завершения игры победой")
    void openCharAndThenWinTest() {
        // given
        ArrayList<String> dictionary = Dictionary.loadDictionary();
        HashSet<Character> usedChar = new HashSet<>();
        usedChar.add('а');
        usedChar.add('л');
        usedChar.add('г');
        usedChar.add('е');
        usedChar.add('р');
        Gallows gallows = Gallows.valueOf(Game.STEPS[2]);
        Game testGame = Game.fabricGameCreator(dictionary, 0, false,
            usedChar, "алгебра", "алге*ра", gallows);

        // when
        TurnResult turnResult = Game.guessChar(testGame, 'б');
        GameResult gameResult = Game.turnResult(testGame);

        // then
        assertEquals("алгебра", testGame.guessedWord); // Изменение состояния отгаданного слова
        assertEquals(6, testGame.usedChar.size()); // Расширение использованных букв
        assertEquals(TurnResult.letterOpens, turnResult);
        assertEquals(GameResult.win, gameResult);
    }

    @Test
    @DisplayName("Отработка поражения и корректность запуска следующей игры")
    void loseAndThenNewGameTest() {
        // given
        ArrayList<String> dictionary = Dictionary.loadDictionary();
        HashSet<Character> usedChar = new HashSet<>();
        usedChar.add('о');
        usedChar.add('а');
        usedChar.add('б');
        usedChar.add('w');
        usedChar.add('q');
        usedChar.add('r');
        usedChar.add('z');
        Gallows gallows = Gallows.valueOf(Game.STEPS[6]);
        Game testGame = Game.fabricGameCreator(dictionary, 6, false,
            usedChar, "арестант", "а****а**", gallows);

        // when
        TurnResult turnResult = Game.guessChar(testGame, 'я');
        GameResult gameResult = Game.turnResult(testGame);
        // then
        assertEquals("а****а**", testGame.guessedWord);
        assertEquals(TurnResult.wrongLetterEntered, turnResult);
        assertEquals(GameResult.lose, gameResult);

        // when
        testGame = new Game(dictionary);
        // then
        assertEquals(0, testGame.usedChar.size()); // Очистка множества букв
        assertEquals(0, testGame.mistakeCount); // Счетчик ошибок сбросился
    }

    @Test
    @DisplayName("Отработка полноценной игры")
    void fullGameTest() {
        // given
        ArrayList<String> dictionary = Dictionary.loadDictionary();
        Gallows gallows = Gallows.valueOf(Game.STEPS[0]);
        Game testGame = Game.fabricGameCreator(dictionary, 0, false,
            new HashSet<>(), "стоп", "****", gallows);

        // when
        TurnResult turnResult = Game.guessChar(testGame, 'с');
        GameResult gameResult = Game.turnResult(testGame);
        // then
        assertEquals(TurnResult.letterOpens, turnResult);
        assertEquals(GameResult.gameContinues, gameResult);


        // when
        turnResult = Game.guessChar(testGame, 'т');
        gameResult = Game.turnResult(testGame);
        // then
        assertEquals(TurnResult.letterOpens, turnResult);
        assertEquals(GameResult.gameContinues, gameResult);


        // when
        turnResult = Game.guessChar(testGame, 'е');
        gameResult = Game.turnResult(testGame);
        // then
        assertEquals(TurnResult.wrongLetterEntered, turnResult);
        assertEquals(GameResult.gameContinues, gameResult);


        // when
        turnResult = Game.guessChar(testGame, 'о');
        gameResult = Game.turnResult(testGame);
        // then
        assertEquals(TurnResult.letterOpens, turnResult);
        assertEquals(GameResult.gameContinues, gameResult);


        // when
        turnResult = Game.guessChar(testGame, 'п');
        gameResult = Game.turnResult(testGame);
        // then
        assertEquals(TurnResult.letterOpens, turnResult);
        assertEquals(GameResult.win, gameResult);
        assertEquals("стоп", testGame.guessedWord); // Изменение состояния отгаданного слова
        assertEquals(5, testGame.usedChar.size()); // Расширение использованных букв
    }

    @Test
    @DisplayName("Прерывание игры")
    void gameInterruptionTest() {
        // given
        ArrayList<String> dictionary = Dictionary.loadDictionary();
        Gallows gallows = Gallows.valueOf(Game.STEPS[0]);
        Game testGame = Game.fabricGameCreator(dictionary, 0, false,
            new HashSet<>(), "стоп", "****", gallows);

        // when
        TurnResult turnResult = Game.guessChar(testGame, '#');

        // then
        assertEquals(TurnResult.gameInterrupted, turnResult);
    }
}
