package example.core.gc;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class SoftReferenceExampleTest {

    @Test
    void when_not_has_reference_but_has_enough_memory_gc_SoftReference_should_get_not_null() {
        SoftReferenceExample example = new SoftReferenceExample(new Object());

        Assertions.assertNotNull(example.getSoftReferenceField());

        System.gc();

        //内存不足时才会被回收，所以当前没有被回收。
        Assertions.assertNotNull(example.getSoftReferenceField());
    }

    @Test
    void when_has_reference_gc_SoftReference_should_get_not_null() {
        Object value = new Object();

        SoftReferenceExample example = new SoftReferenceExample(value);

        Assertions.assertNotNull(example.getSoftReferenceField());

        System.gc();

        Assertions.assertNotNull(example.getSoftReferenceField());
    }

}
