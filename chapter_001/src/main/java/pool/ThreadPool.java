package pool;

import blockingqueue.SimpleBlockingQueue;
import net.jcip.annotations.ThreadSafe;

import java.util.LinkedList;
import java.util.List;

/**
 * https://www.baeldung.com/thread-pool-java-and-guava
 * Пул - это хранилища для ресурсов, которые можно переиспользовать.
 * Клиент берет ресурс из пула, выполняет свою работу и возвращается обратно в пул
 */
@ThreadSafe
public class ThreadPool {
    private final List<Thread> threads;
    private final SimpleBlockingQueue<Runnable> tasks;
    private volatile boolean shutdown = false;

    /**
     * constructor start with count thread == count processors
     */

    public ThreadPool(SimpleBlockingQueue<Runnable> tasks) {
        int size = Runtime.getRuntime().availableProcessors();
        threads = new LinkedList<>();
        this.tasks = tasks;
        for (int i = 0; i < size; i++) {
            ThreadPoolsThread thread = new ThreadPoolsThread(tasks);
            threads.add(thread);
        }
    }

    public void work(Runnable task) throws InterruptedException {
        if (shutdown) {
            throw new InterruptedException("ThreadPool has been shutDown, no further tasks can be added");
        }
        tasks.offer(task);
    }

    public void execute() {
        for (Thread thread : threads
        ) {
            thread.start();
        }
    }

    public void shutdown() {
        for (Thread t : threads
        ) {
            t.interrupt();
        }
        shutdown = true;
        System.out.println("ThreadPool SHUTDOWN initiated.");
    }
}
