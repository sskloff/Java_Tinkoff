package edu.hw10.Task1;

import edu.hw10.Task1.Annotations.Max;
import edu.hw10.Task1.Annotations.Min;
import edu.hw10.Task1.Annotations.NotNull;
import edu.hw10.Task1.Generators.IntegerGenerator;
import edu.hw10.Task1.Generators.StringGenerator;
import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class RandomObjectGenerator {
    private static final Random RANDOM = new Random();
    private static final float NULL_FREQUENCY = 0.1f;
    private static final List<Class<?>> SUPPORTED_PARAMETER_TYPES = List.of(
        int.class, Integer.class, String.class
    );

    public Object nextObject(Class<?> className) throws
        IllegalAccessException, InvocationTargetException, InstantiationException {
        Constructor<?>[] constructors = className.getConstructors();
        ArrayList<Constructor<?>> supportedConstructors = new ArrayList<>();
        for (Constructor<?> constructor : constructors) {
            Class<?>[] types = constructor.getParameterTypes();
            if (Arrays.stream(types).allMatch(SUPPORTED_PARAMETER_TYPES::contains)) {
                supportedConstructors.add(constructor);
            }
        }
        if (supportedConstructors.isEmpty()) {
            throw new RuntimeException();
        }
        Constructor<?> constructor = supportedConstructors.get(RANDOM.nextInt(constructors.length));
        return constructor.newInstance(generateRandomParameters(constructor.getParameters()));
    }

    public Object nextObject(Class<?> className, String factoryMethod) throws
        IllegalAccessException, InvocationTargetException {
        Method method = null;
        for (Method m : className.getMethods()) {
            Class<?>[] types = m.getParameterTypes();
            if (m.getName().equals(factoryMethod)
                && Arrays.stream(types).allMatch(SUPPORTED_PARAMETER_TYPES::contains)
                && Modifier.isStatic(m.getModifiers())
            ) {
                method = m;
                break;
            }
        }
        if (method == null) {
            throw new RuntimeException();
        }
        return method.invoke(className, generateRandomParameters(method.getParameters()));
    }

    @SuppressWarnings("InnerAssignment")
    private Object[] generateRandomParameters(Parameter[] parameters) {
        boolean canBeNull = true;
        List<Object> generatedValues = new ArrayList<>();
        for (Parameter parameter : parameters) {
            Class<?> type = parameter.getType();
            int max = Integer.MAX_VALUE;
            int min = Integer.MIN_VALUE;
            for (Annotation annotation : parameter.getAnnotations()) {
                switch (annotation) {
                    case Max annotationMax -> max = annotationMax.value();
                    case Min annotationMin -> min = annotationMin.value();
                    case NotNull notNull -> canBeNull = false;
                    default -> throw new RuntimeException();
                }
            }

            switch (type.getName()) {
                case "int", "java.lang.Integer" ->
                    generatedValues.add(new IntegerGenerator(min, max).generate());
                case "java.lang.String" -> generatedValues.add(new StringGenerator().generate());
                default -> throw new IllegalArgumentException();
            }

            if (canBeNull
                && RANDOM.nextFloat(0, 1) < NULL_FREQUENCY
                && !(generatedValues.getLast() instanceof Number)
            ) {
                generatedValues.removeLast();
                generatedValues.add(null);
            }
        }
        return generatedValues.toArray();
    }
}
