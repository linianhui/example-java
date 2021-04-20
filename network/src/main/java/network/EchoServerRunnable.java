package network;

public abstract class EchoServerRunnable implements Runnable {
    private final int port;

    public EchoServerRunnable(int port) {
        this.port = port;
    }

    @Override
    public void run() {
        try {
            runCore(port);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected abstract void runCore(int port) throws Exception;
}
