package example.networking.aio;

import java.io.IOException;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;

import example.networking.util.LogUtil;

public class AIOAcceptHandler implements CompletionHandler<AsynchronousSocketChannel, AIOEchoServer> {

    @Override
    public void completed(AsynchronousSocketChannel channel, AIOEchoServer aioEchoServer) {
        LogUtil.logCaller();
        try {
            SocketAddress clientAddress = channel.getRemoteAddress();
            System.out.printf("\naccept client %s", clientAddress);
        } catch (IOException e) {
            e.printStackTrace();
        }

        ByteBuffer buf = ByteBuffer.allocate(1024);
        channel.read(buf, buf, new AIOEchoHandler(channel));
    }

    @Override
    public void failed(Throwable exc, AIOEchoServer attachment) {
        LogUtil.logCaller();
    }
}
