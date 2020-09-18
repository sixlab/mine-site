package tech.minesoft.mine.site.core.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Component;

@Component
public class CacheUtils {
    private static final String KEY = "MsCache";
    private static CacheManager cacheManager;

    @Autowired
    public void setCtx(CacheManager cacheManager) {
        CacheUtils.cacheManager = cacheManager;
    }

    public static void del(String code) {
        Cache cache = cacheManager.getCache(KEY);
        if (cache != null) {
            cache.evict(code);
        }
    }

    public static void put(String code, Object val) {
        Cache cache = cacheManager.getCache(KEY);
        if (cache != null) {
            cache.put(code, val);
        }
    }

    public static String getString(String code) {
        return get(code, String.class);
    }

    public static Integer getInteger(String code) {
        return get(code, Integer.class);
    }

    public static Long getLong(String code) {
        return get(code, Long.class);
    }

    public static <T> T get(String code, Class<T> clz) {
        Cache cache = cacheManager.getCache(KEY);

        if (cache != null) {
            return cache.get(code, clz);
        }
        return null;
    }

}
