package network;

import java.util.HashMap;
import java.util.function.Function;

import network.aio.AIOEchoServer;
import network.bio.BIOEchoServer;
import network.bio.BIOThreadEchoServer;
import network.bio.BIOThreadPoolEchoServer;
import network.netty.BIONettyEchoServer;
import network.netty.EpollNettyEchoServer;
import network.netty.KQueueNettyEchoServer;
import network.netty.NIONettyEchoServer;
import network.nio.NIOEchoServer;

public class App {
    private final static HashMap<IOModel, Function<Integer, EchoServer>> MAP = new HashMap<>();

    static {
        MAP.put(IOModel.BIO, BIOEchoServer::new);
        MAP.put(IOModel.BIO_THREAD, BIOThreadEchoServer::new);
        MAP.put(IOModel.BIO_THREAD_POOL, BIOThreadPoolEchoServer::new);
        MAP.put(IOModel.NIO, NIOEchoServer::new);
        MAP.put(IOModel.AIO, AIOEchoServer::new);
        MAP.put(IOModel.NETTY_BIO, BIONettyEchoServer::new);
        MAP.put(IOModel.NETTY_NIO, NIONettyEchoServer::new);
        MAP.put(IOModel.NETTY_EPOLL, EpollNettyEchoServer::new);
        MAP.put(IOModel.NETTY_KQUEUE, KQueueNettyEchoServer::new);
    }

    public static void main(String[] args) {
        System.out.printf("\nargs [%s]\n", String.join(",", args));
        IOModel model = IOModel.BIO;
        int port = 12345;
        if (args.length >= 1) {
            model = IOModel.valueOf(args[0]);
        }
        if (args.length >= 2) {
            port = Integer.parseInt(args[1]);
        }
        MAP.get(model).apply(port).start();
    }
}
