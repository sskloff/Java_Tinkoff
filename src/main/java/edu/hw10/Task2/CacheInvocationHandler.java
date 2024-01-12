package edu.hw10.Task2;

import edu.hw10.Task2.Annotations.Cache;
import java.io.IOException;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class CacheInvocationHandler implements InvocationHandler {

    private final Object target;
    private final Map<String, Object> cache = new HashMap<>();

    public CacheInvocationHandler(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if (method.isAnnotationPresent(Cache.class)) {
            String key = method.getName() + args[0];
            if (cache.containsKey(key)) {
                return cache.get(key);
            }
            Object result = method.invoke(target, args);
            cache.put(key, result);
            if (method.getAnnotation(Cache.class).persist()) {
                saveResultToFile(key, method, args, result);
            }
            return result;
        }
        return method.invoke(target, args);
    }

    private void saveResultToFile(String key, Method method, Object[] args, Object result) throws
        IOException {
        Path path = Path.of(key + ".cache");
        Files.createFile(path);
        String methodName = method.toString();
        Files.writeString(
            path,
            "Method=" + methodName.substring(methodName.lastIndexOf(".") + 1) + " "
                + "args=" + Arrays.toString(args) + " "
                + "result=" + result.toString() + "\n",
            StandardOpenOption.APPEND
        );
    }
}
