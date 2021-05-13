package proxy.cglib;

import net.sf.cglib.proxy.Enhancer;
import proxy.TestInterface;
import proxy.TestInterfaceImpl;

public class CGLibProxy {
    public static TestInterface proxyTestInterface() {
        final Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(TestInterface.class);
        enhancer.setCallback(new MethodInterceptorImpl(new TestInterfaceImpl()));
        return (TestInterface) enhancer.create();
    }
}
