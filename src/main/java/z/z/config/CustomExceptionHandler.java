package z.z.config;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;
import z.z.util.ResultVo;


/**
 * 自定义异常处理程序
 */
@RestController
@RestControllerAdvice
public class CustomExceptionHandler {

//    /**
//     * 配置默认的加载返回
//     * @return JSONObject
//     */
//    @GetMapping("/")
//    public ResultVo home() {
//        JSONObject jsonObject = new JSONObject();
//
//        JsonMapper jsonMapper = new JsonMapper();
//        jsonMapper.
//        jsonObject.put("time", new Date());
//        jsonObject.put("name", "ssm6");
//        jsonObject.put("version", "1.0");
//        return ResultVo.successResult(jsonObject);
//    }

    /**
     * 处理DispatcherServlet找不到请求的处理程序异常
     * @return ResponseEntity
     */
    @ExceptionHandler(NoHandlerFoundException.class)
    public ResponseEntity<ResultVo<Object>> notFound404() {
        return new ResponseEntity<>(ResultVo.failResult("请求的接口不存在!"), HttpStatus.NOT_FOUND);
    }
}
