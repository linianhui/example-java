package proxy;

import proxy.cglib.CGLibProxy;
import proxy.jdk.JdkProxy;

public class App {
    public static void main(String[] args) throws Exception {
        final TestInterface jdkProxyTestInterface = JdkProxy.proxy(new TestInterfaceImpl());
        jdkProxyTestInterface.hello();

        final TestInterfaceImpl cglibProxyTestInterfaceImpl = CGLibProxy.proxy(TestInterfaceImpl.class);
        cglibProxyTestInterfaceImpl.hello();
    }
}
