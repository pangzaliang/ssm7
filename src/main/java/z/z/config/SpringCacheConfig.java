package z.z.config;

import org.springframework.cache.CacheManager;
import org.springframework.cache.concurrent.ConcurrentMapCache;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Set;

/**
 * Spring缓存配置类
 * //TODO 还未完成
 */
@Configuration
public class SpringCacheConfig {

    /**
     * 注册缓存管理器 使用简单的缓存管理器 内部使用的是JUC下的ConcurrentHashMap
     * @return CacheManager
     */
    @Bean
    public CacheManager cacheManager() {
        SimpleCacheManager simpleCacheManager = new SimpleCacheManager();
        // 给缓存命名
        simpleCacheManager.setCaches(Set.of(new ConcurrentMapCache("mvc6_cache")));
        return simpleCacheManager;
    }

}
