package network.netty;

import java.nio.charset.StandardCharsets;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import network.util.LogUtil;

public class NettyEchoHandler extends ChannelInboundHandlerAdapter {
    public NettyEchoHandler() {
        super();
        LogUtil.logCaller();
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        LogUtil.logCaller();
        ByteBuf buf = (ByteBuf) msg;
        long pid = Thread.currentThread().getId();

        byte[] readBytes = new byte[buf.readableBytes()];
        buf.readBytes(readBytes);
        String str = new String(readBytes, StandardCharsets.UTF_8);
        System.out.printf("\npid=%d read form client : %s", pid, str);


        String upperCase = str.toUpperCase();
        buf.clear();
        buf.writeBytes(upperCase.getBytes(StandardCharsets.UTF_8));
        ctx.writeAndFlush(buf);
        System.out.printf("\npid=%d write to client : %s", pid, upperCase);
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) {
        LogUtil.logCaller();
        ctx.flush();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        LogUtil.logCaller();
        cause.printStackTrace();
        ctx.close();
    }


    @Override
    public void channelUnregistered(ChannelHandlerContext ctx) throws Exception {
        LogUtil.logCaller();
        super.channelUnregistered(ctx);
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        LogUtil.logCaller();
        System.out.printf("\naccept client %s connected", ctx.channel().remoteAddress());
        super.channelActive(ctx);
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        LogUtil.logCaller();
        System.out.printf("\nclose client %s connected", ctx.channel().remoteAddress());
        super.channelInactive(ctx);
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
    public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
        LogUtil.logCaller();
        super.channelRegistered(ctx);
    }

    @Override
    protected void ensureNotSharable() {
        super.ensureNotSharable();
        LogUtil.logCaller();

    }

    @Override
    public boolean isSharable() {
        LogUtil.logCaller();
        return super.isSharable();
    }

}
