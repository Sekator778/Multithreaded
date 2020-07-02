package nonblocking;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class CASCounterAtomicIntegerImplTest {
   private final CASCounterAtomicIntegerImpl counter = new CASCounterAtomicIntegerImpl();

    @Before
    public void setUp() throws Exception {
        counter.set(7);
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void get() {
        assertThat(counter.get(), is(7));
    }

    @Test
    public void set() {
    }

    @Test
    public void getAndSet() {
    }

    @Test
    public void compareAndSet() {
    }

    @Test
    public void incrementAndGet() {
    }

    @Test
    public void getAndAdd() {
    }

    @Test
    public void getAndIncrement() {
    }

    @Test
    public void decrementAndGet() {
    }

    @Test
    public void getAndDecrement() {
    }

    @Test
    public void testToString() {
    }
}