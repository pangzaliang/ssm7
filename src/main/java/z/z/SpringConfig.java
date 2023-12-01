package z.z;

import com.alibaba.fastjson2.JSONObject;
import com.alibaba.fastjson2.support.spring6.http.converter.FastJsonHttpMessageConverter;
import org.springframework.context.annotation.*;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Date;
import java.util.List;

@RestController
@EnableWebMvc // 开启webmvc
@Configuration
@ComponentScan("z.z")
@PropertySource(value = "classpath:application.properties", encoding = "UTF-8") // 加载配置文件
public class SpringConfig implements WebMvcConfigurer {

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

    @Bean
    public MultipartResolver multipartResolver() {
        return new StandardServletMultipartResolver();
    }
}
