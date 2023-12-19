package edu.hw11;

import net.bytebuddy.ByteBuddy;
import net.bytebuddy.description.method.MethodDescription;
import net.bytebuddy.dynamic.loading.ClassLoadingStrategy;
import net.bytebuddy.implementation.Implementation;
import net.bytebuddy.implementation.bytecode.ByteCodeAppender;
import net.bytebuddy.jar.asm.Label;
import net.bytebuddy.jar.asm.MethodVisitor;
import net.bytebuddy.jar.asm.Opcodes;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Task3Test {

    public static class FibonacciByteCodeAppender implements ByteCodeAppender {

        @Override
        public @NotNull Size apply(
            MethodVisitor methodVisitor,
            Implementation.@NotNull Context context,
            @NotNull MethodDescription methodDescription
        ) {
            Label label1 = new Label();
            Label label2 = new Label();
            Label label3 = new Label();
            methodVisitor.visitCode();
            methodVisitor.visitVarInsn(Opcodes.ILOAD, 0);
            methodVisitor.visitJumpInsn(Opcodes.IFNE, label1);
            methodVisitor.visitFrame(Opcodes.F_SAME, 0, null, 0, null);
            methodVisitor.visitVarInsn(Opcodes.ILOAD, 0);
            methodVisitor.visitInsn(Opcodes.I2L);
            methodVisitor.visitInsn(Opcodes.LRETURN);
            methodVisitor.visitLabel(label1);
            methodVisitor.visitFrame(Opcodes.F_SAME, 0, null, 0, null);
            methodVisitor.visitInsn(Opcodes.ICONST_1);
            methodVisitor.visitVarInsn(Opcodes.ISTORE, 1);
            methodVisitor.visitInsn(Opcodes.ICONST_1);
            methodVisitor.visitVarInsn(Opcodes.ISTORE, 2);
            methodVisitor.visitInsn(Opcodes.ICONST_2);
            methodVisitor.visitVarInsn(Opcodes.ISTORE, 3);
            methodVisitor.visitLabel(label2);
            methodVisitor.visitFrame(Opcodes.F_APPEND, 3,
                new Object[] {Opcodes.INTEGER, Opcodes.INTEGER, Opcodes.INTEGER},
                0, null);
            methodVisitor.visitVarInsn(Opcodes.ILOAD, 3);
            methodVisitor.visitVarInsn(Opcodes.ILOAD, 0);
            methodVisitor.visitJumpInsn(Opcodes.IF_ICMPGE, label3);
            methodVisitor.visitVarInsn(Opcodes.ILOAD, 1);
            methodVisitor.visitVarInsn(Opcodes.ILOAD, 2);
            methodVisitor.visitInsn(Opcodes.IADD);
            methodVisitor.visitVarInsn(Opcodes.ISTORE, 2);
            methodVisitor.visitVarInsn(Opcodes.ILOAD, 2);
            methodVisitor.visitVarInsn(Opcodes.ILOAD, 1);
            methodVisitor.visitInsn(Opcodes.ISUB);
            methodVisitor.visitVarInsn(Opcodes.ISTORE, 1);
            methodVisitor.visitIincInsn(3, 1);
            methodVisitor.visitVarInsn(Opcodes.ILOAD, 3);
            methodVisitor.visitVarInsn(Opcodes.ILOAD, 0);
            methodVisitor.visitJumpInsn(Opcodes.IF_ICMPLT, label2);
            methodVisitor.visitLabel(label3);
            methodVisitor.visitFrame(Opcodes.F_SAME, 0, null, 0, null);
            methodVisitor.visitVarInsn(Opcodes.ILOAD, 2);
            methodVisitor.visitInsn(Opcodes.I2L);
            methodVisitor.visitInsn(Opcodes.LRETURN);
            methodVisitor.visitMaxs(4, 4);
            methodVisitor.visitEnd();
            return new Size(4, 4);
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
        assertEquals(55L, result);
    }
}
