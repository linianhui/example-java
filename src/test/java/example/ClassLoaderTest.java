package example;

import java.net.URLClassLoader;
import java.util.Arrays;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import sun.misc.Launcher;

class ClassLoaderTest {

  @Test
  void test_parent_ClassLoader() throws ClassNotFoundException {
    // 先委托父类加载器加载。
    URLClassLoader appClassLoader = (URLClassLoader) this.getClass().getClassLoader();
    URLClassLoader extClassLoader = (URLClassLoader) appClassLoader.getParent();
    URLClassLoader bootstrapClassLoader = (URLClassLoader) extClassLoader.getParent();

    Assertions.assertEquals(
        "sun.misc.Launcher$AppClassLoader", appClassLoader.getClass().getName()
    );
    Assertions.assertTrue(
        Arrays.stream(appClassLoader.getURLs())
            .anyMatch(x -> x.toString().contains("junit"))
    );

    Assertions.assertEquals(
        "sun.misc.Launcher$ExtClassLoader", extClassLoader.getClass().getName()
    );
    Assertions.assertTrue(
        Arrays.stream(extClassLoader.getURLs())
            .allMatch(x -> x.toString().contains("/ext/") || x.toString().contains("/Ext"))
    );

    Assertions.assertNull(bootstrapClassLoader);
    Assertions.assertTrue(
        Arrays.stream(Launcher.getBootstrapClassPath().getURLs())
            .allMatch(x -> x.toString().contains("/jre/lib/")
                || x.toString().contains("/jre/classes"))
    );

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
