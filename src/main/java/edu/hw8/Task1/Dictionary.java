package edu.hw8.Task1;

import java.util.Map;

public class Dictionary {

    private Dictionary() {
    }

    public static final Map<String, String> KEYWORDS = Map.ofEntries(
        Map.entry("личности", "Не переходи на личности там, где их нет"),
        Map.entry("оскорбления",
            "Если твои противники перешли на личные оскорбления, будь уверена - твоя победа не за горами"),
        Map.entry("глупый", "А я тебе говорил, что ты глупый? Так вот, я забираю свои слова обратно..."),
        Map.entry("интеллект", "Чем ниже интеллект, тем громче оскорбления"));
}
