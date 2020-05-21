package atomicity;

/**
 * Класс счетчик.
 */

public final class Count {
    private int value;

    public  void increment() {
        synchronized (this) {
            value++;
        }
    }

    public void decrement() {
        value--;
    }

    public int get() {
        return value;
    }
}