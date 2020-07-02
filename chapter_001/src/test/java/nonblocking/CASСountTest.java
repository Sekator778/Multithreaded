package nonblocking;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class CASСountTest {

    @Test
    public void increment() throws InterruptedException {
        for (int x = 0; x < 10; x++) {

            CASСount<Integer> casСount = new CASСount<>();
            Thread first = new Thread(
                    () -> {
                        for (int i = 0; i < 2; i++) {
                            casСount.increment();
                        }
                    }
            );
            Thread second = new Thread(
                    () -> {
                        for (int i = 0; i < 5; i++) {
                            casСount.increment();
                        }
                    }
            );
            Thread three = new Thread(
                    () -> {
                        for (int i = 0; i < 7; i++) {
                            casСount.increment();
                        }
                    }
            );
            first.start();
            second.start();
            three.start();
            first.join();
            second.join();
            three.join();

            assertThat(casСount.get(), is(14));
        }
    }
}
