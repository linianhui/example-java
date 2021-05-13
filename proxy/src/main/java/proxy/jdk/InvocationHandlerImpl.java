package proxy.jdk;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

import proxy.util.LogUtil;

public class InvocationHandlerImpl implements InvocationHandler {

    private final Object target;

    public InvocationHandlerImpl(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        LogUtil.logArgs(proxy.getClass(), method, args);
        Object result = method.invoke(target, args);
        LogUtil.logReturn(result);
        return result;
    }
}
