package io.netty;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;

public class NettyAcceptHandler extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel ch) {
        ChannelPipeline channelPipeline = ch.pipeline();
        channelPipeline.addLast(new NettyEchoHandler());

        System.out.printf("\naccept client %s", ch.remoteAddress());
    }
}
