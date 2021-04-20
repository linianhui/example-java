package network.netty;

import network.EchoServerHandler;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

public class NettyEchoServer extends EchoServerHandler {

    public NettyEchoServer(int port) {
        super(port);
    }

    @Override
    protected void startCore(int port) throws InterruptedException {
        final EventLoopGroup master = new NioEventLoopGroup(1);
        final EventLoopGroup worker = new NioEventLoopGroup(4);
        try {
            ServerBootstrap bootstrap = new ServerBootstrap();
            bootstrap.group(master, worker)
                    .channel(NioServerSocketChannel.class)
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
}
