package example;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ClassLoaderTest {

    @Test
    void test_parent_ClassLoader() throws ClassNotFoundException {
        // 先委托父类加载器加载。
        ClassLoader appClassLoader = (ClassLoader) this.getClass().getClassLoader();
        ClassLoader extClassLoader = (ClassLoader) appClassLoader.getParent();
        ClassLoader bootstrapClassLoader = (ClassLoader) extClassLoader.getParent();

        Assertions.assertEquals(
            "jdk.internal.loader.ClassLoaders$AppClassLoader", appClassLoader.getClass().getName()
        );

        Assertions.assertEquals(
            "jdk.internal.loader.ClassLoaders$PlatformClassLoader", extClassLoader.getClass().getName()
        );

        Assertions.assertNull(bootstrapClassLoader);

        Assertions.assertSame(
            appClassLoader.loadClass("java.lang.String"),
            extClassLoader.loadClass("java.lang.String")
        );

        Assertions.assertSame(
            extClassLoader.loadClass("java.lang.String"),
            Class.forName("java.lang.String")
        );

        Assertions.assertSame(
            Class.forName("java.lang.String"),
            ClassLoader.getSystemClassLoader().loadClass("java.lang.String")
        );
    }
}
