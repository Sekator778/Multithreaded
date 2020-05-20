package concurrent;

/**
 * !!! Метод Thread.inturrupt() не выставляет флаг прерывания, если нить находиться в режиме WAIT, JOIN.
 * Прерывание блокированной нити обрабатываем в блоке catch
 *  catch (InterruptedException e) {
 *             Thread.currentThread().interrupt();
 */

public class ConsoleProgress implements Runnable {
    @Override
    public void run() {
        try {
            while (!Thread.currentThread().isInterrupted()) {
                System.out.print("\r load: " + "\\");
                Thread.sleep(500);
                System.out.print("\r load: " + "|");
                Thread.sleep(500);
                System.out.print("\r load: " + "/");
                Thread.sleep(500);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread progress = new Thread(new ConsoleProgress());
        progress.start();
        Thread.sleep(10000); /* главный поток засыпает дает время для работы параллельной задачи в течение 1 секунды. */
       /* <-- Метод Thread.inturrupt() не выставляет флаг прерывания, если нить находиться в режиме WAIT, JOIN. */
        progress.interrupt();
    }
}
