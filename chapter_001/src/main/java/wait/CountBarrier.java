package wait;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

/**
 * Класс, который блокирует выполнение по условию счетчика.
 * Переменная total содержит количество вызовом метода count().
 * <p>
 * Нити, которые выполняют метод await, могут начать работу если поле count == total.
 * Если оно не равно, то нужно перевести нить в состояние wait.
 */
@ThreadSafe
public class CountBarrier {
    @GuardedBy("monitor")
    private final Object monitor = this;

    private final int total;

    private int count = 0;

    public CountBarrier(final int total) {
        this.total = total;
    }

    public void count() {
        synchronized (monitor) {
            count++;
        }
    }

    public void await() {
        synchronized (monitor) {
            if (total != count) {
                monitor.notifyAll();
            } else {
                try {
                    monitor.wait();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        }
    }
}