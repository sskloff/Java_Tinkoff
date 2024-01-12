package edu.hw10.Task2.FibCalculator;

import edu.hw10.Task2.Annotations.Cache;

public interface FibCalculator {

    @Cache(persist = true)
    long calculate(int number);
}
