package synch;

import list.SimpleArrayList;
import net.jcip.annotations.ThreadSafe;

import java.util.Iterator;

@ThreadSafe
public class SingleLockList<T> implements Iterable<T> {
    private SimpleArrayList list = new SimpleArrayList();
    

    public void add(T value) {
    }

    public T get(int index) {
        return null;
    }


    @Override
    public Iterator<T> iterator() {
        return null;
    }
}