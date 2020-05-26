package examples;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.RejectedExecutionException;

public class LifeCycleWebServer {
    private static final int NUMBER_OF_THREAD = 10;
    private static final ExecutorService taskExecutor = Executors.newFixedThreadPool(NUMBER_OF_THREAD);

    public static void main(String[] args) throws IOException {
        final ServerSocket serverSocket = new ServerSocket(80);

        while (!taskExecutor.isShutdown()) {
            final Socket request = serverSocket.accept();

            try {
                final Runnable task = new Runnable() {
                    public void run() {
                        doSomething(request);
                    }
                };

                taskExecutor.execute(task);
            } catch (RejectedExecutionException e) {
                if (!taskExecutor.isShutdown()) {
                    System.err.println("Task rejected: " + e);
                }
            }
        }
    }

    public static void stop() {
        taskExecutor.shutdown();
    }

    private static void doSomething(Socket socket) {};
}
