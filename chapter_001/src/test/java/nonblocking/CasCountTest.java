package nonblocking;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class CasCountTest {

    @Test
    public void increment() throws InterruptedException {
        for (int x = 0; x < 10; x++) {

            CasCount<Integer> casCountInteger = new CasCount<>();
            Thread first = new Thread(
                    () -> {
                        for (int i = 0; i < 2; i++) {
                            casCountInteger.increment();
                        }
                    }
            );
            Thread second = new Thread(
                    () -> {
                        for (int i = 0; i < 5; i++) {
                            casCountInteger.increment();
                        }
                    }
            );
            Thread three = new Thread(
                    () -> {
                        for (int i = 0; i < 7; i++) {
                            casCountInteger.increment();
                        }
                    }
            );
            first.start();
            second.start();
            three.start();
            first.join();
            second.join();
            three.join();

            assertThat(casCountInteger.get(), is(14));
        }
    }
}
