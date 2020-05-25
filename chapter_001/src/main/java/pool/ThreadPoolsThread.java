package pool;

import blockingqueue.SimpleBlockingQueue;

/**
 * Эти потоки создаются и запускаются из конструктора класса ThreadPool.
 */

public class ThreadPoolsThread extends Thread {
    private final SimpleBlockingQueue<Runnable> taskQueue;

    public ThreadPoolsThread(SimpleBlockingQueue<Runnable> taskQueue) {
        this.taskQueue = taskQueue;
    }

    @Override
    public void run() {
        try {
            while (true) {
                System.out.println(Thread.currentThread().getName() + " Is ready to execute task");
//                Thread.sleep(555);
                Runnable runnable = taskQueue.poll();
                System.out.println(Thread.currentThread().getName() + " has poll task");
                runnable.run();
                System.out.println(Thread.currentThread().getName() + " has execute task");
                if (Thread.currentThread().isInterrupted() && this.taskQueue.isEmpty()) {
                    this.interrupt();
                    Thread.sleep(1);
                }
            }
        } catch (InterruptedException e) {
            System.out.println(Thread.currentThread().getName() + " has been STOPPED.");
            this.interrupt();
        }
    }
}
