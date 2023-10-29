package edu.hw3;

import edu.hw3.Task6.Stock;
import edu.hw3.Task6.TinkoffInvestments;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class Task6Test {

    @Test
    @DisplayName("Тестирование работы биржи")
    void test(){
        //given
        Stock st1 = new Stock("Apple", 123);
        Stock st2 = new Stock("Microsoft", 321);
        Stock st3 = new Stock("Google", 222);

        //when
        TinkoffInvestments portfolio = new TinkoffInvestments();
        portfolio.add(st1);
        portfolio.add(st2);
        portfolio.add(st3);

        //then
        assertEquals(st2.getCost(), portfolio.mostValuableStock().getCost());
        portfolio.remove(st2);
        assertEquals(st3.getCost(), portfolio.mostValuableStock().getCost());
        portfolio.remove(st3);
        assertEquals(st1.getCost(), portfolio.mostValuableStock().getCost());
        portfolio.remove(st1);
        assertNull(portfolio.mostValuableStock());
    }
}
