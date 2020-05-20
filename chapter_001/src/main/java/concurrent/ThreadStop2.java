package concurrent;

/**
 * Метод Thread.inturrupt() не выставляет флаг прерывания, если нить находиться в режиме WAIT, JOIN.
 */

public class ThreadStop2 {
    public static void main(String[] args) throws InterruptedException {
        Thread progress = new Thread(
                () -> {
                    while (!Thread.currentThread().isInterrupted()) {
                        try {
                            System.out.println("start ...");
                            Thread.sleep(10000);
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                        }
                    }
                }
        );
        progress.start();
        Thread.sleep(1000); /* <-- Метод Thread.inturrupt() не выставляет флаг прерывания, если нить находиться в режиме WAIT, JOIN. */
        progress.interrupt();
        progress.join();
    }
}