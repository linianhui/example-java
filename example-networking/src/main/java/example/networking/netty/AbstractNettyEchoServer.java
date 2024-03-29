package example.networking.netty;

import example.networking.EchoServer;
import example.networking.util.LogUtil;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.ServerChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

public abstract class AbstractNettyEchoServer extends EchoServer {

    public AbstractNettyEchoServer(int port) {
        super(port);
    }

    @Override
    protected void startCore(int port) throws InterruptedException {
        LogUtil.logCaller();
        final EventLoopGroup master = buildEventLoopGroup(1);
        final EventLoopGroup worker = buildEventLoopGroup(4);
        try {
            ServerBootstrap bootstrap = new ServerBootstrap();
            bootstrap.group(master, worker)
                .channel(buildChannel())
                .option(ChannelOption.SO_BACKLOG, 64)
                .handler(new LoggingHandler(LogLevel.TRACE))
                .childHandler(new NettyAcceptHandler());

            ChannelFuture future = bootstrap.bind(port).sync();
            System.out.printf(
                "\nlisten on %s waiting for client...",
                future.channel().localAddress()
            );
            future.channel().closeFuture().sync();
        } finally {
            master.shutdownGracefully();
            worker.shutdownGracefully();
        }
    }

    protected abstract EventLoopGroup buildEventLoopGroup(int nThreads);

    protected abstract Class<? extends ServerChannel> buildChannel();
}
