package test;

import org.json.JSONObject;

import java.util.*;
import java.util.concurrent.*;

/**
 *
 */

public class Util {
//    public Map<Integer, JSONObject> setExecutor(List<Camera> cameraList) {
//        Map<Integer, JSONObject> map = new ConcurrentHashMap<>();
//        ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newCachedThreadPool();
//        cameraList.forEach(camera -> {
//            executor.execute(new Worker(camera, map));
//        });
//        executor.shutdown();
//        return map;
//
//    }

    public void setExecutorFuture(List<Camera> cameraList) throws InterruptedException, ExecutionException {
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
        for (Future<JSONObject> future : results) {
            System.out.println(future.get());
        }
        pool.shutdown();
    }
}
