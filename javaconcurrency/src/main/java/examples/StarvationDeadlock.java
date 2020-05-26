package examples;

import java.util.concurrent.*;

public class StarvationDeadlock {
    private static final ExecutorService executor = Executors.newSingleThreadExecutor();

    private static class DummyTask implements Callable<String> {

        public String call() throws Exception {
            System.out.println("Preparing");
            final Future<String> a = executor.submit(new DummyTask());
            System.out.println("Submited subtask");

            System.out.println("before");
            String aa = a.get();
            System.out.println("after");
            return aa;
        }
    }

    public static void main(String[] args) {
        executor.submit(new DummyTask());
        System.out.println("Done");
    }
}
