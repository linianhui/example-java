package example.proxy.javassist;

import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;

public class JavassistProxy {
    /**
     * 直接改clazz的字节码.
     * 被修改的类必须是未被加载的才可以。
     **/
    public static <T> T modifyClass(String className) throws Exception {
        final ClassPool classPool = ClassPool.getDefault();
        final CtClass ctClass = classPool.getCtClass(className);
        ctClass.defrost();
        final CtMethod[] ctMethodArray = ctClass.getDeclaredMethods();
        for (CtMethod ctMethod : ctMethodArray) {
            ctMethod.insertBefore("System.out.println(\"javassist begin===<<\");");
            ctMethod.insertAfter("System.out.println(\"javassist end======>>\");");
        }
        return (T) ctClass.toClass().newInstance();
    }
}
