package io;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Random;

abstract class AbstractTest {
    protected int start_server(String type) {
        int port = getRandomUnusedPort();
        System.out.printf("random port is %d\n", port);
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    AppServer.main(new String[]{String.valueOf(port), type});
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        thread.start();
        return port;
    }

    private int getRandomUnusedPort() {
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
}
