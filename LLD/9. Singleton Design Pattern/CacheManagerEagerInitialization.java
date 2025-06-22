// This implementation is one of the simplest and inherently thread-safe without needing explicit synchronization.

import java.util.HashMap;
import java.util.Map;

public class CacheManagerEagerInitialization {
    // static variable ensures there's only one instance shared across all instances of the class.
    // final prevents the instance from being reassigned after initialization.
    private static final CacheManagerEagerInitialization instance = new CacheManagerEagerInitialization();
    private Map<String, String> cache = new HashMap<>();

    private CacheManagerEagerInitialization() {}

    public static CacheManagerEagerInitialization getInstance() {
        return instance;
    }

    public void put(String key, String value) {
        cache.put(key, value);
    }

    public String get(String key) {
        return cache.get(key);
    }
}
