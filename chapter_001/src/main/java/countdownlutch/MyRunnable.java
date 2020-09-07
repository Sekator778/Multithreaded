package countdownlutch;

public class MyRunnable implements Runnable {
    private final CountDownLatchImpl countDownLatch;
    private final int count;

    public MyRunnable(CountDownLatchImpl countDownLatch, int count) {
        this.countDownLatch = countDownLatch;
        this.count = count;
    }

    @Override
    public void run() {
        for (int i = count; i >= 0; i--) {

            countDownLatch.countDown();
            System.out.println(Thread.currentThread().getName() + " has reduced latch count to : " + i);

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}
