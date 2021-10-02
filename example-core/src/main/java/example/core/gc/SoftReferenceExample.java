package example.core.gc;

import java.lang.ref.SoftReference;


public class SoftReferenceExample {

    private final SoftReference<Object> softReferenceField;


    public SoftReferenceExample(Object value) {
        this.softReferenceField = new SoftReference<Object>(value);
    }

    public Object getSoftReferenceField() {
        return softReferenceField.get();
    }


}
