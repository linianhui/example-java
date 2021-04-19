package io.bio;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketAddress;

public class BIOEchoServerSocket extends EchoServerSocketRunnable {

    public BIOEchoServerSocket(ServerSocket serverSocket) {
        super(serverSocket);
    }

    @Override
    public void runCore(ServerSocket serverSocket) throws IOException {
        while (true) {
            Socket socket = serverSocket.accept();
            SocketAddress clientAddress = socket.getRemoteSocketAddress();
            System.out.println("\naccept client " + clientAddress);
            new BIOEchoSocket(socket).run();
            socket.close();
        }
    }
}
