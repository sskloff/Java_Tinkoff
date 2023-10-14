package edu.hw1;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Task1Test {
    @Test
    void when01Min00SecThenResult60() {
        // given
        String time = "01:00";

        // when
        int result = Task1.minutesToSeconds(time);

        // then
        assertEquals(60, result);
    }

    @Test
    void when13Min56SecThenResult836() {
        // given
        String time = "13:56";

        // when
        int result = Task1.minutesToSeconds(time);

        // then
        assertEquals(836, result);
    }

    @Test
    void when10Min60SecThenResultMinus1() {
        // given
        String time = "10:60";

        // when
        int result = Task1.minutesToSeconds(time);

        // then
        assertEquals(-1, result);
    }

    @Test
    void when999Min59SecThenResult59999() {
        // given
        String time = "999:59";

        // when
        int result = Task1.minutesToSeconds(time);

        // then
        assertEquals(59999, result);
    }

    @Test
    void when04Min100SecThenResultMinus1() {
        // given
        String time = "04:100";

        // when
        int result = Task1.minutesToSeconds(time);

        // then
        assertEquals(-1, result);
    }

    @Test
    void whenIncorrectTimeThenResultMinus1() {
        // given
        String time = "aa:bb";

        // when
        int result = Task1.minutesToSeconds(time);

        // then
        assertEquals(-1, result);
    }
}
