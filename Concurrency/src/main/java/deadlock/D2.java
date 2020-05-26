package deadlock;

import lombok.SneakyThrows;

/**
 * jps
 * jstack
 *  jconsole
 */

public class D2 {
    public static final Object LOCK_1 = new Object();
    public static final Object LOCK_2 = new Object();

    public static void main(String[] args) throws InterruptedException {
        ThreadDemo1 first = new ThreadDemo1();
        ThreadDemo2 second = new ThreadDemo2();
        first.start();
        second.start();
        first.join();
        second.join();
    }

    private static class ThreadDemo1 extends Thread {
        @SneakyThrows
        public void run() {
            synchronized (LOCK_1) {
                System.out.println("Thread 1: Holding lock 1...");
                try {
                    Thread.sleep(10);
                } catch (InterruptedException ignored) {
                }
                System.out.println("Thread 1: Waiting for lock 2...");
                System.out.println("Thread 1: делает wait on lock1 ...");
                LOCK_1.wait();
                synchronized (LOCK_2) {
                    System.out.println("Thread 1: Holding lock 1 & 2...");
                }
            }
        }
    }

    private static class ThreadDemo2 extends Thread {
        @SneakyThrows
        public void run() {
            synchronized (LOCK_2) {
                System.out.println("Thread 2: Holding lock 2...");
                try {
                    Thread.sleep(10);
                } catch (InterruptedException ignored) {
                }
                System.out.println("Thread 2: Waiting for lock 1...");
                synchronized (LOCK_1) {
                    Thread.sleep(2000);
                    System.out.println("Thread 2: Holding lock 1 & 2...");
                }
            }
        }
    }
}