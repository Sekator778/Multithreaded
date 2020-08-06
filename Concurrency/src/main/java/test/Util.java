package test;

import org.json.JSONObject;

import java.util.*;
import java.util.concurrent.*;

/**
 *
 */

public class Util {
    public List<JSONObject> setExecutorFuture(List<Camera> cameraList) throws InterruptedException, ExecutionException {
        ExecutorService pool = Executors.newCachedThreadPool();
        List<Callable<JSONObject>> tasks = new ArrayList<>();
        cameraList.forEach(camera -> {
            tasks.add(new Callable<JSONObject>() {
                public JSONObject call() throws Exception {
                    Worker worker = new Worker(camera);
                    return worker.call();
                }
            });
        });
        List<Future<JSONObject>> results = pool.invokeAll(tasks);
        List<JSONObject> objects = new ArrayList<>();
        for (Future<JSONObject> future : results) {
            objects.add(future.get());
            System.out.println(future.get());
        }
        pool.shutdown();
        return objects;
    }
}
