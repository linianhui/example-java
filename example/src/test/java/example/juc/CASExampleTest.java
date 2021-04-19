package example.juc;

import example.Action;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class CASExampleTest {

  @Test
  void test_cas() throws InterruptedException {
    final CASExample casExample1 = new CASExample();
    final CASExample casExample2 = new CASExample();
    casExample2.incrementAndGet();

    Thread thread1 = new Thread(Action.loop(1000, casExample1::incrementAndGet));
    Thread thread2 = new Thread(Action.loop(1000, casExample1::incrementAndGet));
    thread1.start();
    thread2.start();
    thread1.join();
    thread2.join();

    Assertions.assertEquals(2000, casExample1.getValue());

    Assertions.assertEquals(1, casExample2.getValue());
  }
}
