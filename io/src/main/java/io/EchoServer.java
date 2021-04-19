package io;

import io.aio.AIOEchoServer;
import io.bio.BIOEchoServer;
import io.bio.BIOThreadEchoServer;
import io.bio.BIOThreadPoolEchoServer;
import io.nio.NIOEchoServer;

public class EchoServer {
    public static void main(String[] args) {
        String type = "bio";
        int port = 23456;
        if (args.length >= 1) {
            type = args[0];
        }
        if (args.length >= 2) {
            port = Integer.parseInt(args[1]);
        }

        create(type, port).run();
    }

    private static EchoServerRunnable create(
            final String type,
            final int port
    ) {
        switch (type) {
            case "bio":
                return new BIOEchoServer(port);
            case "bio-thread":
                return new BIOThreadEchoServer(port);
            case "bio-thread-pool":
                return new BIOThreadPoolEchoServer(port);
            case "nio":
                return new NIOEchoServer(port);
            case "aio":
                return new AIOEchoServer(port);
            default:
                return null;
        }
    }
}
