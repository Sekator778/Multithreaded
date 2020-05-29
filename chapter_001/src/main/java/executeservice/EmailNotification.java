package executeservice;

import net.jcip.annotations.ThreadSafe;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * сервис для рассылки почты
 */
@ThreadSafe
public class EmailNotification {
    private final ExecutorService pool = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

    public void emailTo(User user) {
        String subject = String.format("Notification %s to email %s ", user.getUsername(), user.getEmail());
        String body = String.format("Add a new event to %s", user.getUsername());
        this.pool.submit(
                () -> {
                    send(subject, body, user.getEmail());
                    sendString(subject, body, user.getEmail());
                });
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

    private void send(String subject, String body, String email) {
    }

    /**
     * only for test
     */
    private String sendString(String subject, String body, String email) {
        return "dd " + subject + body + email;
    }
}
