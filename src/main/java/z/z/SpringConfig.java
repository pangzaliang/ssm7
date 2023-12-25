package z.z;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import z.z.aop.RequestInterceptor;
import z.z.service.PDFView;
import z.z.service.PdfViewResolver;


//TODO modelAndView
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

    @Bean
    public PDFView pdfView () {
        return new PDFView();
    }

    @Bean
    public ViewResolver pdfViewResolver() {
        return new PdfViewResolver();
    }
}
