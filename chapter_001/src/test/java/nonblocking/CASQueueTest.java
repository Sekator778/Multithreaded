package nonblocking;

import org.junit.Test;

import java.util.*;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class CASQueueTest {

    @Test
    public void whenPushAndPollElement() {
        CASQueue<Integer> queue = new CASQueue<>();
        queue.push(1);
        queue.push(2);
        queue.push(3);
        assertThat(queue.poll(), is(1));
        assertThat(queue.poll(), is(2));
        queue.push(4);
        queue.push(5);
        assertThat(queue.poll(), is(3));
        assertThat(queue.poll(), is(4));
        assertThat(queue.poll(), is(5));
    }

    @Test
    public void whenMultithreadingPushAndPollElement() throws InterruptedException {
        CASQueue<Integer> queue = new CASQueue<>();
        Thread first = new Thread(
                () -> {
                    queue.push(1);
                    queue.push(2);
                    queue.push(3);
                }
        );
        Thread second = new Thread(
                () -> {
                    queue.push(1);
                    queue.push(2);
                    queue.push(3);
                }
        );
        first.start();
        second.start();
        first.join();
        second.join();
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            list.add(queue.poll());
        }
        Collections.sort(list);
        assertThat(list, is(Arrays.asList(1, 1, 2, 2, 3, 3)));
    }
}
