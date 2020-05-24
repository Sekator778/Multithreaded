package blockingqueue;

import org.junit.Test;

import java.util.Arrays;
import java.util.concurrent.CopyOnWriteArrayList;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

/**
 * while (!queue.isEmpty() || !Thread.currentThread().isInterrupted()) {
 *
 * Здесь мы проверяем, что очередь пустая или нить выключили.
 * Зачем нужна двойная проверка.
 * Если производитель закончил свою работу и сразу подаст сигнал об отключении потребителя, то мы не сможет прочитать все данные.
 * С другой стороны, если мы успели прочитать все данные и находимся в режиме wait пришедший сигнал запустит нить и
 * проверит состояние очереди и завершит цикл. Потребитель закончит свою работу.
 */
public class SimpleBlockingQueueTestTwo {
    @Test
    public void whenFetchAllThenGetIt() throws InterruptedException {
        final CopyOnWriteArrayList<Integer> buffer = new CopyOnWriteArrayList<>();
        final SimpleBlockingQueue<Integer> queue = new SimpleBlockingQueue<>();
        Thread producer = new Thread(
                () -> {
                    for (int i = 0; i < 5; i++) {
                        try {
                            queue.offer(i);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    System.out.println("producer end");
                }
        );
        producer.start();
        Thread consumer = new Thread(
                () -> {
                    System.out.println("start consumer");
                    while (!queue.isEmpty() || !Thread.currentThread().isInterrupted()) {
                        try {
                            buffer.add(queue.poll());
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                            Thread.currentThread().interrupt();
                        }
                    }
                }
        );
        consumer.start();
        producer.join();
        consumer.interrupt();
        consumer.join();
        assertThat(buffer, is(Arrays.asList(0, 1, 2, 3, 4)));
    }

    @Test
    public void whenAddTwoAndGetTwoTwoTime() throws InterruptedException {
        final CopyOnWriteArrayList<Integer> buffer = new CopyOnWriteArrayList<>();
        final SimpleBlockingQueue<Integer> queue = new SimpleBlockingQueue<>();
        Thread first = new Thread(
                () -> {
                    for (int i = 0; i < 3; i++) {
                        try {
                            queue.offer(i);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
        );
        first.start();
        first.join();
        Thread second = new Thread(
                () -> {
                    for (int i = 0; i < 3; i++) {
                        try {
                            queue.offer(i);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
        );
        Thread third = new Thread(
                () -> {
                    while (!queue.isEmpty() && Thread.currentThread().isInterrupted()) {
                        try {
                            buffer.add(queue.poll());
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                            Thread.currentThread().interrupt();
                        }
                    }
                }
        );
        second.start();
        second.join();
        third.start();
        third.interrupt();
        third.join();
        assertThat(buffer, is(Arrays.asList(0, 1, 2, 0, 1, 2)));
    }
}