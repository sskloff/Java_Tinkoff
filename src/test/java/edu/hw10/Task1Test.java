package edu.hw10;

import edu.hw10.Models.TestClass;
import edu.hw10.Models.TestRecord;
import edu.hw10.Task1.RandomObjectGenerator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.lang.reflect.InvocationTargetException;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Task1Test {

    @Test
    @DisplayName("Создание экземпляра класса по конструктору")
    void objectByConstructorMeetsConditionsFromAnnotation() throws
        InvocationTargetException, IllegalAccessException, InstantiationException {
        //given
        RandomObjectGenerator randomObjectGenerator = new RandomObjectGenerator();

        //when
        TestClass object = (TestClass) randomObjectGenerator.nextObject(TestClass.class);

        //then
        assertTrue(object.value >= -100);
        assertTrue(object.value <= 100);
        assertNotNull(object.str);
    }

    @Test
    @DisplayName("Создание экземпляра рекорда")
    void recordMeetsConditionsFromAnnotation() throws
        InvocationTargetException, IllegalAccessException, InstantiationException {
        //given
        RandomObjectGenerator randomObjectGenerator = new RandomObjectGenerator();

        //when
        TestRecord object = (TestRecord) randomObjectGenerator.nextObject(TestRecord.class);

        //then
        assertTrue(object.value() >= -100);
        assertTrue(object.value() <= 100);
        assertNotNull(object.str());
    }

    @Test
    @DisplayName("Создание экземпляра класса через фабричный метод")
    void objectByFabricMethodMeetsConditionsFromAnnotation() throws
        InvocationTargetException, IllegalAccessException {
        //given
        RandomObjectGenerator randomObjectGenerator = new RandomObjectGenerator();

        //when
        TestClass object =
            (TestClass) randomObjectGenerator.nextObject(TestClass.class, "create");

        //then
        assertTrue(object.value >= -100);
        assertTrue(object.value <= 100);
        assertNotNull(object.str);
    }
}
