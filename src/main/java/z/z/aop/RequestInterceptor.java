package z.z.aop;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;


@Component
public class RequestInterceptor implements HandlerInterceptor {

    private static final Logger LOGGER = LoggerFactory.getLogger(RequestInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        // 在处理请求之前调用，可以用于记录请求信息

        // 获取访问IP
        String clientIp = request.getRemoteAddr();

        // 获取浏览器信息
        String browser = request.getHeader("User-Agent");

        // 获取请求头
//        Enumeration<String> headerNames = request.getHeaderNames();
//        while (headerNames.hasMoreElements()) {
//            String headerName = headerNames.nextElement();
//            String headerValue = request.getHeader(headerName);
//            // 在这里可以记录请求头信息
//            System.out.println(headerValue);
//        }
        //ServletInputStream inputStream = request.getInputStream();
        // 获取请求体
        // 对于POST请求，你可能需要从request.getInputStream()中读取请求体
        // System.out.println(clientIp + ":" + browser);
        LOGGER.info("访问IP:" + clientIp);
        LOGGER.info("访问浏览器:" + browser);
        // 在这里你可以将这些信息记录到日志中或进行其他处理

        return true; // 如果返回false，则请求将被中断
    }

}
