package network.aio;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.nio.charset.StandardCharsets;

public class AIOReadCompleteHandler implements CompletionHandler<Integer, ByteBuffer> {

    private final AsynchronousSocketChannel channel;

    public AIOReadCompleteHandler(AsynchronousSocketChannel channel) {
        this.channel = channel;
    }

    @Override
    public void completed(Integer readSize, ByteBuffer buf) {
        long pid = Thread.currentThread().getId();
        if (readSize==-1) {
            System.out.printf("\npid=%d read form client : FIN\n", pid);
            try {
                channel.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return;
        }
        String str = new String(buf.array(), 0, readSize, StandardCharsets.UTF_8);
        System.out.printf("\npid=%d read form client : %s", pid, str);

        String upperCase = str.toUpperCase();
        channel.write(ByteBuffer.wrap(upperCase.getBytes(StandardCharsets.UTF_8)));
        System.out.printf("\npid=%d write to client : %s", pid, upperCase);
    }

    @Override
    public void failed(Throwable exc, ByteBuffer buf) {

    }
}
