package network.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;
import java.util.Set;

import network.EchoServerHandler;

public class NIOEchoServer extends EchoServerHandler {

    public NIOEchoServer(int port) {
        super(port);
    }

    @Override
    protected void startCore(int port) throws IOException {
        startCore(bind(port));
    }

    private ServerSocketChannel bind(int port) throws IOException {
        final ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        final ServerSocket serverSocket = serverSocketChannel.socket();
        serverSocket.bind(new InetSocketAddress(port));
        System.out.printf(
                "\nlisten on %s waiting for client...",
                serverSocket.getLocalSocketAddress()
        );
        serverSocketChannel.configureBlocking(false);
        return serverSocketChannel;
    }

    private void startCore(ServerSocketChannel serverSocketChannel) throws IOException {
        final Selector selector = Selector.open();
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

        final ByteBuffer buf = ByteBuffer.allocate(1024);

        while (true) {
            selector.select();
            final Set<SelectionKey> keys = selector.selectedKeys();
            final Iterator<SelectionKey> it = keys.iterator();
            while (it.hasNext()) {
                final SelectionKey key = it.next();
                if (key.isAcceptable()) {
                    registerReadChannel(serverSocketChannel.accept(), selector);
                } else if (key.isReadable()) {
                    if (readAndWrite((SocketChannel) key.channel(), buf) < 0) {
                        key.channel().close();
                        key.cancel();
                    }
                }
                it.remove();
            }
        }
    }

    private void registerReadChannel(
            final SocketChannel socketChannel,
            final Selector selector
    ) throws IOException {
        socketChannel.configureBlocking(false);
        socketChannel.register(selector, SelectionKey.OP_READ);
        SocketAddress clientAddress = socketChannel.socket().getRemoteSocketAddress();
        System.out.printf("\naccept client %s", clientAddress);
    }

    private int readAndWrite(
            final SocketChannel socketChannel,
            final ByteBuffer buf
    ) throws IOException {
        long pid = Thread.currentThread().getId();
        buf.clear();
        int readSize = socketChannel.read(buf);
        if (readSize==-1) {
            System.out.printf("\npid=%d read form client : FIN\n", pid);
            return readSize;
        }
        buf.flip();
        String str = new String(buf.array(), 0, readSize, StandardCharsets.UTF_8);
        System.out.printf("\npid=%d read form client : %s", pid, str);

        String upperCase = str.toUpperCase();
        socketChannel.write(ByteBuffer.wrap(upperCase.getBytes(StandardCharsets.UTF_8)));
        System.out.printf("\npid=%d write to client : %s", pid, upperCase);
        return readSize;
    }

}
