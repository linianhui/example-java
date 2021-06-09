package network;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

abstract class AbstractTest {

    private static final Set<Integer> USED_PORT = new HashSet<>();

    protected int startServer(ServerModel model) {
        int port = getRandomUnusedPort();
        System.out.printf("\n\n%s server port is %d", model.name(), port);
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                EchoServer.main(new String[]{model.name(), String.valueOf(port)});
            }
        });
        thread.start();
        return port;
    }

    protected int getRandomUnusedPort() {
        int port = new Random().nextInt(10000) + 20000;
        while (true) {
            if (portIsUnused(port)) {
                return port;
            } else {
                port = new Random().nextInt(10000) + 20000;
            }
        }
    }

    private boolean portIsUnused(int port) {
        if (USED_PORT.contains(port)){
            return false;
        }
        try {
            new Socket(InetAddress.getLoopbackAddress(), port);
            return false;
        } catch (IOException e) {
            USED_PORT.add(port);
            return true;
        }
    }

    protected Socket connect(int port) {
        while (true) {
            try {
                final Socket socket = connectCore(port);
                System.out.printf("\ntest client port is %d", socket.getLocalPort());
                return socket;
            } catch (Exception e) {

            }
        }
    }

    private Socket connectCore(int port) throws IOException {
        return new Socket(InetAddress.getLoopbackAddress(), port);
    }
}
