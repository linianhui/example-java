package example;

import java.util.function.Supplier;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class AtomicIntegerSynchronizedExampleTest {

  @Test
  void test_synchronized_this() throws InterruptedException {
    final AtomicIntegerSynchronizedExample example = new AtomicIntegerSynchronizedExample();

    Thread thread1 = new Thread(buildRunnable(1000, example::incrementAndGetSynchronizedThis));
    Thread thread2 = new Thread(buildRunnable(1000, example::incrementAndGetSynchronizedThis));
    thread1.start();
    thread2.start();
    thread1.join();
    thread2.join();

    Assertions.assertEquals(2000, example.getCount());
  }

  @Test
  void test_synchronized_class() throws InterruptedException {
    Thread thread1 = new Thread(buildRunnable(
        1000, new AtomicIntegerSynchronizedExample()::incrementAndGetSynchronizedClass)
    );
    Thread thread2 = new Thread(buildRunnable(
        1000, new AtomicIntegerSynchronizedExample()::incrementAndGetSynchronizedClass)
    );
    thread1.start();
    thread2.start();
    thread1.join();
    thread2.join();

    Assertions.assertEquals(2000, new AtomicIntegerSynchronizedExample().getCountStatic());
  }


  private Runnable buildRunnable(
      final int count,
      final Supplier<Integer> example
  ) {
    return new Runnable() {
      public void run() {
        for (int i = 1; i <= count; i++) {
          example.get();
        }
      }
    };
  }
}
