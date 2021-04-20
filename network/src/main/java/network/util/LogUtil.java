package network.util;

public class LogUtil {
    public static void logCaller() {
        final Thread thread = Thread.currentThread();
        long pid = thread.getId();
        final StackTraceElement caller = thread.getStackTrace()[2];
        System.out.printf(
                "\n[CALL] pid=%d %s %s",
                pid,
                caller.getClassName(),
                caller.getMethodName()
        );
    }
}
