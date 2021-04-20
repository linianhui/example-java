package network.bio;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import network.EchoServerHandler;

public class BIOEchoServer extends EchoServerHandler {

    public BIOEchoServer(int port) {
        super(port);
    }

    @Override
    protected void startCore(int port) throws IOException {
        final ServerSocket serverSocket = new ServerSocket(port);
        System.out.printf(
                "\nlisten on %s:%d waiting for client...",
                serverSocket.getInetAddress().getHostAddress(),
                serverSocket.getLocalPort()
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
