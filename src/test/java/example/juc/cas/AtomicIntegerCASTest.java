package example.juc.cas;

import example.Action;
import example.juc.cas.AtomicIntegerCAS;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class AtomicIntegerCASTest {

  @Test
  void test_cas() throws InterruptedException {
    final AtomicIntegerCAS atomicIntegerCAS1 = new AtomicIntegerCAS();
    final AtomicIntegerCAS atomicIntegerCAS2 = new AtomicIntegerCAS();
    atomicIntegerCAS2.incrementAndGet();

    Thread thread1 = new Thread(Action.loop(1000, atomicIntegerCAS1::incrementAndGet));
    Thread thread2 = new Thread(Action.loop(1000, atomicIntegerCAS1::incrementAndGet));
    thread1.start();
    thread2.start();
    thread1.join();
    thread2.join();

    Assertions.assertEquals(2000, atomicIntegerCAS1.getValue());

    Assertions.assertEquals(1, atomicIntegerCAS2.getValue());
  }
}
