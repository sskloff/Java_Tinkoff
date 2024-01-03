package edu.project5;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.infra.Blackhole;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;
import org.openjdk.jmh.runner.options.TimeValue;
import java.lang.invoke.CallSite;
import java.lang.invoke.LambdaMetafactory;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Objects;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

@State(Scope.Thread)
public class ReflectionBenchmark {

    private Student student;
    private Method method;
    private MethodHandle methodHandle;
    private StudentGetter getter;

    public static void main(String[] args) throws RunnerException {
        Options options = new OptionsBuilder()
            .include(ReflectionBenchmark.class.getSimpleName())
            .shouldFailOnError(true)
            .shouldDoGC(true)
            .mode(Mode.AverageTime)
            .timeUnit(TimeUnit.NANOSECONDS)
            .forks(1)
            .warmupForks(1)
            .warmupIterations(1)
            .warmupTime(TimeValue.seconds(5))
            .measurementIterations(1)
            .measurementTime(TimeValue.seconds(5))
            .build();

        new Runner(options).run();
    }

    @Setup
    public void setup() throws Throwable {
        student = new Student("Alexander", "Biryukov");
        method = Student.class.getMethod("name");
        MethodHandles.Lookup lookup = MethodHandles.lookup();
        methodHandle = lookup.findVirtual(
            Student.class, "name", MethodType.methodType(String.class)
        );
        CallSite callsite = LambdaMetafactory.metafactory(
            lookup, "get", MethodType.methodType(StudentGetter.class),
            MethodType.methodType(String.class, Student.class),
            methodHandle, MethodType.methodType(String.class, Student.class)
        );
        getter = (StudentGetter) callsite.getTarget().invokeExact();
    }

    @Benchmark
    public void directAccess(Blackhole bh) {
        bh.consume(student.name());
    }

    @Benchmark
    public void reflection(Blackhole bh) throws InvocationTargetException, IllegalAccessException {
        bh.consume(method.invoke(student));
    }

    @Benchmark
    public void methodHandles(Blackhole bh) throws Throwable {
        bh.consume(methodHandle.invoke(student));
    }

    @Benchmark
    public void lambdaMetafactory(Blackhole bh) {
        bh.consume(getter.get(student));
    }
}
