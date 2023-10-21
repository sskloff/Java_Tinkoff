package edu.hw2;

import edu.hw2.Task2.Rectangle;
import edu.hw2.Task2.Square;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class Task2Test {

    @Test
    @DisplayName("Рассчет площади")
    void rectangleArea() {
        //given
        Rectangle rectangle = new Rectangle(2, 4);

        //then
        assertThat(rectangle.setDimensions(3, 4).area()).isEqualTo(12.0);
    }

    @Test
    @DisplayName("Квадрат является квадратом")
    void isSquare() {
        //given
        Square square = new Square(4);

        //then
        assertThat(square).isInstanceOf(Square.class);
    }

    @Test
    @DisplayName("Квадрат остается квадратом после изменения параметров")
    void isSquareAfterSetDimensions() {
        //given
        Square square = new Square(4);

        //then
        assertThat(square.setDimensions(3, 3)).isInstanceOf(Square.class);
    }

    @Test
    @DisplayName("Квадрат становится прямоугольником после изменения параметров")
    void isRectangleAfterSetDimensions() {
        //given
        Square square = new Square(4);

        //then
        assertThat(square.setDimensions(3, 5)).isInstanceOf(Rectangle.class);
    }
}
