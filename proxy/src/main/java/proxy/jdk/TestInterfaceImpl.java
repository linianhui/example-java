package proxy.jdk;

import proxy.TestInterface;

public class TestInterfaceImpl implements TestInterface {
    @Override
    public String hello() {
        return "hi!";
    }
}
