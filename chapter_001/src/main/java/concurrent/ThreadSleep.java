package concurrent;

/**
 *
 */

public class ThreadSleep {
    public static void main(String[] args) {
        Thread first = new Thread(
                () -> {
                    try {
                        System.out.println("Start loading ....");
                        Thread.sleep(3000);
                        System.out.println("Loaded.");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
        );
        first.start();
        System.out.println("main: " + Thread.currentThread().getName());
    }
}
