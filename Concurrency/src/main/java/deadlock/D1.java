package deadlock;

/**
 *
 */

public class D1 {
    public synchronized static void deadlock() {
        try {
            Thread t = new Thread(D1::deadlock);
            t.start();
            t.join();
        } catch (Exception ignored) {
        }
    }

    public static void main(String[] args) {
        deadlock();
    }
}
