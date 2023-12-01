package z.z.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.JdkClientHttpRequestFactory;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.List;

/**
 * RestTemplate配置类
 */
@Configuration
public class RestTemplateConfig {

    @Value("${restTemplate.readTimeOut}")
    private Integer restTemplateReadTimeOut;

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
        httpRequestFactory.setReadTimeout(Duration.ofMinutes(restTemplateReadTimeOut));
        return httpRequestFactory;
    }
}
