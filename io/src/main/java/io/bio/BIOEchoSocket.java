package io.bio;

import java.io.IOException;
import java.net.Socket;

public class BIOEchoSocket extends EchoSocketRunnable {

    public BIOEchoSocket(Socket socket) {
        super(socket);
    }

    @Override
    public void runCore(Socket socket) throws IOException {
        int readSize = 0;
        do {
            readSize = readAndWrite(socket);
        } while (readSize != -1);
        socket.close();
    }
}
