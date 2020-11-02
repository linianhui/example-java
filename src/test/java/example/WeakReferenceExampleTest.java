package example;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class WeakReferenceExampleTest {

    @Test
    void when_not_has_reference_gc_WeakReference_should_get_null() {
        WeakReferenceExample example = new WeakReferenceExample(new Object());

        Assertions.assertNotNull(example.getWeakReferenceField());

        System.gc();

        Assertions.assertNull(example.getWeakReferenceField());
    }

    @Test
    void when_has_reference_gc_WeakReference_should_get_not_null() {
        Object value = new Object();

        WeakReferenceExample example = new WeakReferenceExample(value);

        Assertions.assertNotNull(example.getWeakReferenceField());

        System.gc();

        Assertions.assertNotNull(example.getWeakReferenceField());
    }

}
