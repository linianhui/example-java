package io.bio;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketAddress;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class BIOEchoServerSocketThreadPool extends EchoServerSocketRunnable {

    public BIOEchoServerSocketThreadPool(ServerSocket serverSocket) {
        super(serverSocket);
    }

    @Override
    public void runCore(ServerSocket serverSocket) throws IOException {
        ExecutorService executor = new ThreadPoolExecutor(
            2,
            8,
            10, TimeUnit.MINUTES,
            new ArrayBlockingQueue<Runnable>(16),
            new ThreadPoolExecutor.AbortPolicy()
        );

        while (true) {
            Socket socket = serverSocket.accept();
            SocketAddress clientAddress = socket.getRemoteSocketAddress();
            System.out.println("\naccept client " + clientAddress);
            executor.submit(new BIOEchoSocket(socket));
        }
    }
}
