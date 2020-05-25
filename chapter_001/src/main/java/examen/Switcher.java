package examen;

/**
 * Нить А печатает первой, нить В всегда второй. Нити работают вечно.
 * Для реализации работы нужно только метод wait, notify, notifyAll.
 */

public class Switcher {
    public static void main(String[] args) throws InterruptedException {
        final Object lock = new Object();
        Thread first = new Thread(
                () -> {
                    while (true) {
                        synchronized (lock) {
                            try {
                                lock.wait();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            System.out.println("Thread A");
                            try {
                                Thread.sleep(1000);
                                lock.notifyAll();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
        );
        Thread second = new Thread(
                () -> {
                    System.out.println("first state " + first.getState());
                    while (true) {
                        synchronized (lock) {
                            lock.notifyAll();
                            try {
                                lock.wait();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            System.out.println("Thread B");
                            try {
                                Thread.sleep(1000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
        );
        second.start(); // без разницы кто 1й
        first.start();
        first.join();
        second.join();
    }
}