package example.juc;

import example.UnsafeUtil;
import sun.misc.Unsafe;

public class CASExample {
  private static Unsafe unsafe;

  // value字段的偏移位置。
  private static long valueFieldOffset;

  static {
    try {
      unsafe = UnsafeUtil.getUnsafe();
      valueFieldOffset = unsafe.objectFieldOffset(
          CASExample.class.getDeclaredField("value")
      );
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  private volatile int value;

  public final int incrementAndGet() {
    int tempValue;
    do {
      tempValue = unsafe.getIntVolatile(this, valueFieldOffset);
    } while (!unsafe.compareAndSwapInt(this, valueFieldOffset, tempValue, tempValue + 1));
    return tempValue + 1;
  }

  public int getValue() {
    return value;
  }
}
