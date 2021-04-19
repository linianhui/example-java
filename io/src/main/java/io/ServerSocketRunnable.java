package io;

import java.io.IOException;
import java.net.ServerSocket;

public interface ServerSocketRunnable{
    public void run(ServerSocket serverSocket) throws IOException;
}
