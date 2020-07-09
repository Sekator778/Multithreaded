package test;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * read input data with Url
 */

public class Reader  {
    public List<Camera> readCamera() {
        List<Camera> result = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        try {
            URL url = new URL("http://www.mocky.io/v2/5c51b9dd3400003252129fb5");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            in.lines().forEach(sb::append);
            String json = sb.toString();
            JSONArray jsonArray = new JSONArray(json);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObj = jsonArray.getJSONObject(i);
                int id = jsonObj.getInt("id");
                String sourceDataUrl = (String) jsonObj.get("sourceDataUrl");
                String tokenDataUrl = (String) jsonObj.get("tokenDataUrl");
                result.add(new Camera(id, sourceDataUrl, tokenDataUrl));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
