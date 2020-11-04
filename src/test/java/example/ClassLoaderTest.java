package example;

import java.net.URLClassLoader;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

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

    Assertions.assertEquals(
        "sun.misc.Launcher$ExtClassLoader", extClassLoader.getClass().getName()
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
