package edu.hw6;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Task5Test {

    @Test
    @DisplayName("Проверка title для 37570037")
    void when37570037ThenTitleJDK21ReleaseNotes() {
        assertEquals(HackerNews.news(37570037), "JDK 21 Release Notes");
    }

    @Test
    @DisplayName("Проверка что в массиве топ историй 500 записи")
    void whenHackerNewsTopStroiesThen500() {
        assertThat(HackerNews.hackerNewsTopStories()).hasSize(500);
    }
}
