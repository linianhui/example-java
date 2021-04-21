package network;

import java.io.IOException;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.UUID;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

public class EchoServerTest extends AbstractTest {

    @ParameterizedTest()
    @EnumSource(value = ServerModel.class)
    void test_server_is_ok(ServerModel model) throws IOException {
        int port = startServer(model);

        final Socket socket = connect(port);

        assertWriteAndRead(socket);
    }

    private void assertWriteAndRead(Socket socket) throws IOException {
        String inputString = UUID.randomUUID().toString();

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
