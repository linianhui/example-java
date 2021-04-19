package io;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketAddress;

public class BIOEcho implements Echo {

    public void echo(ServerSocket serverSocket) throws IOException {
        while (true) {
            Socket socket = serverSocket.accept();
            SocketAddress clientAddress = socket.getRemoteSocketAddress();
            System.out.println("\naccept client " + clientAddress);
            echo(socket);
            socket.close();
        }
    }

    private void echo(Socket socket) throws IOException {
        int readSize = 0;
        do {
            readSize = echoCore(socket);
        } while (readSize!=-1);
    }

}
