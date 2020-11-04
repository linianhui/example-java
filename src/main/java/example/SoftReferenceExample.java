package example;

import java.lang.ref.SoftReference;


public class SoftReferenceExample {

  private SoftReference<Object> softReferenceField;


  public SoftReferenceExample(Object value) {
    this.softReferenceField = new SoftReference<Object>(value);
  }

  public Object getSoftReferenceField() {
    return softReferenceField.get();
  }


}
