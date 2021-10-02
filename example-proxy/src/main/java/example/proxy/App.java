package example.proxy;

import example.proxy.cglib.CGLibProxy;
import example.proxy.javassist.JavassistProxy;
import example.proxy.jdk.JdkProxy;
import example.proxy.util.LogUtil;

public class App {

    public static void main(String[] args) throws Exception {
        javassistModifyClass();
        jdkProxy();
        cglibProxy();
    }

    private static void javassistModifyClass() throws Exception {
        final TestInterfaceImpl proxy = JavassistProxy.modifyClass("example.proxy.TestInterfaceImpl");
        proxy.hello();
    }

    private static void jdkProxy() {
        final TestInterface proxy = JdkProxy.proxy(new TestInterfaceImpl());
        proxy.hello();
        LogUtil.logProxyClass(proxy);
    }

    private static void cglibProxy() {
        final TestInterfaceImpl proxy = CGLibProxy.proxy(TestInterfaceImpl.class);
        proxy.hello();
    }
}
