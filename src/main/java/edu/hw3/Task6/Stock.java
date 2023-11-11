package edu.hw3.Task6;

public class Stock {
    private final String company;
    private int cost;

    public Stock(String company, int cost) {
        this.company = company;
        this.cost = cost;
    }

    public int getCost() {
        return cost;
    }
}
