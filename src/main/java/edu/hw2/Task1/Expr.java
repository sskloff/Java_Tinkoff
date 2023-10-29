package edu.hw2.Task1;

public sealed interface Expr {

    double evaluate();

    public record Constant(double value) implements Expr {

        @Override
        public double evaluate() {
            return value;
        }
    }

    public record Negate(Expr value) implements Expr {

        @Override
        public double evaluate() {
            return -value.evaluate();
        }
    }

    public record Exponent(Expr value, int power) implements Expr {

        @Override
        public double evaluate() {
            return Math.pow(value.evaluate(), power);
        }
    }

    public record Addition(Expr a, Expr b) implements Expr {

        @Override
        public double evaluate() {
            return a.evaluate() + b.evaluate();
        }
    }

    public record Multiplication(Expr a, Expr b) implements Expr {

        @Override
        public double evaluate() {
            return a.evaluate() * b.evaluate();
        }
    }
}
