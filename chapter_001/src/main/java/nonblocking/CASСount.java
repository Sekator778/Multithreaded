package nonblocking;


import net.jcip.annotations.ThreadSafe;

import java.util.concurrent.atomic.AtomicReference;

/**
 * неблокирующий счетчик on AtomicReference
 */
@ThreadSafe
public class CASСount<T> {
    private final AtomicReference<Integer> count = new AtomicReference<>();

    /**
     * Creates a new CASСount and is initialized to 0.
     */
    public CASСount() {
        count.set(0);
    }

    /**
     * Creates a new CASСount and is initialized to specified initialValue.
     */
    public CASСount(int initialValue) {
       count.set(initialValue);
    }
    /**
     * method returns the current value
     *
     */
    public int get(){
        return count.get();
    }
    /**
     * method increments the current value
     */
    public void increment() {
        while (true) {
            int realValue = get();
            int newValue = realValue + 1;
            if (count.compareAndSet(realValue, newValue)) {
                return;
            }
        }
    }
}