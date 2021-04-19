package io.bio;

import java.io.IOException;
import java.net.Socket;
import java.net.SocketAddress;

import io.EchoSocketRunnable;

public class BIOEchoSocket extends EchoSocketRunnable {

    public BIOEchoSocket(Socket socket) {
        super(socket);
    }

    @Override
    public void runCore(Socket socket) throws IOException {
        SocketAddress clientAddress = socket.getRemoteSocketAddress();
        System.out.println("\naccept client " + clientAddress);
        int readSize = 0;
        do {
            readSize = readAndWrite(socket);
        } while (readSize != -1);
        socket.close();
    }
}