package io;

import io.bio.BIOEchoServer;
import io.bio.BIOThreadEchoServer;
import io.bio.BIOThreadPoolEchoServer;
import io.nio.NIOEchoServer;

public class EchoServer {
    public static void main(String[] args) {
        int port = 23456;
        String type = "bio";
        if (args.length >= 1) {
            port = Integer.parseInt(args[0]);
        }
        if (args.length >= 2) {
            type = args[1];
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
            default:
                return null;
        }
    }
}
