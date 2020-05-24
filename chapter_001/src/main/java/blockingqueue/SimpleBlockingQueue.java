package blockingqueue;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.LinkedList;
import java.util.Queue;

@ThreadSafe
public class SimpleBlockingQueue<T> implements BlockingQueueImpl<T> {

    @GuardedBy("this")
    private final Queue<T> queue;
    private final int maxSize;

    public SimpleBlockingQueue(int maxSize) {
        this.queue = new LinkedList<>();
        this.maxSize = maxSize;
    }

    public SimpleBlockingQueue() {
        this.queue = new LinkedList<>();
        this.maxSize = Integer.MAX_VALUE;
    }

    /**
     * вставляет заданный элемент в эту очередь , если можно так сразу сделать , не нарушая ограничения емкости
     */
    @Override
    public synchronized void offer(T value) throws InterruptedException {
        if (queue.size() == maxSize) {
            this.wait();
        }
        queue.add(value);
        this.notify();
    }

    /**
     * возвращает элемент из начала очереди с удалением;
     */
    @Override
    public synchronized T poll() throws InterruptedException {
        if (queue.size() == 0) {
            this.wait();
        }
        this.notify();
        return queue.remove();
    }

    @Override
    public boolean isEmpty() {
        return queue.isEmpty();
    }
}