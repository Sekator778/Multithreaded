package blockingqueue;

public class Consumer implements Runnable {
    private final SimpleBlockingQueue<Integer> queue;

    public Consumer(SimpleBlockingQueue<Integer> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            try {
                System.out.println("Consumer : " + queue.poll());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
