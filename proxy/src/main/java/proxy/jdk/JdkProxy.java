package proxy.jdk;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import proxy.TestInterface;
import proxy.util.LogUtil;

public class JdkProxy {

    /**
     * {@link Proxy#newProxyInstance(ClassLoader, Class[], InvocationHandler)}生成代理类
     * 查看生成的代理类：arthas : jad com.sun.proxy.$Proxy0
     *
     **/
    public static TestInterface proxy(Object impl) {
        final InvocationHandler handler = new DefaultInvocationHandler(impl);
        final Class<?> implClass = impl.getClass();
        final ClassLoader loader = implClass.getClassLoader();
        final Class<?>[] interfaces = implClass.getInterfaces();
        return (TestInterface) Proxy.newProxyInstance(loader, interfaces, handler);
    }

    static class DefaultInvocationHandler implements  InvocationHandler {
        private final Object target;

        public DefaultInvocationHandler(Object target) {
            this.target = target;
        }

        /**
         * method.invoke调用次数大于15次就生成(性能提升)
         * 查看生成的代理类：arthas : jad jdk.internal.reflect.GeneratedMethodAccessor1
         * {@see NativeMethodAccessorImpl#invoke}
         **/
        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            LogUtil.logArgs(proxy.getClass(), method, args);
            Object result = method.invoke(target, args);
            LogUtil.logReturn(result);
            return result;
        }
    }
}
