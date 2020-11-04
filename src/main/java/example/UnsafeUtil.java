package example;

import java.lang.reflect.Field;
import sun.misc.Unsafe;

public class UnsafeUtil {
  public static Unsafe getUnsafe() throws Exception {
    Field theUnsafeInstance = Unsafe.class.getDeclaredField("theUnsafe");
    theUnsafeInstance.setAccessible(true);
    return (Unsafe) theUnsafeInstance.get(Unsafe.class);
  }
}
