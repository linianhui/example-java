package mysqlexample.util;

import java.util.Arrays;
import java.util.stream.Collectors;

public class LogUtil {
    public static void logArgs(Object... args) {
        logCaller("args", args);
    }

    public static void logReturn(Object... args) {
        logCaller("return", args);
    }

    private static void logCaller(String type, Object... args) {
        final Thread thread = Thread.currentThread();
        final StackTraceElement caller = thread.getStackTrace()[3];
        final String argsString = Arrays.stream(args)
            .map(Object::toString)
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
