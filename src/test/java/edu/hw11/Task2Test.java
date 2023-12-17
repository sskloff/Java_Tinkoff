package edu.hw11;

import net.bytebuddy.ByteBuddy;
import net.bytebuddy.agent.ByteBuddyAgent;
import net.bytebuddy.dynamic.loading.ClassReloadingStrategy;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Task2Test {

    static class ArithmeticUtils {
        public int sum(int a, int b) {
            return a + b;
        }
    }

    static class ArithmeticUtilsReload {
        public int sum(int a, int b) {
            return a * b;
        }
    }

    @Test
    @DisplayName("Изменение поведения существующего класса на лету")
    void whenChangeSumToThen6() {
        ArithmeticUtils arithmeticUtils = new ArithmeticUtils();
        ByteBuddyAgent.install();
        new ByteBuddy()
            .redefine(ArithmeticUtilsReload.class)
            .name(ArithmeticUtils.class.getName())
            .make()
            .load(ArithmeticUtils.class.getClassLoader(),
                ClassReloadingStrategy.fromInstalledAgent()
            );
        int result = arithmeticUtils.sum(2, 3);
        assertEquals(result, 6);
    }
}
