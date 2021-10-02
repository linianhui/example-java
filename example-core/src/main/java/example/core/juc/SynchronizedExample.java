package example.core.juc;

public class SynchronizedExample {
    private static int countStatic;
    private int count;

    public int getCount() {
        return count;
    }

    public int getCountStatic() {
        return countStatic;
    }

    public int incrementAndGetSynchronizedThis() {
        synchronized (this) {
            return ++count;
        }
    }

    public int incrementAndGetSynchronizedClass() {
        synchronized (SynchronizedExample.class) {
            return ++countStatic;
        }
    }
}
