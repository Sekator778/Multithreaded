package blockingqueue;

interface BlockingQueueImpl<T> {
    /**
     * insert element into this queue
     * only if space is available else
     * waits for space to become available.
     */
    void offer(T value) throws InterruptedException;

    /**
     * возвращает элемент из начала очереди с удалением;
     */
    T poll() throws InterruptedException;
}
