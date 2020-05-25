package executeservice;

import store.User;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * сервис для рассылки почты
 */

public class EmailNotification {
    private final ExecutorService pool;

    /**
     * size == availableProcessors
     */
    public EmailNotification() {
        this.pool = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
    }

    /**
     * size = user set value
     */
    public EmailNotification(int sizePool) {
        this.pool = Executors.newFixedThreadPool(sizePool);
    }

    public void emailTo(User user) {

    }

    public void close() {
        this.pool.shutdown();
        while (!pool.isTerminated()) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void send(String subject, String body, String email) {}
}
