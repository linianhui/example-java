package example.jvm;

// Class的对象分配在堆。
// Class本身的信息（包含代码）分配在元数据区。
public class MemoryLayoutExample implements Comparable<MemoryLayoutExample> {

    // 静态字段分配在元数据区。
    public static final boolean BOOLEAN_VALUE = true;

    public static final byte BYTE_VALUE = 32;

    public static final char CHAR_VALUE = 'L';

    public static final String STRING_VALUE = "LNH中文😀";

    public static final short SHORT_VALUE = 12345;

    public static final int INT_VALUE = 123456789;

    public static final long LONG_VALUE = 12345678901L;

    public static final float FLOAT_VALUE = 3.14F;

    public static final double DOUBLE_VALUE = 123.456D;

    // 实例成员变量随着对象分配在堆。
    public final int v = 123;

    // 方法体内的变量在java的线程栈中：默认1M大小。
    // 一个方法的执行会在线程栈中创建一个栈帧，栈帧包含如下4部分信息：
    // 1. 局部变量表。
    // 2. 操作数栈。
    // 3. 动态连接
    // 4. 返回地址
    // Program Counter 程序计数器，记录当前线程执行到了哪一步（操作数栈）。
    public int compareTo(MemoryLayoutExample o) {
        int ov = o.v;
        return Integer.compare(v, ov);
    }

    // JNI java native interface, 调用本地方法，内存分配在本地方法栈中。
    public native void someNativeMethodName();

    // Direct Memory直接内存区并不是JVM管理的一部分
    // 1.4引入NIO后。
}