package test;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ThreadingExample {

    public static void main(String[] args) {
        ExecutorService service = Executors.newCachedThreadPool();
        Set<Callable<String>> callables = new HashSet<Callable<String>>();
        callables.add(new Callable<String>() {
            @Override
            public String call() throws Exception {
                return "This is where I make the call to web service A, and put its results here";
            }
        });
        callables.add(new Callable<String>() {
            @Override
            public String call() throws Exception {
                return "This is where I make the call to web service B, and put its results here";
            }
        });
        callables.add(new Callable<String>() {
            @Override
            public String call() throws Exception {
                return "This is where I make the call to web service C, and put its results here";
            }
        });
        try {
            List<Future<String>> futures = service.invokeAll(callables);
            for (Future<String> future : futures) {
                System.out.println(future.get());
            }
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }
}