package io;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public abstract class SocketEcho implements Runnable {

    private final Socket socket;

    public SocketEcho(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            runCore(socket);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected abstract void runCore(Socket socket) throws IOException;

    int readAndWrite(Socket socket) throws IOException {
        final InputStream in = socket.getInputStream();
        final byte[] buf = new byte[256];
        long pid = Thread.currentThread().getId();
        int readSize = in.read(buf);
        if (readSize==-1) {
            System.out.printf("pid=%d read form client : FIN\n", pid);
            return readSize;
        }
        String str = new String(buf, 0, readSize - 1, StandardCharsets.UTF_8);
        System.out.printf("pid=%d read form client : %s", pid, str);
        String upperCase = str.toUpperCase();

        final OutputStream out = socket.getOutputStream();
        out.write(upperCase.getBytes(StandardCharsets.UTF_8));
        System.out.printf("pid=%d write to client : %s", pid, upperCase);
        return readSize;
    }
}
