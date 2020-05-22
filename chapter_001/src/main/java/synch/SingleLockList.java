package synch;

import list.SimpleArrayList;
import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.Iterator;

/**
 * коллекция, которая будет корректно работать в многопоточный среде.
 * То есть сама коллекция будет общим ресурсом между нитями.
 *
 * @param <T>
 */
@ThreadSafe
public class SingleLockList<T> implements Iterable<T> {
    @GuardedBy("list")
    private final SimpleArrayList<T> list = new SimpleArrayList<T>();


    public void add(T value) {
        synchronized (list) {
            list.add(value);
        }
    }

    public synchronized T get(int index) {
        synchronized (list) {
            return list.get(index);
        }
    }

    private SimpleArrayList<T> copy() {
        synchronized (list) {
            SimpleArrayList<T> copy = new SimpleArrayList<T>();
            for (T t : list
            ) {
                copy.add(t);
            }
            return copy;
        }
    }

    /**
     * Этот итератор будет работать в режиме fail-safe -
     * все изменения после получения коллекции не будут отображаться в итераторе.
     * @return итератор
     */
    @Override
    public Iterator<T> iterator() {
        synchronized (list) {
            return copy().iterator();
        }
    }
}