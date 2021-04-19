package io.bio;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class BIOEchoServerSocket extends EchoServerSocketRunnable {

    public BIOEchoServerSocket(ServerSocket serverSocket) {
        super(serverSocket);
    }

    @Override
    public void runCore(ServerSocket serverSocket) throws IOException {
        while (true) {
            Socket socket = serverSocket.accept();
            new BIOEchoSocket(socket).run();
            socket.close();
        }
    }
}
