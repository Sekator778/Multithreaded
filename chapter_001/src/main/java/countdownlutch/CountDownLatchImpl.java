package countdownlutch;

public class CountDownLatchImpl {
    private int count;

    public CountDownLatchImpl(int count) {
        this.count = count;
        System.out.println("countDownLatchCreated with count " + count);
    }

    public synchronized void await() throws InterruptedException {
        if (count > 0) {
            this.wait();
            System.out.println(Thread.currentThread().getName() + " wait");

        } else {
            System.out.println("await release");
        }
    }

    public synchronized void countDown() {
        count --;
        if (count == 0) {
            this.notify();
        }
    }
}
