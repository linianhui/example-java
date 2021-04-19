package io;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class AppServerTest extends AbstractTest {
    @Test
    void test_bio_server_is_ok() throws IOException {
        int port = start_server("bio");

        String actual = writeAndRead(port, "abc\n");

        Assertions.assertEquals("ABC\n", actual);
    }

    private String writeAndRead(int port, String input) throws IOException {
        Socket socket = new Socket(InetAddress.getLoopbackAddress(), port);
        byte[] writeBytes = input.getBytes(StandardCharsets.UTF_8);
        socket.getOutputStream().write(writeBytes);

        byte[] readBytes = new byte[writeBytes.length];
        socket.getInputStream().read(readBytes, 0, writeBytes.length);
        return new String(readBytes);
    }


}
