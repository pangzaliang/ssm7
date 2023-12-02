package z.z.config;

import com.alibaba.fastjson2.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.util.Date;

@RestController
@RestControllerAdvice
public class CustomExceptionHandler {

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

    @ExceptionHandler(NoHandlerFoundException.class)
    public ResponseEntity<String> notFound404 () {
        return new ResponseEntity<>("404", HttpStatus.NOT_FOUND);
    }
}
