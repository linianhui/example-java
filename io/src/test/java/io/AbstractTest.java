package io;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Random;

abstract class AbstractTest {
    protected int start_server(String type) {
        int port = getRandomUnusedPort();
        System.out.printf("\n\nrandom port is %d", port);
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                EchoServer.main(new String[]{type, String.valueOf(port)});
            }
        });
        thread.start();
        return port;
    }

    protected int getRandomUnusedPort() {
        int port = new Random().nextInt(10000) + 50000;
        while (true) {
            if (portIsUnused(port)) {

                return port;
            } else {
                port = new Random().nextInt(10000) + 50000;
            }
        }
    }

    private boolean portIsUnused(int port) {
        try {
            new Socket(InetAddress.getLoopbackAddress(), port);
            return false;
        } catch (IOException e) {
            return true;
        }
    }

    protected Socket connect(int port) {
        while (true) {
            try {
                return connectCore(port);
            } catch (Exception e) {

            }
        }
    }

    private Socket connectCore(int port) throws IOException {
        return new Socket(InetAddress.getLoopbackAddress(), port);
    }
}
