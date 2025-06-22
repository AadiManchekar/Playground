public class Main {
    public static void main(String[] args) {

        String output;

        // Usage of CacheManagerLazyInitialization
        CacheManagerLazyInitialization cache1 = CacheManagerLazyInitialization.getInstance();
        cache1.put("name", "Aadi");
        output = cache1.get("name");
        System.out.println(output);

        // Usage of CacheManagerThreadSafe
        CacheManagerThreadSafe cache2 = CacheManagerThreadSafe.getInstance();
        cache2.put("name", "Hello");
        output = cache2.get("name");
        System.out.println(output);

        // Usage of CacheManagerDoubleCheckedLocking
        CacheManagerDoubleCheckedLocking cache3 = CacheManagerDoubleCheckedLocking.getInstance();
        cache3.put("name", "World");
        output = cache3.get("name");
        System.out.println(output);

        // Usage of CacheManagerEagerInitialization
        CacheManagerEagerInitialization cache4 = CacheManagerEagerInitialization.getInstance();
        cache4.put("name", "Basic");
        output = cache4.get("name");
        System.out.println(output);

        // Usage of CacheManagerEnum 
        CacheManagerEnum.INSTANCE.put("name", "Programming");
        output = CacheManagerEnum.INSTANCE.get("name");
        System.out.println(output);

    }
}
