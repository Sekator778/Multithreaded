package nonblocking;


import net.jcip.annotations.ThreadSafe;

import java.util.concurrent.atomic.AtomicReference;

/**
 * https://coderlessons.com/tutorials/java-tekhnologii/izuchite-java-parallelizm/parallelizm-java-klass-atomicreference
 * <p>
 * https://codereview.stackexchange.com/questions/224/thread-safe-and-lock-free-queue-implementation
 */
@ThreadSafe
public class CASQueue<T> {
    private final Node<T> dummy = new Node<>(null);
    private final AtomicReference<Node<T>> head = new AtomicReference<>(dummy);
    private final AtomicReference<Node<T>> tail = new AtomicReference<>(dummy);

    /**
     * put object into tail queue
     */
    public void push(T value) {
        if (value == null) {
            throw new NullPointerException();
        }
        Node<T> node = new Node<>(value);
        Node<T> prevTail = tail.getAndSet(node);
        /*Атомно устанавливается на заданное значение и возвращает предыдущее значение.
         * т.е.1. в  prevTail ставим значение текущего хвоста 2. и сразу же в хвост ставим нод */
        prevTail.next = node;                    // в prevTail.некст ставим нода добавленного
    }

    /**
     * get object from head queue
     *
     * @return object
     */
    public T poll() {
        Node<T> tNode, next;
        /* перемещаем головной узел к следующему узлу, используя атомарную семантику
         до тех пор, пока следующий узел не является нулевым */
        do {
            tNode = head.get();
            next = tNode.next;
            if (next == null) {
                return null;
            }
/*  public boolean compareAndSet (ожидание, обновление V)
Атомно устанавливает значение для данного обновленного значения, если текущее значение == ожидаемое значение.
 пытаемся, пока весь цикл не будет выполнен псевдоатомно
         (т.е. не зависит от изменений, сделанных другими потоками)*/
        } while (!head.compareAndSet(tNode, next));
        T value = next.value;
        next.value = null;
        return value;
    }

    private static final class Node<T> {
        volatile T value; // final == volatile
        volatile Node<T> next;

        public Node(final T value) {
            this.value = value;
        }
    }
}