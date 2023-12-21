package z.z.config;

import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.client5.http.impl.io.PoolingHttpClientConnectionManagerBuilder;
import org.apache.hc.client5.http.io.HttpClientConnectionManager;
import org.apache.hc.client5.http.ssl.SSLConnectionSocketFactory;
import org.apache.hc.client5.http.ssl.SSLConnectionSocketFactoryBuilder;
import org.apache.hc.client5.http.ssl.TrustAllStrategy;
import org.apache.hc.core5.ssl.SSLContexts;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import javax.net.ssl.SSLContext;
import java.nio.charset.StandardCharsets;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
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
    public RestTemplate restTemplate(HttpComponentsClientHttpRequestFactory httpRequestFactory){
        RestTemplate restTemplate = new RestTemplate(httpRequestFactory);
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
     * 注册Http 组件客户端 Http 请求工厂
     * @return JdkClientHttpRequestFactory
     */
    @Bean
    public HttpComponentsClientHttpRequestFactory clientHttpRequestFactory() throws NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
        HttpComponentsClientHttpRequestFactory httpRequestFactory = new HttpComponentsClientHttpRequestFactory();

        // SSL 上下文
        SSLContext sslcontext = SSLContexts.custom()
                // 接受所有证书为可信地信任策略
                .loadTrustMaterial(null, new TrustAllStrategy())
                .build();
        // SSL 连接套接字工厂
        SSLConnectionSocketFactory sslSocketFactory = SSLConnectionSocketFactoryBuilder.create()
                // 设置 SSL 上下文
                .setSslContext(sslcontext)
                .build();
        // 池化 Http 客户端连接管理器生成器
        HttpClientConnectionManager cm = PoolingHttpClientConnectionManagerBuilder.create()
                // 设置SSL套接字工厂
                .setSSLSocketFactory(sslSocketFactory)
                .build();
        // 构造可关闭的Http客户端
        CloseableHttpClient httpClient = HttpClients.custom()
                // 设置连接管理器
                .setConnectionManager(cm)
                .build();
        // 设置连接超时
        httpRequestFactory.setConnectTimeout(Duration.ofMinutes(restTemplateReadTimeOut));
        // 设置连接请求超时
        httpRequestFactory.setConnectionRequestTimeout(Duration.ofMinutes(restTemplateReadTimeOut));
        // 设置Http客户端
        httpRequestFactory.setHttpClient(httpClient);
        // 返回
        return httpRequestFactory;
    }
}
