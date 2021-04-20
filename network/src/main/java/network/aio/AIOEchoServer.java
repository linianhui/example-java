package network.aio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.util.concurrent.CountDownLatch;

import network.EchoServerRunnable;

public class AIOEchoServer extends EchoServerRunnable {

    public AIOEchoServer(int port) {
        super(port);
    }

    @Override
    protected void runCore(int port) throws IOException {
        runCore(bind(port));
    }

    private AsynchronousServerSocketChannel bind(int port) throws IOException {
        final AsynchronousServerSocketChannel asynchronousServerSocketChannel = AsynchronousServerSocketChannel.open();
        final InetSocketAddress socketAddress = new InetSocketAddress(port);
        asynchronousServerSocketChannel.bind(socketAddress);
        System.out.printf(
                "\nlisten on %s:%d waiting for client...",
                socketAddress.getAddress().getHostAddress(),
                socketAddress.getPort()
        );
        return asynchronousServerSocketChannel;
    }

    private void runCore(AsynchronousServerSocketChannel asynchronousServerSocketChannel) throws IOException {
        try {
            CountDownLatch countDownLatch = new CountDownLatch(1);
            asynchronousServerSocketChannel.accept(this, new AIOAcceptCompleteHandler());
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
