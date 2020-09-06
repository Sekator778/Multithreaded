package countdownlutch;

import org.junit.Test;

import static org.junit.Assert.*;

public class CountDownLatchImplTest {


    @Test
    public void test() {
        int count = 3;
        CountDownLatchImpl countDownLatch = new CountDownLatchImpl(count);
        new Thread(new MyRunnable(countDownLatch, count), "Thread-1").start();

        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("count has reached zero, " +
                Thread.currentThread().getName() + " thread has ended");
    }

    @Test
    public void twoThread() {
        CountDownLatchImpl countDownLatch = new CountDownLatchImpl(2);
        Thread first = new Thread(
                () -> {
                    countDownLatch.countDown();
                    try {
                        countDownLatch.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName() + " started");
                }
        );
        Thread second = new Thread(
                () -> {
                    countDownLatch.countDown();
                    try {
                        countDownLatch.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName() + " started");
                }
        );
        first.start();
        second.start();
        try {
            first.join();
            second.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
            first.interrupt();
            second.interrupt();
        }
    }
}