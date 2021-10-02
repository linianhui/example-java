package example.proxy;

import example.proxy.util.LogUtil;

public class TestInterfaceImpl implements TestInterface {
    @Override
    public String hello() {
        LogUtil.logArgs();
        final String text = "hi!";
        LogUtil.logReturn(text);
        return text;
    }
}
