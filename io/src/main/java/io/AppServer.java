package io;

import java.io.IOException;
import java.net.ServerSocket;

import io.bio.BIOEchoServerSocket;
import io.bio.BIOEchoServerSocketThread;
import io.bio.BIOEchoServerSocketThreadPool;

public class AppServer {
    public static void main(String[] args) throws IOException {
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

    private static Runnable create(
        final String type,
        final int port
    ) throws IOException {
        switch (type) {
            case "bio":
                return new BIOEchoServerSocket(new ServerSocket(port));
            case "bio-thread":
                return new BIOEchoServerSocketThread(new ServerSocket(port));
            case "bio-thread-pool":
                return new BIOEchoServerSocketThreadPool(new ServerSocket(port));
            default:
                return null;
        }
    }
}
