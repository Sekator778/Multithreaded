package test;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class Test {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Reader reader = new Reader();
        List<Camera> cameras = reader.readCamera();
        Util util = new Util();
        util.setExecutorFuture(cameras);
    }
}