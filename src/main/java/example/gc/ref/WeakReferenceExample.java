package example.gc.ref;

import java.lang.ref.WeakReference;

public class WeakReferenceExample {

    private WeakReference<Object> weakReferenceField;


    public WeakReferenceExample(Object value) {
        this.weakReferenceField = new WeakReference<Object>(value);
    }

    public Object getWeakReferenceField() {
        return weakReferenceField.get();
    }


}
