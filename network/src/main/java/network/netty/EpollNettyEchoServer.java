package network.netty;

import io.netty.channel.EventLoopGroup;
import io.netty.channel.ServerChannel;
import io.netty.channel.epoll.EpollEventLoopGroup;
import io.netty.channel.epoll.EpollServerSocketChannel;

public class EpollNettyEchoServer extends AbstractNettyEchoServer {

    public EpollNettyEchoServer(int port) {
        super(port);
    }

    @Override
    protected EventLoopGroup buildEventLoopGroup(int nThreads) {
        return new EpollEventLoopGroup(nThreads);
    }

    @Override
    protected Class<? extends ServerChannel> buildChannel() {
        return EpollServerSocketChannel.class;
    }
}
