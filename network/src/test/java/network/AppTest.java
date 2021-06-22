package network;

import java.io.IOException;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.UUID;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.EnabledOnOs;
import org.junit.jupiter.api.condition.OS;

public class AppTest extends AbstractTest {

    @Test
    void test_bio_server_is_ok() throws IOException {
        test_server(IOModel.BIO);
    }

    @Test
    void test_bio_thread_server_is_ok() throws IOException {
        test_server(IOModel.BIO_THREAD);
    }

    @Test
    void test_bio_thread_pool_server_is_ok() throws IOException {
        test_server(IOModel.BIO_THREAD_POOL);
    }

    @Test
    void test_nio_server_is_ok() throws IOException {
        test_server(IOModel.NIO);
    }

    @Test
    void test_aio_server_is_ok() throws IOException {
        test_server(IOModel.AIO);
    }

    @Test
    void test_netty_bio_server_is_ok() throws IOException {
        test_server(IOModel.NETTY_BIO);
    }

    @Test
    void test_netty_nio_server_is_ok() throws IOException {
        test_server(IOModel.NETTY_NIO);
    }

    @Test
    @EnabledOnOs(OS.LINUX)
    void test_netty_epoll_server_is_ok() throws IOException {
        test_server(IOModel.NETTY_EPOLL);
    }

    @Test
    @EnabledOnOs(OS.MAC)
    void test_netty_kqueue_server_is_ok() throws IOException {
        test_server(IOModel.NETTY_KQUEUE);
    }

    void test_server(IOModel model) throws IOException {
        int port = startServer(model);

        final Socket socket = connect(port);

        assertWriteAndRead(socket);
    }

    private void assertWriteAndRead(Socket socket) throws IOException {
        String inputString = UUID.randomUUID().toString().toLowerCase();

        String actual = writeAndRead(socket, inputString);

        Assertions.assertEquals(inputString.toUpperCase(), actual);
    }

    private String writeAndRead(Socket socket, String input) throws IOException {
        System.out.printf("\npid=%d client write : %s", Thread.currentThread().getId(), input);
        byte[] writeBytes = input.getBytes(StandardCharsets.UTF_8);
        socket.getOutputStream().write(writeBytes);

        byte[] readBytes = new byte[writeBytes.length];
        socket.getInputStream().read(readBytes);
        String read = new String(readBytes);
        System.out.printf("\npid=%d client read : %s", Thread.currentThread().getId(), read);

        return read;
    }

}
