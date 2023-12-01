package z.z.config;

import com.github.benmanes.caffeine.cache.Caffeine;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.CacheManager;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;

/**
 * Spring缓存配置类
 *
 */
@Configuration
public class SpringCacheConfig {

    @Value("${cache.expireAfterWrite}")
    private Integer expireAfterWrite;
    @Value("${cache.initialCapacity}")
    private Integer initialCapacity;
    @Value("${cache.maximumSize}")
    private Integer maximumSize;

    /**
     * 注册缓存管理器 使用Caffeine缓存管理器
     * @return CacheManager
     */
    @Bean
    public CacheManager cacheManager() {
        CaffeineCacheManager caffeineCacheManager = new CaffeineCacheManager("caffeineCache");
        caffeineCacheManager.setCaffeine(Caffeine.newBuilder()
                .expireAfterWrite(Duration.ofSeconds(expireAfterWrite))
                .initialCapacity(initialCapacity)
                .maximumSize(maximumSize));
        return caffeineCacheManager;
    }

}
