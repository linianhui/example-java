package io;

import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class EchoServerTest extends AbstractTest {

    @ParameterizedTest()
    @ValueSource(strings = {"bio", "bio-thread", "bio-thread-pool"})
    void test_server_is_ok(String type) throws IOException, InterruptedException {
        int port = getRandomUnusedPort();
        Thread thread = start_server(port, type);
        thread.start();

        Thread.sleep(200);
        String actual = writeAndRead(port, type);
        thread.stop();

        Assertions.assertEquals(type.toUpperCase(), actual);
    }

    private String writeAndRead(int port, String input) throws IOException, InterruptedException {
        Socket socket = new Socket(InetAddress.getLoopbackAddress(), port);
        byte[] writeBytes = input.getBytes(StandardCharsets.UTF_8);
        socket.getOutputStream().write(writeBytes);

        Thread.sleep(2000);
        byte[] readBytes = new byte[writeBytes.length];
        InputStream in = socket.getInputStream();
        while (true) {
            int readSize = in.read(readBytes, 0, writeBytes.length);
            System.out.printf("\ntest read %d", Thread.currentThread().getId());
            if (readSize != 0) {
                return new String(readBytes, 0, readSize);
            }
            if (readSize == -1) {
                return null;
            }
        }
    }


}
