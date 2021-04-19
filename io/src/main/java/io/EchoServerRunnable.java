package io;

import java.io.IOException;

public abstract class EchoServerRunnable implements Runnable {
    private final int port;

    public EchoServerRunnable(int port) {
        this.port = port;
    }

    @Override
    public void run() {
        try {
            runCore(port);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected abstract void runCore(int port) throws IOException;
}
