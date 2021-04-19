package io;

import java.io.IOException;
import java.net.ServerSocket;

public abstract class EchoServerSocketRunnable implements Runnable {
    private final ServerSocket serverSocket;

    public EchoServerSocketRunnable(ServerSocket serverSocket) {
        this.serverSocket = serverSocket;
    }

    @Override
    public void run() {
        try {
            runCore(serverSocket);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected abstract void runCore(ServerSocket serverSocket) throws IOException;
}
