package example.core.native1;

import example.core.util.UnsafeUtil;
import sun.misc.Unsafe;

public class UnsafeExample {

    private static final Unsafe UNSAFE = UnsafeUtil.getUnsafe();

    private static final Class<UnsafeExample> CLASS = UnsafeExample.class;

    public static long instanceValueFieldOffset;

    public static long instanceVolatileValueFieldOffset;

    public static long staticValueFieldOffset;

    private static int staticValue;

    static {
        try {
            instanceValueFieldOffset = UNSAFE.objectFieldOffset(CLASS.getDeclaredField("instanceValue"));
            instanceVolatileValueFieldOffset = UNSAFE.objectFieldOffset(CLASS.getDeclaredField("instanceVolatileValue"));
            staticValueFieldOffset = UNSAFE.staticFieldOffset(CLASS.getDeclaredField("staticValue"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private volatile int instanceVolatileValue;

    private int instanceValue;

    public static int getStaticValue() {
        return staticValue;
    }

    public static void setStaticValue(int staticValue) {
        UnsafeExample.staticValue = staticValue;
    }

    public static int getStaticValueByUnsafe() {
        return UNSAFE.getInt(CLASS, staticValueFieldOffset);
    }

    public static void setStaticValueByUnsafe(int staticValue) {
        UNSAFE.putInt(CLASS, staticValueFieldOffset, staticValue);
    }

    public int getInstanceVolatileValue() {
        return instanceVolatileValue;
    }

    public void setInstanceVolatileValue(int instanceVolatileValue) {
        this.instanceVolatileValue = instanceVolatileValue;
    }

    public int getInstanceValue() {
        return instanceValue;
    }

    public void setInstanceValue(int instanceValue) {
        this.instanceValue = instanceValue;
    }

    public int getInstanceValueByUnsafe() {
        return UNSAFE.getInt(this, instanceValueFieldOffset);
    }

    public void setInstanceValueByUnsafe(int instanceValue) {
        UNSAFE.putInt(this, instanceValueFieldOffset, instanceValue);
    }

    public int getInstanceVolatileValueByUnsafe() {
        return UNSAFE.getIntVolatile(this, instanceVolatileValueFieldOffset);
    }

    public void setInstanceVolatileValueByUnsafe(int instanceVolatileValue) {
        UNSAFE.putIntVolatile(this, instanceVolatileValueFieldOffset, instanceVolatileValue);
    }

}
