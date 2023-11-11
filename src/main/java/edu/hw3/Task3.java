package edu.hw3;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Task3<K> {

    public Map<K, Integer> freqDict(List<K> input) {
        Map<K, Integer> answer = new HashMap<>();
        for (K el : input) {
            if (!answer.containsKey(el)) {
                answer.put(el, 1);
            } else {
                answer.replace(el, answer.get(el) + 1);
            }
        }
        return answer;
    }
}
