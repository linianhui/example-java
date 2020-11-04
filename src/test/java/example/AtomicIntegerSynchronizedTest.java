package example;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class AtomicIntegerSynchronizedTest {

  @Test
  void test_synchronized_this() throws InterruptedException {
    final AtomicIntegerSynchronized example = new AtomicIntegerSynchronized();

    Thread thread1 = new Thread(Action.loop(1000, example::incrementAndGetSynchronizedThis));
    Thread thread2 = new Thread(Action.loop(1000, example::incrementAndGetSynchronizedThis));
    thread1.start();
    thread2.start();
    thread1.join();
    thread2.join();

    Assertions.assertEquals(2000, example.getCount());
  }

  @Test
  void test_synchronized_class() throws InterruptedException {
    Thread thread1 = new Thread(Action.loop(
        1000, new AtomicIntegerSynchronized()::incrementAndGetSynchronizedClass
    ));
    Thread thread2 = new Thread(Action.loop(
        1000, new AtomicIntegerSynchronized()::incrementAndGetSynchronizedClass
    ));
    thread1.start();
    thread2.start();
    thread1.join();
    thread2.join();

    Assertions.assertEquals(2000, new AtomicIntegerSynchronized().getCountStatic());
  }

}
