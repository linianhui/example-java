package example.core.util;

import java.lang.reflect.Field;

import sun.misc.Unsafe;

public class UnsafeUtil {

    public static Unsafe getUnsafe() {
        try {
            Field theUnsafeField = Unsafe.class.getDeclaredField("theUnsafe");
            theUnsafeField.setAccessible(true);
            return (Unsafe) theUnsafeField.get(null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
