package network;

public abstract class EchoServer {
    private final int port;

    public EchoServer(int port) {
        this.port = port;
    }

    public void start() {
        try {
            startCore(port);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected abstract void startCore(int port) throws Exception;
}
