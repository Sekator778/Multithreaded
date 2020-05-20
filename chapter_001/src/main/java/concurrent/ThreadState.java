package concurrent;

/**
 * NEW - нить создана, но не запущена.
 *
 * RUNNABLE - нить запущена и выполняется.
 *
 * BLOCKED - нить заблокирована.
 *
 * WAITING - нить ожидает уведомления.
 *
 * TIMED_WAITING - нить ожидает уведомление в течении определенного периода.
 *
 * TERMINATED - нить завершила работу.
 */

public class ThreadState {
    public static void main(String[] args) {
        Thread first = new Thread(
                () -> System.out.println(Thread.currentThread().getName())
        );
        Thread second = new Thread(
                () -> System.out.println(Thread.currentThread().getName())
        );
        System.out.println("first run: " + first.getState());
        System.out.println("second run: " + second.getState());
        first.start();
        second.start();
        while ((first.getState() != Thread.State.TERMINATED)) {
            System.out.println("first state: " + first.getState());
        }
        while ((second.getState() != Thread.State.TERMINATED)) {
            System.out.println("second state: " + second.getState());
        }
        System.out.println("first terminated: " + first.getState());
        System.out.println("second terminated: " + second.getState());
    }
}