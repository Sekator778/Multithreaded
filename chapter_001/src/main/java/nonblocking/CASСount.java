package nonblocking;



import net.jcip.annotations.ThreadSafe;

import java.util.concurrent.atomic.AtomicReference;

/**
 *
 */
@ThreadSafe
public class CASСount<T> {
    private final AtomicReference<Integer> count = new AtomicReference<>();

    public void increment() {
        throw new UnsupportedOperationException("Count is not impl.");
    }

    public int get() {
        throw new UnsupportedOperationException("Count is not impl.");
    }
}