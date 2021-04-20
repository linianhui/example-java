package network.netty;

import java.nio.charset.StandardCharsets;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class NettyEchoHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
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
        ctx.flush();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }
}
