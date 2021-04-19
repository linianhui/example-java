package io;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public interface Echo {
    void echo(ServerSocket serverSocket) throws IOException;

    default int echoCore(
            Socket socket
    ) throws IOException {
        final InputStream in = socket.getInputStream();
        final byte[] buf = new byte[256];
        int readSize = in.read(buf);
        if (readSize==-1) {
            return readSize;
        }
        String str = new String(buf, 0, readSize - 1, StandardCharsets.UTF_8);
        System.out.printf("read form client : %s", str);
        String upperCase = str.toUpperCase();

        final OutputStream out = socket.getOutputStream();
        out.write(upperCase.getBytes(StandardCharsets.UTF_8));
        System.out.printf("write to client : %s", upperCase);
        return readSize;
    }
}
