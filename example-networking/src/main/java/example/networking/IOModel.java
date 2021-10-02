package example.networking;

public enum IOModel {
    BIO,
    BIO_THREAD,
    BIO_THREAD_POOL,
    NIO,
    AIO,
    NETTY_BIO,
    NETTY_NIO,
    NETTY_EPOLL,
    NETTY_KQUEUE
}
