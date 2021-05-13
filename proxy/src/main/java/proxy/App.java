package proxy;

import proxy.cglib.CGLibProxy;
import proxy.jdk.JdkProxy;

public class App {
    public static void main(String[] args) throws Exception {
        final TestInterface jdkProxyTestInterface = JdkProxy.proxyTestInterface();
        jdkProxyTestInterface.hello();

        final TestInterface cglibProxyTestInterface = CGLibProxy.proxyTestInterface();
        cglibProxyTestInterface.hello();
    }
}
