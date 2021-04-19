package io;

import java.io.IOException;
import java.net.Socket;
import java.net.SocketAddress;

public class BIOSocketEcho extends SocketEcho {

    public BIOSocketEcho(Socket socket) {
        super(socket);
    }

    @Override
    public void runCore(Socket socket) throws IOException {
        SocketAddress clientAddress = socket.getRemoteSocketAddress();
        System.out.println("\naccept client " + clientAddress);
        int readSize = 0;
        do {
            readSize = readAndWrite(socket);
        } while (readSize!=-1);
        socket.close();
    }
}
