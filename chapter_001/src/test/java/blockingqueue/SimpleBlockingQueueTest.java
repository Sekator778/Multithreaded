package blockingqueue;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class SimpleBlockingQueueTest {

    @Test
    public void whenOfferAndPoll10Element() throws InterruptedException {
        SimpleBlockingQueue<Integer> queue = new SimpleBlockingQueue<>(10);
        Producer producer = new Producer(queue);
        Consumer consumer = new Consumer(queue);
        Thread producerThread = new Thread(producer, "ThreadProducer");
        Thread consumerThread = new Thread(consumer, "ConsumerProducer");
        producerThread.start();
        consumerThread.start();
        producerThread.join();
        consumerThread.join();
    }

    @Test
    public void whenProducerThreadStateWaitingAndAfterConsumerThreadMakePollStateChange() throws InterruptedException {
        SimpleBlockingQueue<Integer> queue = new SimpleBlockingQueue<>(3);
        Thread producerThread = new Thread(
                () -> {
                    try {
                        queue.offer(10);
                        queue.offer(20);
                        queue.offer(30);
                        assertThat(Thread.currentThread().getState(), is(Thread.State.RUNNABLE));
                        queue.offer(23);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }, "Producer"
        );
        Thread consumerThread = new Thread(
                () -> {
                    try {
                        Thread.sleep(20);
                        assertThat(producerThread.getState(), is(Thread.State.WAITING));
                        queue.poll();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }, "ConsumerProducer");
        producerThread.start();
        consumerThread.start();
        producerThread.join();
        consumerThread.join();
    }

    @Test
    public void whenConsumerStateWaitingAndAfterProducerThreadMakeOfferStateChange() throws InterruptedException {
        SimpleBlockingQueue<Integer> queue = new SimpleBlockingQueue<>(3);
        Thread consumerThread = new Thread(
                () -> {
                    try {
                        queue.poll();
                        assertThat(Thread.currentThread().getState(), is(Thread.State.RUNNABLE));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }, "ConsumerProducer");
        Thread producerThread = new Thread(
                () -> {
                    try {
                        Thread.sleep(20);
                        assertThat(consumerThread.getState(), is(Thread.State.WAITING));
                        queue.offer(30);
                        assertThat(Thread.currentThread().getState(), is(Thread.State.RUNNABLE));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }, "Producer"
        );
        consumerThread.start();
        producerThread.start();
        producerThread.join();
        consumerThread.join();
    }

    @Test
    public void when2TimeProducerAndOneConsumer() throws InterruptedException {
        SimpleBlockingQueue<Integer> queue = new SimpleBlockingQueue<>(10);
        Producer producer1 = new Producer(queue);
        Producer producer2 = new Producer(queue);
        Consumer consumer = new Consumer(queue);
        Thread producerThread = new Thread(producer1, "ThreadProducerOne");
        Thread producerThread2 = new Thread(producer2, "ThreadProducerTwo");
        Thread consumerThread = new Thread(consumer, "ConsumerProducer");
        producerThread.start();
        producerThread2.start();
        consumerThread.start();
        producerThread.join();
        consumerThread.join();
        producerThread2.join();
    }

    @Test
    public void whenFirstConsumer() throws InterruptedException {
        SimpleBlockingQueue<Integer> queue = new SimpleBlockingQueue<>(10);
        Producer producer1 = new Producer(queue);
        Consumer consumer = new Consumer(queue);
        Thread producerThread = new Thread(producer1, "ThreadProducerOne");
        Thread consumerThread = new Thread(consumer, "ConsumerProducer");
        consumerThread.start();
        producerThread.start();
        producerThread.join();
        consumerThread.join();
    }
}