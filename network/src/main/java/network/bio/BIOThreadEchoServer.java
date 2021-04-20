package network.bio;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class BIOThreadEchoServer extends BIOEchoServer {

    public BIOThreadEchoServer(int port) {
        super(port);
    }

    @Override
    public void startCore(final ServerSocket serverSocket) throws IOException {
        while (true) {
            Socket socket = serverSocket.accept();
            Thread thread = new Thread(new BIOEchoHandler(socket));
            thread.start();
        }
    }
}
