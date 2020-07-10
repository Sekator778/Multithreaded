package test;

import org.json.JSONObject;
import org.junit.Ignore;
import org.junit.Test;

import java.util.List;
import java.util.concurrent.ExecutionException;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class UtilTest {
    @Ignore
    public void test() throws ExecutionException, InterruptedException {
        Reader reader = new Reader();
        List<Camera> cameras = reader.readCamera();
        Util util = new Util();
        List<JSONObject> result = util.setExecutorFuture(cameras);
        JSONObject object = new JSONObject("{\"urlType\":\"LIVE\",\"videoUrl\":\"rtsp://127.0.0.1/1\",\"id\":1,\"value\":\"fa4b588e-249b-11e9-ab14-d663bd873d93\",\"ttl\":120}");
        assertThat(result.get(0), is(object));
    }


}