package cache.test;

import java.util.concurrent.ConcurrentHashMap;

/**
 *
 */

public class Main {
    public static void main(String[] args) {
        ConcurrentHashMap<String, String> map = new ConcurrentHashMap<>();
        System.out.println(map.put("one", "1"));
        System.out.println("twice put " + map.put("one", "1"));
        System.out.println(map.put("two", "2"));
        System.out.println(map.computeIfPresent("one", (key, value) -> key + " add to key, " + value + " add to value"));
        map.computeIfAbsent("awesome key", key -> key + ", " + "amazing value");
        System.out.println(map.get("awesome key")); //output: awesome key, amazing value
        map.put("awesome key", "cool value");
        map.computeIfAbsent("awesome key", key -> key + ", " + "amazing value");
        System.out.println(map.get("awesome key")); //output: cool value
    }
}
