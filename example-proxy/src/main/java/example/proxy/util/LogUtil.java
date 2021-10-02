package example.proxy.util;

import java.util.Arrays;
import java.util.stream.Collectors;

public class LogUtil {
    public static void logArgs(Object... args) {
        logCaller("args", args);
    }

    public static void logReturn(Object... args) {
        logCaller("return", args);
    }

    public static void logProxyClass(Object proxyInstance) {
        if (proxyInstance==null) {
            return;
        }
        final Class<?> proxyClass = proxyInstance.getClass();
        System.out.printf("\n[Proxy] %s\n", proxyClass.getName());

        if (ProxyUtil.supperClassIsProxy(proxyClass)) {
            final Class<?> invocationHandlerClass = ProxyUtil.getInvocationHandlerClass(proxyInstance);
            System.out.printf("\n[InvocationHandler] %s\n", invocationHandlerClass.getName());
        }
    }

    private static void logCaller(String type, Object... args) {
        final Thread thread = Thread.currentThread();
        final StackTraceElement caller = thread.getStackTrace()[3];
        final String argsString = Arrays.stream(args)
            .map(x -> x==null ? "null":x.toString())
            .collect(Collectors.joining(","));

        System.out.printf(
            "\n[CALL] pid=%d threadName=%s %s#%s %s=[%s]\n",
            thread.getId(),
            thread.getName(),
            caller.getClassName(),
            caller.getMethodName(),
            type,
            argsString
        );
    }
}
