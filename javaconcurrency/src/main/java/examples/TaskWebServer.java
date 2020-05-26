package examples;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class TaskWebServer {
    private static final int NUMBER_OF_THREAD = 10;
    private static final Executor taskExecutor = Executors.newFixedThreadPool(NUMBER_OF_THREAD);

    public static void main(String[] args) throws IOException {
        final ServerSocket serverSocket = new ServerSocket(80);

        while (true) {
            final Socket request = serverSocket.accept();

            final Runnable task = new Runnable() {
                public void run() {
                    doSomething(request);
                }
            };

            taskExecutor.execute(task);
        }
    }

    private static void doSomething(Socket socket) {};
}
