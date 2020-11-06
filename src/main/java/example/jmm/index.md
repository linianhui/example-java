# JMM(Java Memory Model)

JMM规定了Java中的变量的访问规则。

1. 所有变量都存储在主内存中。
2. 每个线程有自己的工作内存，所有操作都必须在工作内存中进行。

主内存对应就是在堆、元数据区这点地方。
工作内存对应着线程私有的存储空间和cpu的缓存等。

多线程之间通信一般由两种方式：
1. 消息
2. 共享内存

java采用的是共享内存。

# 指令重排和happens-before

为了提供性能，编译器和CPU通常会对指令进行重排：
1. 编译器级别的重排。不改变单线程代码语义的情况下，优化指令的顺序。
2. 指令并行的重排。把原本是串行的指令调整为并行执行，不改变依赖性。
3. 内存系统的重排。cpu缓存。

JMM提供的是语言级别的内存模型，所以可以确保在不同的编译器和CPU平台上，通过禁止一些重排，来提供一致的内存可见性。
编译器禁止重排是通过添加一些内存屏障指令，来标记不能重排。

JDK 1.5时引入了happens-before的规则，通过它来描述操作的可见性。比如
```java
int i=1;    // A
int j=i+1;  // B
```
上面就是A happens-before B。JMM可以保证B在执行时，A的结果对B是可见的。

1. 程序顺序：一个线程中，每个操作都before于后续的操作。
2. 监视器锁：每一个监视器的解锁，before于后面的加锁。
3. volatile关键字: 对一个volatile的写，before后续任意的读。
4. 传递性: A before B, B before C，那么A before C。

## volatile 关键字

两个特征：
1. 保证被修饰的变量对所有线程的可见性。
2. 禁止指令重排优化。

实现原理：
1. 写操作强制同步到主内存。
2. 写操作强制失效其他线程的工作内存中的变量值。

四种类型的内存屏障（lock前缀的指令，把缓存同步到主内存，同时使得处理器的其他核心的缓存无效，这样读取的时候就会从主内存重写加载）：
1. LoadLoad : Load1—>LoadLoad—>Load2 Load1的结果对Load2可见。
2. StoreStore : Store1—>StoreStore—>Store2 Store1的结果对其他处理器可见
3. LoadStore : Load1—>LoadStore—>Store2 Load1的结果对后续的操作可见
4. StoreLoad : Store1—>StoreLoad—>Load2  Store1的结果对其他处理器可见。

