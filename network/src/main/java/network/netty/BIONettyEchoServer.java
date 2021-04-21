package network.netty;

import io.netty.channel.EventLoopGroup;
import io.netty.channel.oio.OioEventLoopGroup;
import io.netty.channel.socket.oio.OioServerSocketChannel;

public class BIONettyEchoServer extends AbstractNettyEchoServer {

    public BIONettyEchoServer(int port) {
        super(port);
    }

    @Override
    protected EventLoopGroup buildEventLoopGroup(int nThreads) {
        return new OioEventLoopGroup(nThreads);
    }

    @Override
    protected Class<?> buildChannel() {
        return OioServerSocketChannel.class;
    }
}
