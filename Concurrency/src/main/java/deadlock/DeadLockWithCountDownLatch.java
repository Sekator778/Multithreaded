package deadlock;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 *
 */

public class DeadLockWithCountDownLatch {
        public static void main(String[] args) {
            Object obj1 = new Object();
            Object obj2 = new Object();
            CountDownLatch latch = new CountDownLatch(3);
            new Locker(obj1, obj2, latch).start();
            new Locker(obj2, obj1, latch).start();
        }
    }

    class Locker extends Thread {
        CountDownLatch latch;
        final Object obj1;
        final Object obj2;

        Locker(Object obj1, Object obj2, CountDownLatch latch) {
            this.obj1 = obj1;
            this.obj2 = obj2;
            this.latch = latch;
        }

        @Override
        public void run() {
            synchronized (obj1) {
                latch.countDown();
                try {
                    latch.await(3000, TimeUnit.MILLISECONDS);
                    System.out.println(latch.toString());
                } catch (Exception e) {
                    e.printStackTrace();
                }
                synchronized (obj2) {
                    System.out.println("Thread finished");
                }
            }
        }
    }