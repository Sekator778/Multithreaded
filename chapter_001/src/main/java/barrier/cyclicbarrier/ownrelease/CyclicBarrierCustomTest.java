package barrier.cyclicbarrier.ownrelease;

/**
 * Main class
 */
public class CyclicBarrierCustomTest {
    public static void main(String[] args) {
        /*
         * Create CountDownLatch with 3 parties, when all 3 parties
         * will reach common barrier point CyclicBarrierEvent will be
         * triggered i.e. run() method of CyclicBarrierEvent will be called
         */
        CyclicBarrierCustom cyclicBarrierCustom = new CyclicBarrierCustom(3, new CyclicBarrierEvent());
        System.out.println("CountDownLatch has been created with parties=3,"
                + " when all 3 parties will reach common barrier point "
                + "CyclicBarrierEvent will be triggered");

        MyRunnable myRunnable1 = new MyRunnable(cyclicBarrierCustom);

        //Create and start 3 threads
        new Thread(myRunnable1, "Thread-1").start();
        new Thread(myRunnable1, "Thread-2").start();
        new Thread(myRunnable1, "Thread-3").start();
    }
}