package example.networking.bio;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import example.networking.EchoServer;
import example.networking.util.LogUtil;

public class BIOEchoServer extends EchoServer {

    public BIOEchoServer(int port) {
        super(port);
    }

    @Override
    protected void startCore(int port) throws IOException {
        LogUtil.logCaller();
        final ServerSocket serverSocket = new ServerSocket(port);
        System.out.printf(
            "\nlisten on %s waiting for client...",
            serverSocket.getLocalSocketAddress()
        );
        startCore(serverSocket);
    }

    protected void startCore(final ServerSocket serverSocket) throws IOException {
        while (true) {
            Socket socket = serverSocket.accept();
            new BIOEchoHandler(socket).run();
            socket.close();
        }
    }
}
