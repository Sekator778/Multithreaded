package pool;

/**
 *
 */

public class Task implements Runnable {
    @Override
    public void run() {
        try {
            System.out.println(Thread.currentThread().getName() + " is executing task.");
            Thread.sleep(2000);
            System.out.println(Thread.currentThread().getName() + " is accomplished task.");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
