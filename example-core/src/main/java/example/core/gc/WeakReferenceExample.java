package example.core.gc;

import java.lang.ref.WeakReference;

public class WeakReferenceExample {

    private final WeakReference<Object> weakReferenceField;


    public WeakReferenceExample(Object value) {
        this.weakReferenceField = new WeakReference<Object>(value);
    }

    public Object getWeakReferenceField() {
        return weakReferenceField.get();
    }


}
