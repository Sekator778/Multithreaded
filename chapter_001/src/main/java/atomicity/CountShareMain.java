package atomicity;

/**
 * Класс с двумя нитями, которые увеличивают счетчик.
 */

public class CountShareMain {
    public static void main(String[] args) throws InterruptedException {
        Count count = new Count();
        for (int i = 1; i < 1001; i++) {
            System.out.println("i = " + i);

            Thread first = new Thread(count::increment);
            Thread second = new Thread(count::increment);
            first.start();
            second.start();
            first.join();
            second.join();
            System.out.println("increment count.get() " + count.get());
        }
        Thread.sleep(950);

        for (int i = 1; i < 1001; i++) {
            System.out.println("i = " + i);

            Thread first = new Thread(count::increment);
            Thread second = new Thread(count::increment);
            first.start();
            second.start();
            first.join();
            second.join();
            System.out.println("decrement count.get() " + count.get());
        }
    }
}