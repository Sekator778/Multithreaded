package executorservice;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 *
 */

public class CacheTreadPool {
    public static void main(String[] args) {
//        ExecutorService service = Executors.newCachedThreadPool();
//        ExecutorService service = Executors.newFixedThreadPool(5);
        int x = Runtime.getRuntime().availableProcessors();
        System.out.println(String.format("your PC have %d processors", x));
        ExecutorService service = Executors.newFixedThreadPool(x);
        /* SingleThreadExecutor() - one thread and have private queue */
//        ExecutorService service = Executors.newSingleThreadExecutor();
        for (int i = 0; i < 5; i++) {
            service.execute(new LiftOff());
        }
        service.shutdown();
    }
}
