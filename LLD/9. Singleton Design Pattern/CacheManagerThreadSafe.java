import java.util.HashMap;
import java.util.Map;

public class CacheManagerThreadSafe {
    private static CacheManagerThreadSafe instance;
    private Map<String, String> cache = new HashMap<>();

    private CacheManagerThreadSafe() {}

    // making the getInstance() method synchronized ensuring only one thread can execute this method
    // at a time.
    // When a thread enters the synchronized method, it acquires a lock on the class object.
    // Other threads must wait until the method is executed.
    // The synchronization keyword ensures that only one thread can perform the (instance == null)
    // check and create the object.
    // Using synchronized can decrease performance, which can be a bottleneck if called frequently.
    public static synchronized CacheManagerThreadSafe getInstance() {
        if (instance == null) {
            instance = new CacheManagerThreadSafe();
        }

        return instance;
    }

    public void put(String key, String value) {
        cache.put(key, value);
    }

    public String get(String key) {
        return cache.get(key);
    }
}
