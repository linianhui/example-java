package network.netty;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import network.util.LogUtil;

public class NettyAcceptHandler extends ChannelInitializer<SocketChannel> {

    public NettyAcceptHandler() {
        super();
        LogUtil.logCaller();
    }

    @Override
    protected void initChannel(SocketChannel ch) {
        LogUtil.logCaller();
        ChannelPipeline channelPipeline = ch.pipeline();
        channelPipeline.addLast(new NettyEchoHandler());
    }

    @Override
    public void channelUnregistered(ChannelHandlerContext ctx) throws Exception {
        LogUtil.logCaller();
        super.channelUnregistered(ctx);
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        LogUtil.logCaller();
        super.channelActive(ctx);
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        LogUtil.logCaller();
        super.channelInactive(ctx);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        LogUtil.logCaller();
        super.channelRead(ctx, msg);
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        LogUtil.logCaller();
        super.channelReadComplete(ctx);
    }

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        LogUtil.logCaller();
        super.userEventTriggered(ctx, evt);
    }

    @Override
    public void channelWritabilityChanged(ChannelHandlerContext ctx) throws Exception {
        LogUtil.logCaller();
        super.channelWritabilityChanged(ctx);
    }

    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        LogUtil.logCaller();
        super.handlerAdded(ctx);
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        LogUtil.logCaller();
        super.handlerRemoved(ctx);
    }


    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        LogUtil.logCaller();
        super.exceptionCaught(ctx, cause);
    }

    @Override
    protected void ensureNotSharable() {
        LogUtil.logCaller();
        super.ensureNotSharable();
    }

    @Override
    public boolean isSharable() {
        LogUtil.logCaller();
        return super.isSharable();
    }

}
