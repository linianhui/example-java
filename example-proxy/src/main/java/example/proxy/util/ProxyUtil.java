package example.proxy.util;

import java.lang.reflect.Field;
import java.lang.reflect.Proxy;

public class ProxyUtil {
    private static Field h;

    static {
        try {
            h = Proxy.class.getDeclaredField("h");
            h.setAccessible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static boolean supperClassIsProxy(final Class<?> clazz) {
        Class<?> temp = clazz;
        while (true) {
            if (temp==null) {
                return false;
            }
            if (temp.equals(Proxy.class)) {
                return true;
            }
            temp = temp.getSuperclass();
        }
    }

    public static Class<?> getInvocationHandlerClass(final Object proxy) {
        if (proxy==null) {
            return null;
        }
        try {
            return h.get(proxy).getClass();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }
}
