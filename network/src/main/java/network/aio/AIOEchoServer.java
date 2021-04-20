package network.aio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.util.concurrent.CountDownLatch;

import network.EchoServerHandler;

public class AIOEchoServer extends EchoServerHandler {

    public AIOEchoServer(int port) {
        super(port);
    }

    @Override
    protected void startCore(int port) throws IOException {
        startCore(bind(port));
    }

    private AsynchronousServerSocketChannel bind(int port) throws IOException {
        final AsynchronousServerSocketChannel asynchronousServerSocketChannel = AsynchronousServerSocketChannel.open();
        final InetSocketAddress socketAddress = new InetSocketAddress(port);
        asynchronousServerSocketChannel.bind(socketAddress);
        System.out.printf(
                "\nlisten on %s waiting for client...",
                socketAddress
        );
        return asynchronousServerSocketChannel;
    }

    private void startCore(AsynchronousServerSocketChannel asynchronousServerSocketChannel) throws IOException {
        try {
            CountDownLatch countDownLatch = new CountDownLatch(1);
            asynchronousServerSocketChannel.accept(this, new AIOAcceptHandler());
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
