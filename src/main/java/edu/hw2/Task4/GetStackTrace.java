package edu.hw2.Task4;

public class GetStackTrace {

    private GetStackTrace() {
    }

    public static CallingInfo callingInfo() {
        StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
        if (stackTraceElements.length > 2) {
            String className = stackTraceElements[2].getClassName();
            String methodName = stackTraceElements[2].getMethodName();
            return new CallingInfo(className, methodName);
        } else {
            return null;
        }
    }
}
