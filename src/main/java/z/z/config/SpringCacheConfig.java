package z.z.config;

import com.github.benmanes.caffeine.cache.Caffeine;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;

/**
 * Spring缓存配置类
 *
 */
@EnableCaching //开启spring 缓存
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
        // 创建名称为mvc6_cache的Caffeine缓存管理器
        CaffeineCacheManager caffeineCacheManager = new CaffeineCacheManager("mvc6_cache");
        caffeineCacheManager.setCaffeine(Caffeine.newBuilder()
                // 设置缓存的过期时间(秒)
                .expireAfterWrite(Duration.ofSeconds(expireAfterWrite))
                // 设置缓存初始大小
                .initialCapacity(initialCapacity)
                // 设置缓存最大值
                .maximumSize(maximumSize));
        return caffeineCacheManager;
    }

}
