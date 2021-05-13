package proxy.cglib;

import java.lang.reflect.Method;

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;
import proxy.util.LogUtil;

public class MethodInterceptorImpl implements MethodInterceptor {
    private final Object target;

    public MethodInterceptorImpl(Object target) {
        this.target = target;
    }

    @Override
    public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
        LogUtil.logArgs(obj.getClass(), method, args, proxy);
        Object result = method.invoke(target, args);
        LogUtil.logReturn(result);
        return result;
    }
}
