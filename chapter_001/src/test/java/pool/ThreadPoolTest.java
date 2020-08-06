package pool;

import blockingqueue.SimpleBlockingQueue;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class ThreadPoolTest {

    @Test
    public void whenWorkThreadPool() throws InterruptedException {
        SimpleBlockingQueue<Runnable> queue = new SimpleBlockingQueue<>(23);
        List<Runnable> jobs = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            int idIndex = i;
            jobs.add(() -> System.out.printf("%s starting job %s%n", Thread.currentThread().getName(), idIndex));
        }
        ThreadPool threadPool = new ThreadPool(queue);
        Thread producer = new Thread(
                () -> {
                    for (Runnable job: jobs) {
                        try {
                            threadPool.work(job);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                            Thread.currentThread().interrupt();
                        }
                    }
                }
        );
        Thread consumer = new Thread(
                threadPool::execute
        );
        producer.start();
        consumer.start();
        producer.join();
        consumer.join();
        assertThat(consumer.getState(), is(Thread.State.TERMINATED));
    }

    @Test
    public void whenShutdownThreadPool() throws InterruptedException {
        SimpleBlockingQueue<Runnable> queue = new SimpleBlockingQueue<>(23);
        List<Runnable> jobs = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            int idIndex = i;
            jobs.add(() -> System.out.printf("%s starting job %s%n", Thread.currentThread().getName(), idIndex));
        }
        final ThreadPool threadPool = new ThreadPool(queue);
        Thread producer = new Thread(
                () -> {
                    for (Runnable job: jobs) {
                        try {
                            threadPool.work(job);
                            Thread.currentThread().wait(100);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                            Thread.currentThread().interrupt();
                        }
                    }
                }
        );
        Thread consumer = new Thread(
                threadPool::execute
        );
        producer.start();
        consumer.start();
        producer.join();
        consumer.join();
        threadPool.shutdown();
        assertThat(consumer.getState(), is(Thread.State.TERMINATED));
    }
}