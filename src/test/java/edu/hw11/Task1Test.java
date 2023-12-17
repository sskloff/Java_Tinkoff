package edu.hw11;

import net.bytebuddy.ByteBuddy;
import net.bytebuddy.implementation.FixedValue;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.lang.reflect.InvocationTargetException;
import static net.bytebuddy.matcher.ElementMatchers.named;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Task1Test {

    @Test
    @DisplayName("Создание класса, метод toString которого выводит заготовленную строку")
    void testToStringMethod()
        throws NoSuchMethodException, InvocationTargetException,
        InstantiationException, IllegalAccessException {
        Class<?> dynamicType = new ByteBuddy()
            .subclass(Object.class)
            .method(named("toString"))
            .intercept(FixedValue.value("Hello, ByteBuddy!"))
            .make()
            .load(getClass().getClassLoader())
            .getLoaded();

        Object instance = dynamicType.getDeclaredConstructor().newInstance();
        assertEquals("Hello, ByteBuddy!", instance.toString());
    }
}
