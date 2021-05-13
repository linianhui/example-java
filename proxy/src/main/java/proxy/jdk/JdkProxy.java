package proxy.jdk;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

import proxy.TestInterface;
import proxy.TestInterfaceImpl;

public class JdkProxy {
    /**
     * arthas : jad com.sun.proxy.$Proxy0
     **/
    public static TestInterface proxyTestInterface() {
        final InvocationHandler handler = new InvocationHandlerImpl(
                new TestInterfaceImpl()
        );

        final ClassLoader loader = TestInterface.class.getClassLoader();
        final Class[] interfaces = new Class[]{TestInterface.class};
        return (TestInterface) Proxy.newProxyInstance(loader, interfaces, handler);
    }
}
