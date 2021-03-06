package test;


import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.*;
import java.util.concurrent.Callable;

/**
 *
 */

public class Worker implements Callable {
    private final int id;
    private final String sourceDataUrl;
    private final String tokenDataUrl;
    private  Map<Integer, JSONObject> map;

    public Worker(Camera camera) {
        id = camera.getId();
        sourceDataUrl = camera.getSourceDataUrl();
        tokenDataUrl = camera.getTokenDataUrl();
//        this.map = map;
    }

    @Override
    public JSONObject call() {
        JSONObject object = answer();
        return object;
    }

    private JSONObject getJSON(String urlName) {
        JSONObject result = null;
        StringBuilder sb = new StringBuilder();
        try {
            URL url = new URL(urlName);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            in.lines().forEach(sb::append);
            String json = sb.toString();
            result = new JSONObject(json);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * iterator ???
     */
    private JSONObject answer() {
        JSONObject one = new JSONObject();
        one.put("id", id);
        JSONObject two = getJSON(sourceDataUrl);
        JSONObject three = getJSON(tokenDataUrl);
        JSONObject merged = new JSONObject();
        JSONObject[] objs = new JSONObject[]{one, two, three};
        for (JSONObject obj : objs) {
            Iterator it = obj.keys();
            while (it.hasNext()) {
                String key = (String) it.next();
                merged.put(key, obj.get(key));
            }
        }
        return merged;
    }
}
