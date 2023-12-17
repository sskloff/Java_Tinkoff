package edu.hw11;

import net.bytebuddy.ByteBuddy;
import net.bytebuddy.description.method.MethodDescription;
import net.bytebuddy.dynamic.loading.ClassLoadingStrategy;
import net.bytebuddy.implementation.Implementation;
import net.bytebuddy.implementation.bytecode.ByteCodeAppender;
import net.bytebuddy.jar.asm.MethodVisitor;
import net.bytebuddy.jar.asm.Opcodes;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Task3Test {

    public static class FibonacciByteCodeAppender implements ByteCodeAppender {

        @Override
        public Size apply(
            MethodVisitor methodVisitor,
            Implementation.Context context,
            MethodDescription methodDescription
        ) {
            //Вот тут я задушился задушнился задушнимился
            methodVisitor.visitCode();
            methodVisitor.visitVarInsn(Opcodes.ILOAD, 0);
            methodVisitor.visitInsn(Opcodes.I2L);
            methodVisitor.visitInsn(Opcodes.LRETURN);
            return new Size(2, 1);
        }
    }

    @Test
    @DisplayName("Создание класса и метода fib с помощью ByteBuddy")
    public void whenFibInvokeThenEqualsExpected()
        throws NoSuchMethodException, InvocationTargetException, IllegalAccessException, InstantiationException {
        Class<?> loadedClass = new ByteBuddy()
            .subclass(Object.class)
            .name("Fibonacci")
            .modifiers(Opcodes.ACC_PUBLIC)
            .defineMethod("fib", long.class, Opcodes.ACC_PUBLIC | Opcodes.ACC_STATIC)
            .withParameter(int.class, "n")
            .intercept(new Implementation.Simple(new FibonacciByteCodeAppender()))
            .make()
            .load(getClass().getClassLoader(), ClassLoadingStrategy.Default.INJECTION)
            .getLoaded();

        Object fibInstance = loadedClass.getDeclaredConstructor().newInstance();
        Method fibMethod = loadedClass.getDeclaredMethod("fib", int.class);
        long result = (long) fibMethod.invoke(fibInstance, 10);
        assertEquals(10L, result);
    }
}
