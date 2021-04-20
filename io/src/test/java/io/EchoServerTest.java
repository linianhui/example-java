package io;

import java.io.IOException;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class EchoServerTest extends AbstractTest {

    @ParameterizedTest()
    @ValueSource(strings = {
            "bio",
            "bio-thread",
            "bio-thread-pool",
            "nio",
            "aio",
            "netty"
    })
    void test_server_is_ok(String type) throws IOException {
        int port = start_server(type);

        String actual = writeAndRead(port, type);

        Assertions.assertEquals(type.toUpperCase(), actual);
    }

    private String writeAndRead(int port, String input) throws IOException {
        Socket socket = connect(port);

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
