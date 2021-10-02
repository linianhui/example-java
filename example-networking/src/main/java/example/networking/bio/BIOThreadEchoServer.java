package example.networking.bio;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import example.networking.util.LogUtil;

public class BIOThreadEchoServer extends BIOEchoServer {

    public BIOThreadEchoServer(int port) {
        super(port);
    }

    @Override
    public void startCore(final ServerSocket serverSocket) throws IOException {
        LogUtil.logCaller();
        while (true) {
            final Socket socket = serverSocket.accept();
            final Thread thread = new Thread(
                new BIOEchoHandler(socket),
                "bio-thread-for-" + socket.getRemoteSocketAddress().toString()
            );
            thread.start();
        }
    }
}
