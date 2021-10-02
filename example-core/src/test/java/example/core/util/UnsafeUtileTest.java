package example.core.util;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class UnsafeUtilTest {

    @Test
    void test_getUnsafe_same() {
        Assertions.assertNotNull(UnsafeUtil.getUnsafe());
        Assertions.assertSame(UnsafeUtil.getUnsafe(), UnsafeUtil.getUnsafe());
    }

}
