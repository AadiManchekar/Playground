import java.util.HashMap;
import java.util.Map;

public class CacheManagerDoubleCheckedLocking {
    // The single instance, initially null, marked as volatile
    // volatile ensures all threads see the same, fully constructed instance.
    private static volatile CacheManagerDoubleCheckedLocking instance;
    private Map<String, String> cache = new HashMap<>();

    private CacheManagerDoubleCheckedLocking() {}

    public static CacheManagerDoubleCheckedLocking getInstance() {
        // First check (not synchronized)
        if(instance == null) {
            // synchronized block
            // synchronized block ensures only one thread creates the instance.
            // You can synchronize any block of code using synchronized (lockObject) { ... }.
            // So, synchronize on class object
            synchronized (CacheManagerDoubleCheckedLocking.class) {
                // Second check (synchronized)
                if(instance == null) {
                    // Create the new instance
                    instance = new CacheManagerDoubleCheckedLocking();
                }
            }
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
