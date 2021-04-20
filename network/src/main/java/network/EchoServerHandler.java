package network;

public abstract class EchoServerHandler {
    private final int port;

    public EchoServerHandler(int port) {
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
