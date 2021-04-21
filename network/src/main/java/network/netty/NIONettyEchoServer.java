package network.netty;

import io.netty.channel.EventLoopGroup;
import io.netty.channel.ServerChannel;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

public class NIONettyEchoServer extends AbstractNettyEchoServer {

    public NIONettyEchoServer(int port) {
        super(port);
    }

    @Override
    protected EventLoopGroup buildEventLoopGroup(int nThreads) {
        return new NioEventLoopGroup(nThreads);
    }

    @Override
    protected Class<? extends ServerChannel> buildChannel() {
        return NioServerSocketChannel.class;
    }
}
