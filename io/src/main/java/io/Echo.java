package io;

import java.io.IOException;
import java.net.ServerSocket;

public interface Echo {
    void echo(ServerSocket serverSocket) throws IOException;
}
