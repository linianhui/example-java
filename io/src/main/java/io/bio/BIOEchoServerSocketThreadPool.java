package io.bio;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketAddress;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import io.EchoServerSocketRunnable;

public class BIOEchoServerSocketThreadPool extends EchoServerSocketRunnable {

    public BIOEchoServerSocketThreadPool(ServerSocket serverSocket) {
        super(serverSocket);
    }

    @Override
    public void runCore(ServerSocket serverSocket) throws IOException {
        while (true) {
            Socket socket = serverSocket.accept();
            SocketAddress clientAddress = socket.getRemoteSocketAddress();
            System.out.println("\naccept client " + clientAddress);
            ExecutorService executor = new ThreadPoolExecutor(
                2,
                8,
                10, TimeUnit.MINUTES,
                new ArrayBlockingQueue<Runnable>(16),
                new ThreadPoolExecutor.AbortPolicy()
            );
            executor.submit(new BIOEchoSocket(socket));
            socket.close();
        }
    }
}