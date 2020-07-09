package test;

import org.json.JSONObject;

import java.util.List;
import java.util.Map;
import java.util.concurrent.*;

/**
 *
 */

public class Util {
    public Map<Integer, JSONObject> setExecutor(List<Camera> cameraList) {
        Map<Integer, JSONObject> map = new ConcurrentHashMap<>();
        ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newCachedThreadPool();
        cameraList.forEach(camera -> {
            executor.execute(new Worker(camera, map));
        });
        executor.shutdown();
        return map;

    }
}
