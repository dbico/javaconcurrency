package examples;

public class NonVisible {
    private static boolean ready;
    private static int number;

    private static class ReaderClass extends Thread {

        public void run() {
            while (!ready) {
                Thread.yield();
            }
            System.out.println("Number:" + number);
        }
    }

    public static void main(String[] args) {
        new ReaderClass().run();
        number = 1;
        ready = true;
    }
}
