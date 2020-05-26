package countdownlatch;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicLong;

/**
 *
 */

public class UseCountDownLatch {
        public static void main(String[] args) {
            CountDownLatch latch = new CountDownLatch(1);
            for (int threadNo = 0; threadNo < 1000; threadNo++) {
                Runnable t = new Test(latch);
                new Thread(t).start();
            }
            try {
                latch.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("done");
        }
    }
    class Test implements Runnable {
        CountDownLatch latch;
        public Test(CountDownLatch latch) {
            this.latch = latch;
        }
        private static AtomicLong number = new AtomicLong(0);
        public long next() {
            return number.getAndIncrement();
        }
        @Override
        public void run() {
            try {
                Thread.sleep(1000 + (int) (Math.random() * 3000));
                System.out.println(next());
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                latch.countDown();
            }
        }
    }