package concurrent;

/**
 *
 */

public class ThreadStop {
    public static void main(String[] args) throws InterruptedException {
        Thread first = new Thread(
                () -> {
                    int count = 0;
                    while (!Thread.currentThread().isInterrupted()) {
                        System.out.println(count++);
                    }
                }
        );
        first.start();
        Thread.sleep(1);
        first.interrupt();
    }
}