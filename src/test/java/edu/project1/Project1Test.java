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
        HashSet<Character> usedChar = new HashSet<>();
        usedChar.add('а');
        usedChar.add('л');
        usedChar.add('г');
        usedChar.add('е');
        usedChar.add('р');
        Gallows gallows = Gallows.valueOf(Game.STEPS[2]);
        Game testGame = new Game(
            0, false, usedChar,
            "алгебра", "алге*ра", gallows
        );

        // when
        int code = Game.guessChar(testGame, 'б');
        int gameResult = Game.turnResult(testGame);

        // then
        assertEquals("алгебра", testGame.guessedWord); // Изменение состояния отгаданного слова
        assertEquals(6, testGame.usedChar.size()); // Расширение использованных букв
        assertEquals(1, code); // Код 1 - статус "отгадана буква"
        assertEquals(1, gameResult); // Код 1 - статус "победа"
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
        Game testGame = new Game(
            6, false, usedChar,
            "арестант", "а****а**", gallows
        );

        // when
        int code = Game.guessChar(testGame, 'я');
        int gameResult = Game.turnResult(testGame);
        // then
        assertEquals("а****а**", testGame.guessedWord);
        assertEquals(3, code); // Код 3 - статус "введена неправильная буква"
        assertEquals(0, gameResult); // Код 0 - статус "поражение"

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
        Gallows gallows = Gallows.valueOf(Game.STEPS[0]);
        Game testGame = new Game(
            0, false, new HashSet<>(),
            "стоп", "****", gallows
        );

        // when
        int code = Game.guessChar(testGame, 'с');
        int gameResult = Game.turnResult(testGame);
        // then
        assertEquals(1, code); // Код 1 - статус "отгадана буква"
        assertEquals(2, gameResult); // Код 2 - статус "игра продолжается"


        // when
        code = Game.guessChar(testGame, 'т');
        gameResult = Game.turnResult(testGame);
        // then
        assertEquals(1, code); // Код 1 - статус "отгадана буква"
        assertEquals(2, gameResult); // Код 2 - статус "игра продолжается"


        // when
        code = Game.guessChar(testGame, 'е');
        gameResult = Game.turnResult(testGame);
        // then
        assertEquals(3, code); // Код 3 - статус "введена неправильная буква"
        assertEquals(2, gameResult); // Код 2 - статус "игра продолжается"


        // when
        code = Game.guessChar(testGame, 'о');
        gameResult = Game.turnResult(testGame);
        // then
        assertEquals(1, code); // Код 1 - статус "отгадана буква"
        assertEquals(2, gameResult); // Код 2 - статус "игра продолжается"


        // when
        code = Game.guessChar(testGame, 'п');
        gameResult = Game.turnResult(testGame);
        // then
        assertEquals(1, code); // Код 1 - статус "отгадана буква"
        assertEquals(1, gameResult); // Код 1 - статус "победа"
        assertEquals("стоп", testGame.guessedWord); // Изменение состояния отгаданного слова
        assertEquals(5, testGame.usedChar.size()); // Расширение использованных букв
    }
}
