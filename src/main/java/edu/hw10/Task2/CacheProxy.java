package edu.hw10.Task2;

import java.lang.reflect.Proxy;

public class CacheProxy {

    private CacheProxy() {
    }

    @SuppressWarnings("unchecked")
    public static <T> T create(T target, Class<? extends T> targetClass) {
        return (T) Proxy.newProxyInstance(
            targetClass.getClassLoader(),
            targetClass.getInterfaces(),
            new CacheInvocationHandler(target)
        );
    }
}
