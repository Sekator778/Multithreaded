package wait;

import org.junit.Test;

import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.CyclicBarrier;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class CountBarrierTest {

    @Test
    public void testTwoBarrier() {
        CountBarrier barrier = new CountBarrier(2);
        Thread first = new Thread(
                () -> {
                    barrier.count();
                    barrier.await();
                    System.out.println(Thread.currentThread().getName() + " started");
                }
        );
        Thread second = new Thread(
                () -> {
                    barrier.count();
                    barrier.await();
                    System.out.println(Thread.currentThread().getName() + " started");
                }
        );
        first.start();
        second.start();
        try {
            first.join();
            second.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
            first.interrupt();
            second.interrupt();
        }
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenCreateBarrier0() {
        CountBarrier barrier = new CountBarrier(0);
    }
}