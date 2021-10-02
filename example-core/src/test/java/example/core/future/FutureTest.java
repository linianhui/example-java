package example.core.future;

import static org.junit.jupiter.api.Assertions.*;

import java.util.concurrent.*;

import org.junit.jupiter.api.Test;

class FutureTest {

    @Test
    void test_future_with_runnable() throws Throwable {
        final ExecutorService executor = Executors.newSingleThreadExecutor();
        final Future<String> future = executor.submit(sleepRunnable(10), "result-abc");

        assertFalse(future.isCancelled());
        assertFalse(future.isDone());
        assertSame(FutureTask.class, future.getClass());
        assertEquals("result-abc", future.get());
        assertTrue(future.isDone());
    }

    @Test
    void test_future_with_runnable_when_timeout() {
        final ExecutorService executor = Executors.newSingleThreadExecutor();
        final Future<String> future = executor.submit(sleepRunnable(20), "result-abc");

        assertFalse(future.isCancelled());
        assertFalse(future.isDone());
        assertSame(FutureTask.class, future.getClass());
        assertThrows(
            TimeoutException.class,
            () -> future.get(5, TimeUnit.MILLISECONDS)
        );
        assertFalse(future.isDone());
    }

    @Test
    void test_future_with_callable() throws Throwable {
        final ExecutorService executor = Executors.newSingleThreadExecutor();
        final Future<String> future = executor.submit(sleepCallable(10));

        assertFalse(future.isCancelled());
        assertFalse(future.isDone());
        assertSame(FutureTask.class, future.getClass());
        assertEquals("callable-result-123", future.get());
        assertTrue(future.isDone());
    }

    @Test
    void test_future_with_callable_when_timeout() {
        final ExecutorService executor = Executors.newSingleThreadExecutor();
        final Future<String> future = executor.submit(sleepCallable(10));

        assertFalse(future.isCancelled());
        assertFalse(future.isDone());
        assertSame(FutureTask.class, future.getClass());
        assertThrows(
            TimeoutException.class,
            () -> future.get(5, TimeUnit.MILLISECONDS)
        );
        assertFalse(future.isDone());
    }

    @Test
    void test_future_with_callable_when_cancel() {
        final ExecutorService executor = Executors.newSingleThreadExecutor();
        final Future<String> future = executor.submit(sleepCallable(100));

        assertFalse(future.isCancelled());
        assertFalse(future.isDone());
        assertSame(FutureTask.class, future.getClass());
        future.cancel(false);
        assertThrows(
            CancellationException.class,
            () -> future.get(5, TimeUnit.MILLISECONDS)
        );
        assertTrue(future.isDone());
        assertTrue(future.isCancelled());
    }

    private Runnable sleepRunnable(final int millis) {
        return new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(millis);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
    }

    private Callable<String> sleepCallable(final int millis) {
        return new Callable() {
            @Override
            public String call() {
                try {
                    Thread.sleep(millis);
                    return "callable-result-123";
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return null;
            }
        };
    }

}
