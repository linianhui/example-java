package proxy;

import proxy.jdk.JdkProxy;

public class App {
    public static void main(String[] args) throws Exception {
        final TestInterface testInterface = JdkProxy.proxyTestInterface();
        testInterface.hello();
    }
}
