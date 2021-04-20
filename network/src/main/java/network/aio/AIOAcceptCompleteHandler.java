package network.aio;

import java.io.IOException;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;

public class AIOAcceptCompleteHandler implements CompletionHandler<AsynchronousSocketChannel, AIOEchoServer> {

    @Override
    public void completed(AsynchronousSocketChannel channel, AIOEchoServer aioEchoServer) {
        try {
            SocketAddress clientAddress = channel.getRemoteAddress();
            System.out.printf("\naccept client %s", clientAddress);
        } catch (IOException e) {
            e.printStackTrace();
        }

        ByteBuffer buf = ByteBuffer.allocate(1024);
        channel.read(buf, buf, new AIOReadCompleteHandler(channel));
    }

    @Override
    public void failed(Throwable exc, AIOEchoServer attachment) {

    }
}
