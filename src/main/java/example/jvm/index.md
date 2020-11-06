# JVM(Java Virtual Machine) Java虚拟机

JVM是java代码的运行环境，包括如下几个部分：

## 1 代码执行

1. java编译器javac : 把*.java源代码编译为*.class字节码文件。
2. ClassLoader : 装载*.class。
3. 执行*.class : 
    1. 解释执行
    2. 热点代码经过JIT编译为本机代码直接执行。
    
## 2 内存管理

### 2.1 内存布局

1. 堆: 对象分配区域。
2. Java线程栈: 默认1M大小，1:1OS的线程。由方法的栈帧构成：
     1. 局部变量表。
     2. 操作数栈。
     3. 动态连接
     4. 返回地址
3. 元数据区: class、代码、静态变量、常量池等。
4. 本机方法栈: 调用本地方法时使用的。
5. 程序计数器: 在线程中断、阻塞或者让出CPU时间片时记录的当前执行位置。
6. 直接内存区: 1.4 NIO引入后，基于缓冲区的I/O。直接在堆外分配内存，然后通过DirectByteBuffer这个引用操作堆外内存。

### 2.2 内存分配

1. TLAB(Thread Local Allocation Buffer)
2. 堆
3. 栈


### 2.3 GC

gc.md

## 3 线程同步机制

