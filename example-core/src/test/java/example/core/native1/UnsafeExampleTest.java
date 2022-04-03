package example.core.native1;

import example.core.util.UnsafeUtil;
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
    void test_pack_to_Thread_State_WAITING() throws InterruptedException {
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                UnsafeUtil.getUnsafe().park(false, 0);
                while (true) {

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

    @Test
    void test_pack_to_Thread_State_TIMED_WAITING() throws InterruptedException {
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                UnsafeUtil.getUnsafe().park(false, 10 * 1000 * 1000 * 1000);
                while (true) {

                }
            }
        });

        t1.start();
        Thread.sleep(100L);
        Assertions.assertEquals(Thread.State.TIMED_WAITING, t1.getState());

        UnsafeUtil.getUnsafe().unpark(t1);
        Thread.sleep(100L);
        Assertions.assertEquals(Thread.State.RUNNABLE, t1.getState());
    }

    @Test
    void test_filed_offset() {
        Assertions.assertEquals(12, UnsafeExample.instanceVolatileValueFieldOffset);
        Assertions.assertEquals(16, UnsafeExample.instanceValueFieldOffset);
        Assertions.assertEquals(136, UnsafeExample.staticValueFieldOffset);
    }

    @Test
    void test_get_put() {
        UnsafeExample.setStaticValue(123);
        Assertions.assertEquals(123, UnsafeExample.getStaticValueByUnsafe());
        Assertions.assertEquals(123, UnsafeExample.getStaticValue());
        UnsafeExample.setStaticValueByUnsafe(1234);
        Assertions.assertEquals(1234, UnsafeExample.getStaticValue());
        Assertions.assertEquals(1234, UnsafeExample.getStaticValueByUnsafe());

        UnsafeExample unsafeExample = new UnsafeExample();

        unsafeExample.setInstanceValue(123);
        Assertions.assertEquals(123, unsafeExample.getInstanceValueByUnsafe());
        Assertions.assertEquals(123, unsafeExample.getInstanceValue());
        unsafeExample.setInstanceValueByUnsafe(1234);
        Assertions.assertEquals(1234, unsafeExample.getInstanceValue());
        Assertions.assertEquals(1234, unsafeExample.getInstanceValueByUnsafe());

        unsafeExample.setInstanceVolatileValue(123);
        Assertions.assertEquals(123, unsafeExample.getInstanceVolatileValue());
        Assertions.assertEquals(123, unsafeExample.getInstanceVolatileValueByUnsafe());
        unsafeExample.setInstanceVolatileValueByUnsafe(1234);
        Assertions.assertEquals(1234, unsafeExample.getInstanceVolatileValue());
        Assertions.assertEquals(1234, unsafeExample.getInstanceVolatileValueByUnsafe());
    }

}
