package z.z;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import z.z.aop.RequestInterceptor;


// modelAndView
// 注意
// 当controller  被 @RestController 修饰时，只会进行HttpMessageConverter(HTTP消息转换器)判断
// 当            被 @Controller 修饰时，需要配置对应的ModelAndView(视图控制器)
@EnableWebMvc // 开启webmvc
@Configuration
@ComponentScan("z.z")
@PropertySource(value = "classpath:application.properties", encoding = "UTF-8") // 加载配置文件
public class SpringConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new RequestInterceptor());
    }

    @Bean
    public MultipartResolver multipartResolver() {
        return new StandardServletMultipartResolver();
    }

}
