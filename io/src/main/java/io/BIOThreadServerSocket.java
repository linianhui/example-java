package io;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketAddress;

public class BIOThreadServerSocket implements ServerSocketRunnable{

    @Override
    public void run(ServerSocket serverSocket) throws IOException {
        while (true) {
            Socket socket = serverSocket.accept();
            SocketAddress clientAddress = socket.getRemoteSocketAddress();
            System.out.println("\naccept client " + clientAddress);
            Thread thread = new Thread(new BIOSocketEcho(socket));
            thread.start();
            socket.close();
        }
    }
}
