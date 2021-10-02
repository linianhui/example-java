package example.networking.bio;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.SocketAddress;
import java.nio.charset.StandardCharsets;

import example.networking.util.LogUtil;

public class BIOEchoHandler implements Runnable {

    private final Socket socket;

    public BIOEchoHandler(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            SocketAddress clientAddress = socket.getRemoteSocketAddress();
            System.out.printf("\naccept client %s", clientAddress);
            echo(socket);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void echo(Socket socket) throws IOException {
        int readSize = 0;
        do {
            readSize = readAndWrite(socket);
        } while (readSize!=-1);
        socket.close();
    }

    protected int readAndWrite(Socket socket) throws IOException {
        LogUtil.logCaller();
        final InputStream in = socket.getInputStream();
        final byte[] buf = new byte[256];
        long pid = Thread.currentThread().getId();
        int readSize = in.read(buf);
        if (readSize==-1) {
            System.out.printf("\npid=%d read form client : FIN\n", pid);
            return readSize;
        }
        String str = new String(buf, 0, readSize, StandardCharsets.UTF_8);
        System.out.printf("\npid=%d read form client : %s", pid, str);
        String upperCase = str.toUpperCase();

        final OutputStream out = socket.getOutputStream();
        out.write(upperCase.getBytes(StandardCharsets.UTF_8));
        System.out.printf("\npid=%d write to client : %s", pid, upperCase);
        return readSize;
    }
}
