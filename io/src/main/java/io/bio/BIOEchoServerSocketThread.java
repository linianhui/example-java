package io.bio;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketAddress;

import io.EchoServerSocketRunnable;

public class BIOEchoServerSocketThread extends EchoServerSocketRunnable {

    public BIOEchoServerSocketThread(ServerSocket serverSocket) {
        super(serverSocket);
    }

    @Override
    public void runCore(ServerSocket serverSocket) throws IOException {
        while (true) {
            Socket socket = serverSocket.accept();
            SocketAddress clientAddress = socket.getRemoteSocketAddress();
            System.out.println("\naccept client " + clientAddress);
            Thread thread = new Thread(new BIOEchoSocket(socket));
            thread.start();
            socket.close();
        }
    }
}
