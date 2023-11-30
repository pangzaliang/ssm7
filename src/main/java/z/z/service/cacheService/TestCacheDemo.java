package z.z.service.cacheService;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class TestCacheDemo {

    @Cacheable(value = "mvc6_cache")
    public List<String> list () {
        System.out.println("获取文章列表!");
        return Arrays.asList("spring", "mysql", "java高并发", "maven");
    }

    @Cacheable(value = "mvc6_cache")
    public List<String> list2 () {
        System.out.println("获取文章列表!");
        return Arrays.asList("spring", "mysql", "java高并发", "maven");
    }
}
