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

        final ServerSocket serverSocket = new ServerSocket(port);
        System.out.printf(
            "\nlisten on %s:%d waiting for client...",
            serverSocket.getInetAddress().getHostAddress(),
            serverSocket.getLocalPort()
        );
        create(type, serverSocket).run();
        create(type, serverSocket).run();
    }

    private static EchoServerSocketRunnable create(
        final String type,
        final ServerSocket serverSocket) {
        switch (type) {
            case "bio":
                return new BIOEchoServerSocket(serverSocket);
            case "bio-thread":
                return new BIOEchoServerSocketThread(serverSocket);
            case "bio-thread-pool":
                return new BIOEchoServerSocketThreadPool(serverSocket);
            default:
                return null;
        }
    }
}
