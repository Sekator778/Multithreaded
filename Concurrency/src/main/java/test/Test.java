package test;

import org.json.JSONObject;

import java.util.List;
import java.util.Map;

public class Test {
    public static void main(String[] args) {
        Reader reader = new Reader();
        List<Camera> cameras = reader.readCamera();
        Util util = new Util();
        Map<Integer, JSONObject> result = util.setExecutor(cameras);
        result.forEach((key, value) -> {
            System.out.println("Key : " + key + " Value : " + value);
        });
    }
}