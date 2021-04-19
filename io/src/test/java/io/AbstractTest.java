package io;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Random;

abstract class AbstractTest {
    protected Thread start_server(int port, String type) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    EchoServer.main(new String[]{String.valueOf(port), type});
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        return thread;
    }

    public int getRandomUnusedPort() {
        int port = new Random().nextInt(10000) + 50000;
        while (true) {
            if (portIsUnused(port)) {
                System.out.printf("random port is %d\n", port);
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
}
