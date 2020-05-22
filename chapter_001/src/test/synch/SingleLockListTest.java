package synch;

import org.junit.Test;

import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class SingleLockListTest {

    @Test
    public void add() throws InterruptedException {
        SingleLockList<Integer> list = new SingleLockList<>();
        Thread first = new Thread(() -> list.add(1));
        Thread second = new Thread(() -> list.add(2));
        first.start();
        second.start();
        first.join();
        second.join();
        Set<Integer> rsl = new TreeSet<>();
        list.iterator().forEachRemaining(rsl::add);
        assertThat(rsl, is(Set.of(1, 2)));
    }

    @Test
    public void get() throws InterruptedException {
        SingleLockList<Integer> list = new SingleLockList<>();
        Thread first = new Thread(
                () -> {
                    list.add(1);
                    list.add(2);
                    list.add(3);
                });
        Thread second = new Thread(
                () -> {
                    list.add(1);
                    list.add(2);
                    list.add(3);
                });
        first.start();
        second.start();
        first.join();
        second.join();
        Thread rsl = new Thread(
                () -> {
                    int x = 0;
                    for (int i = 0; i < 3; i++) {
                        assertThat(list.get(i), is(++x));
                    }
                }
        );
        rsl.start();
    }

    @Test
    public void iterator() throws InterruptedException {
        SingleLockList<Integer> list = new SingleLockList<>();
        Thread first = new Thread(
                () -> {
                    list.add(1);
                    list.add(2);
                    list.add(3);
                });

        Thread second = new Thread(
                () -> {
                    int x = 0;
                    Iterator<Integer> iterator = list.iterator();
                    while (iterator.hasNext()) {
                        assertThat(iterator.next(), is(++x));
                    }
                });
        first.start();
        second.start();
        first.join();
        second.join();
    }
}