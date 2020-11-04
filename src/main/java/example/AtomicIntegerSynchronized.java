package example;

public class AtomicIntegerSynchronized {
  private int count;
  private static int countStatic;

  public int getCount() {
    return count;
  }

  public int getCountStatic(){
    return countStatic;
  }

  public int incrementAndGetSynchronizedThis() {
    synchronized (this) {
      return ++count;
    }
  }

  public int incrementAndGetSynchronizedClass() {
    synchronized (AtomicIntegerSynchronized.class) {
      return ++countStatic;
    }
  }
}
