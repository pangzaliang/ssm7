package z.z.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;

@EnableAspectJAutoProxy // 开启spring aop
@Aspect //标记此类为切面类
@Component // 注册为组件
public class AspectjAop {

    // 日志记录
    private static final Logger LOGGER = LoggerFactory.getLogger(AspectjAop.class);

    /**
     * 切入点为controller包下的任意包任意类任意方法
     */
    @Pointcut("execution(* z.z.restcontroller..*.*(..))")
    public void pcController(){

    }

    @Pointcut("execution(* z.z.service..*.*(..))")
    public void pcService(){
    }

    @Pointcut("pcService() || pcController()")
    public void combinedPointcut(){
    }

    /**
     * Controller环绕通知
     * @param point 切入点
     * @return Object
     */
    @Around("pcController()")
    public Object around (ProceedingJoinPoint point) throws Throwable {
        // 开始时间
        long startTime = System.currentTimeMillis();
        // 获取方法参数
        Object[] methodArgs = point.getArgs();
        // 获取方法签名，包括类名、方法名等信息
        String methodName = point.getSignature().toShortString();
        // 方法返回结果
        Object result = null;
        // 方法抛出异常
        Throwable exception = null;
        try {
            // 执行目标方法
            result = point.proceed();
        } catch (Throwable e) {
            exception = e;
            throw e;
        } finally {
            // 结束时间
            long endTime = System.currentTimeMillis();
            // 计算耗时(毫秒)
            long executionTime = endTime - startTime;
            // 若异常为空,则代表方法正常执行
            if (exception == null) {
                LOGGER.info("方法名: " + methodName +
                        ". 方法入参: " +  arrayToString(methodArgs) +
                        ". 方法运行耗时: " + executionTime + "ms" +
                        ". 方法返回结果: " + result);
            }
            // 不为空,则代表方法出现异常
            else {
                LOGGER.error("方法名: " + methodName +
                        ". 方法入参: " +  arrayToString(methodArgs) +
                        ". 方法运行耗时: " + executionTime + "ms" +
                        ". 方法返回结果: " + null +
                        ". 方法抛出异常: ", exception);
            }
        }
        return result;
    }

    /**
     * obj数组转字符串
     * @param array Object[]
     * @return String
     */
    private String arrayToString(Object[] array) {
        if (array == null) {
            return "null";
        }
        StringBuilder sb = new StringBuilder("[");
        for (Object obj : array) {
            sb.append(obj).append(", ");
        }
        if (array.length > 0) {
            sb.setLength(sb.length() - 2); // Remove the trailing comma and space
        }
        sb.append("]");
        return sb.toString();
    }
}
