// This implementation is not thread-safe.
// If multiple threads call getInstance() simultaneously when instance is null, it's possible to
// create multiple instances.

import java.util.HashMap;
import java.util.Map;

public class CacheManagerLazyInitialization {

    // The single instance, initally null
    private static CacheManagerLazyInitialization instance;

    private Map<String, String> cache = new HashMap<>();

    // Private constructor to prevent instantiation
    private CacheManagerLazyInitialization() {}

    // Public method to get the instance
    public static CacheManagerLazyInitialization getInstance() {
        if (instance == null) {
            // If null then create a new instance
            instance = new CacheManagerLazyInitialization();
        }

        // If not null then some call already has done instantiation
        return instance;
    }

    public void put(String key, String value) {
        cache.put(key, value);
    }

    public String get(String key) {
        return cache.get(key);
    }
}
