package io;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.HashMap;

public class AppServer {
    public static void main(String[] args) throws IOException {
        int port = 23456;
        String type = "bio";
        if (args.length==1) {
            port = Integer.parseInt(args[0]);
        }
        if (args.length==2) {
            type = args[1];
        }
        HashMap<String, Echo> map = new HashMap<>();
        map.put("bio", new BIOEcho());

        final ServerSocket serverSocket = new ServerSocket(port);
        System.out.printf(
                "\nlisten on %s:%d waiting for client...",
                serverSocket.getInetAddress().getHostAddress(),
                serverSocket.getLocalPort()
        );
        map.get(type).echo(serverSocket);
    }
}
