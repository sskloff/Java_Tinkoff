package edu.hw3.Task6;

import java.util.Comparator;
import java.util.PriorityQueue;
import org.jetbrains.annotations.NotNull;

public class TinkoffInvestments implements StockMarket {
    private final PriorityQueue<Stock> stocks = new PriorityQueue<>(new Comparator<Stock>() {
        @Override
        public int compare(Stock st1, Stock st2) {
            return Integer.compare(st2.getCost(), st1.getCost());
        }
    });

    @Override
    public void add(@NotNull Stock stock) {
        stocks.add(stock);
    }

    @Override
    public void remove(@NotNull Stock stock) {
        stocks.remove(stock);
    }

    @Override
    public Stock mostValuableStock() {
        return stocks.poll();
    }
}
