package proxy;

import proxy.cglib.CGLibProxy;
import proxy.javassist.JavassistProxy;
import proxy.jdk.JdkProxy;

public class App {
    public static void main(String[] args) throws Exception {
        javassistModifyClass();
        jdkProxy();
        cglibProxy();
    }

    private static void javassistModifyClass() throws Exception {
        final TestInterfaceImpl proxy= JavassistProxy.modifyClass("proxy.TestInterfaceImpl");
        proxy.hello();
    }

    private static void jdkProxy() {
        final TestInterface proxy = JdkProxy.proxy(new TestInterfaceImpl());
        proxy.hello();
    }

    private static void cglibProxy() {
        final TestInterfaceImpl proxy = CGLibProxy.proxy(TestInterfaceImpl.class);
        proxy.hello();
    }
}
