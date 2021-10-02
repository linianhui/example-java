package example.proxy.cglib;

import java.lang.reflect.Method;

import example.proxy.util.LogUtil;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

public class CGLibProxy {
    public static <T> T proxy(Class<T> clazz) {
        final Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(clazz);
        enhancer.setCallback(new DefaultMethodInterceptor());
        return (T) enhancer.create();
    }

    static class DefaultMethodInterceptor implements MethodInterceptor {
        @Override
        public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
            LogUtil.logArgs(obj.getClass(), method, args, proxy);
            Object result = proxy.invokeSuper(obj, args);
            LogUtil.logReturn(result);
            return result;
        }
    }
}
