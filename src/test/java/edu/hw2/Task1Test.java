package edu.hw2;

import edu.hw2.Task1.Expr;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Task1Test {

    @Test
    @DisplayName("Константа")
    void testConstant() {
        Expr two = new Expr.Constant(2);
        assertEquals(2.0, two.evaluate(), 0.001);
    }

    @Test
    @DisplayName("Противоположное число")
    void testNegate() {
        Expr four = new Expr.Constant(4);
        Expr negateFour = new Expr.Negate(four);
        assertEquals(-4.0, negateFour.evaluate(), 0.001);
    }

    @Test
    @DisplayName("Возведение в степень")
    void testExponent() {
        Expr four = new Expr.Constant(4);
        Expr expFour = new Expr.Exponent(four, 2);
        assertEquals(16.0, expFour.evaluate(), 0.001);
    }

    @Test
    @DisplayName("Сложение")
    void testAddition() {
        Expr four = new Expr.Constant(4);
        Expr two = new Expr.Constant(2);
        Expr sum = new Expr.Addition(four, two);
        assertEquals(6.0, sum.evaluate(), 0.001);
    }

    @Test
    @DisplayName("Произведение")
    void testMultiplication() {
        Expr four = new Expr.Constant(4);
        Expr two = new Expr.Constant(2);
        Expr comp = new Expr.Multiplication(four, two);
        assertEquals(8.0, comp.evaluate(), 0.001);
    }
}
