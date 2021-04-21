package network.netty;

import io.netty.channel.EventLoopGroup;
import io.netty.channel.ServerChannel;
import io.netty.channel.kqueue.KQueueEventLoopGroup;
import io.netty.channel.kqueue.KQueueServerSocketChannel;

public class KQueueNettyEchoServer extends AbstractNettyEchoServer {

    public KQueueNettyEchoServer(int port) {
        super(port);
    }

    @Override
    protected EventLoopGroup buildEventLoopGroup(int nThreads) {
        return new KQueueEventLoopGroup(nThreads);
    }

    @Override
    protected Class<? extends ServerChannel> buildChannel() {
        return KQueueServerSocketChannel.class;
    }
}
