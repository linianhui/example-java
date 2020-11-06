package example.native1;

import example.util.UnsafeUtil;
import sun.misc.Unsafe;

public class UnsafeExample {

    private static final Unsafe unsafe;

    static {
        unsafe = UnsafeUtil.getUnsafe();
    }
}
