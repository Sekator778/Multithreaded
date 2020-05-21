package atomicity;

/**
 * Класс счетчик.
 */

public final class Count {
    private static int value;

    public synchronized void increment() {
        value++;
    }

    public int get() {
        return value;
    }
}