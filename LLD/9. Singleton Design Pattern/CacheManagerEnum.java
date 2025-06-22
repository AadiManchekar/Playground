// Java ensures that only one instance of an enum value is created, even in a multithreaded environment.
// The Enum Singleton pattern is the most robust and concise way to implement a singleton in Java.
import java.util.HashMap;
import java.util.Map;

public enum CacheManagerEnum {
    INSTANCE;

    private Map<String, String> cache = new HashMap<>();

    public void put(String key, String value) {
        cache.put(key, value);
    }

    public String get(String key) {
        return cache.get(key);
    }
}
