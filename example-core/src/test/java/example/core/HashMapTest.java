package example.core;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class HashMapTest {
    @Test
    void when_15_75_should_scale() throws NoSuchFieldException, IllegalAccessException {
        final Map<Integer, Integer> map = new HashMap<Integer, Integer>(15, 0.75f);

        map.put(1, 1);
        Assertions.assertEquals(12, getThreshold(map));
        Assertions.assertEquals(16, getTableSize(map));

        for (int i = 1; i <= 12; i++) {
            map.put(i, i);
        }
        Assertions.assertEquals(12, getThreshold(map));
        Assertions.assertEquals(16, getTableSize(map));

        map.put(13, 13);

        Assertions.assertEquals(24, getThreshold(map));
        Assertions.assertEquals(32, getTableSize(map));
    }

    private int getThreshold(
        final Object value
    ) throws NoSuchFieldException, IllegalAccessException {
        return getField(value, "threshold").getInt(value);
    }

    private Field getField(
        final Object value,
        final String name
    ) throws NoSuchFieldException {
        Field field = value.getClass().getDeclaredField(name);
        field.setAccessible(true);
        return field;
    }

    private int getTableSize(
        final Object value
    ) throws NoSuchFieldException, IllegalAccessException {
        Object table = getField(value, "table")
            .get(value);
        if (table==null) {
            return 0;
        }
        return ((Object[]) table).length;
    }
}
