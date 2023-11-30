package z.z;

import com.alibaba.fastjson2.JSONObject;
import com.alibaba.fastjson2.support.spring6.http.converter.FastJsonHttpMessageConverter;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCache;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.cache.interceptor.SimpleKeyGenerator;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.http.MediaType;
import org.springframework.http.client.JdkClientHttpRequestFactory;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

@RestController
@EnableWebMvc // 开启webmvc
@Configuration
@ComponentScan("z.z")
@EnableScheduling //启动定时任务
@EnableAsync //启动异步任务
@EnableAspectJAutoProxy // 开启spring aop
@EnableCaching //开启spring 缓存
public class SpringConfig implements WebMvcConfigurer, SchedulingConfigurer {


    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        converters.add(jsonHttpMessageConverter());
    }

    /**
     * 配置jsonHttp消息转换器
     * @return HttpMessageConverter
     */
    @Bean
    public HttpMessageConverter<Object> jsonHttpMessageConverter() {
        FastJsonHttpMessageConverter converter = new FastJsonHttpMessageConverter();
        converter.setSupportedMediaTypes(List.of(MediaType.APPLICATION_JSON));
        return converter;
    }

    @Override
    public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
        taskRegistrar.setScheduler(taskSExecutor());
    }

    @Bean
    public Executor taskSExecutor() {
        return Executors.newScheduledThreadPool(8);
    }

    @Bean
    public CacheManager cacheManager() {
        SimpleCacheManager simpleCacheManager = new SimpleCacheManager();
        simpleCacheManager.setCaches(Set.of(new ConcurrentMapCache("mvc6_cache")));
        return simpleCacheManager;
    }

    @Bean
    public KeyGenerator keyGenerator() {
        return new SimpleKeyGenerator();
    }

    /**
     * 配置默认的加载返回
     * @return JSONObject
     */
    @GetMapping("/")
    public JSONObject hi () {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("time", new Date());
        jsonObject.put("name,", "ssm6");
        jsonObject.put("version,", "1.0");
        return jsonObject;
    }

    /**
     * 注册RestTemplate组件 使用JDK11新功能HttpClient
     * @return RestTemplate
     */
    @Bean
    public RestTemplate restTemplate(){
        RestTemplate restTemplate = new RestTemplate(clientHttpRequestFactory());
        // 配置StringHttpMessageConverter,使其默认的字符集为UTF-8
        List<HttpMessageConverter<?>> messageConverters = restTemplate.getMessageConverters();
        for (HttpMessageConverter<?> messageConverter : messageConverters) {
            if (messageConverter instanceof StringHttpMessageConverter) {
                ((StringHttpMessageConverter) messageConverter).setDefaultCharset(StandardCharsets.UTF_8);
            }
        }
        return restTemplate;
    }

    /**
     * 注册JDK11HttpClient请求工厂
     * @return JdkClientHttpRequestFactory
     */
    @Bean
    public JdkClientHttpRequestFactory clientHttpRequestFactory(){
        JdkClientHttpRequestFactory httpRequestFactory = new JdkClientHttpRequestFactory();
        // 设置超时时间为3分钟
        httpRequestFactory.setReadTimeout(Duration.ofMinutes(3));
        return httpRequestFactory;
    }

}
