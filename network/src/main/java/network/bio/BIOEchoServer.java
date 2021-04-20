package network.bio;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import network.EchoServerRunnable;

public class BIOEchoServer extends EchoServerRunnable {

    public BIOEchoServer(int port) {
        super(port);
    }

    @Override
    protected void runCore(int port) throws IOException {
        final ServerSocket serverSocket = new ServerSocket(port);
        System.out.printf(
                "\nlisten on %s:%d waiting for client...",
                serverSocket.getInetAddress().getHostAddress(),
                serverSocket.getLocalPort()
        );
        runCore(serverSocket);
    }

    protected void runCore(final ServerSocket serverSocket) throws IOException {
        while (true) {
            Socket socket = serverSocket.accept();
            new BIOEchoRunnable(socket).run();
            socket.close();
        }
    }
}
