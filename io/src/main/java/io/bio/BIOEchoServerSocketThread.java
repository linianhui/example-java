package io.bio;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class BIOEchoServerSocketThread extends EchoServerSocketRunnable {

    public BIOEchoServerSocketThread(ServerSocket serverSocket) {
        super(serverSocket);
    }

    @Override
    public void runCore(ServerSocket serverSocket) throws IOException {
        while (true) {
            Socket socket = serverSocket.accept();
            Thread thread = new Thread(new BIOEchoSocket(socket));
            thread.start();
        }
    }
}
