package example.native1;

import example.util.UnsafeUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import sun.misc.Unsafe;

class UnsafeExampleTest {

    @Test
    void when_call_Unsafe_getUnsafe_should_get_SecurityException() {
        Assertions.assertThrows(
            SecurityException.class,
            () -> Assertions.assertNotNull(Unsafe.getUnsafe())
        );
    }

    @Test
    void test() throws InterruptedException {
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                UnsafeUtil.getUnsafe().park(false, 0);
                while (true){

                }
            }
        });

        t1.start();
        Thread.sleep(100L);
        Assertions.assertEquals(Thread.State.WAITING, t1.getState());

        UnsafeUtil.getUnsafe().unpark(t1);
        Thread.sleep(100L);
        Assertions.assertEquals(Thread.State.RUNNABLE, t1.getState());
    }

}
