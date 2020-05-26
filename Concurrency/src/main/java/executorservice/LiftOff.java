package executorservice;

import java.util.Random;

/**
 *
 */

public class LiftOff implements Runnable {
    protected int countDown = 10;
    private static int taskCount = 0;
    private final int id = taskCount++;

    public LiftOff() {
    }

    public LiftOff(int countDown) {
        this.countDown = countDown;
    }

    public String status() {
        return "#" + id + "( " + (countDown > 0 ? countDown : "Thread end work") + " ), ";
    }

    @lombok.SneakyThrows
    @Override
    public void run() {
        Random random = new Random();
        while (countDown-- > 0) {
            int x = random.nextInt(100);
            System.out.println(String.format("Thread sleep %d mS", x));
            System.out.println(status());
            Thread.sleep(x);
            Thread.yield();
        }
    }
}
