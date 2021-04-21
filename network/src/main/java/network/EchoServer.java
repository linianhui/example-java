package network;

import java.util.HashMap;
import java.util.function.Function;

import network.aio.AIOEchoServer;
import network.bio.BIOEchoServer;
import network.bio.BIOThreadEchoServer;
import network.bio.BIOThreadPoolEchoServer;
import network.nio.NIOEchoServer;

public class EchoServer {
    private final static HashMap<ServerModel, Function<Integer, EchoServerHandler>> map = new HashMap<>();

    static {
        map.put(ServerModel.BIO, BIOEchoServer::new);
        map.put(ServerModel.BIO_THREAD, BIOThreadEchoServer::new);
        map.put(ServerModel.BIO_THREAD_POOL, BIOThreadPoolEchoServer::new);
        map.put(ServerModel.NIO, NIOEchoServer::new);
        map.put(ServerModel.AIO, AIOEchoServer::new);
        map.put(ServerModel.NETTY, NIOEchoServer::new);
    }

    public static void main(String[] args) {
        System.out.printf("\nargs [%s]\n", String.join(",", args));
        ServerModel model = ServerModel.BIO;
        int port = 12345;
        if (args.length >= 1) {
            model = ServerModel.valueOf(args[0]);
        }
        if (args.length >= 2) {
            port = Integer.parseInt(args[1]);
        }
        map.get(model).apply(port).start();
    }
}
