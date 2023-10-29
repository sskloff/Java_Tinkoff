package edu.hw2.Task2;

public class Square extends Rectangle {

    public Square(int side) {
        super(side, side);
    }

    /* Объединил сеттеры в один метод,
    иначе после использования одного из сеттеров для квадрата
    всегда бы возвращало прямоугольник, а хочется
    иметь возможность хоть иногда получать квадрат.
    Хотя в контексте этого задания вообще логично отказаться от наследования,
    но условие вынуждает сохранять типизацию.
     */
    @Override
    public Rectangle setDimensions(int width, int height) {
        if (width == height) {
            return new Square(width);
        } else {
            return new Rectangle(width, height);
        }
    }
}
