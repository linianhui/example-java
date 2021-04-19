package io;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketAddress;

public class BIOEcho implements Echo {

    public void echo(ServerSocket serverSocket) throws IOException {
        int readSize = 0;
        byte[] buf = new byte[256];
        while (true) {
            Socket socket = serverSocket.accept();
            SocketAddress clientAddress = socket.getRemoteSocketAddress();
            System.out.println("\naccept client " + clientAddress);
            InputStream in = socket.getInputStream();
            OutputStream out = socket.getOutputStream();
            while ((readSize = in.read(buf))!=-1) {
                System.out.print("\nread form client : ");
                System.out.print(new String(buf, 0, readSize));
                out.write(buf, 0, readSize);
            }
            socket.close();
        }
    }
}
