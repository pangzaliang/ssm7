package z.z.service.taskService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.concurrent.CountDownLatch;

@Service
public class TaskServiceDemo {

    private static final Logger LOGGER = LoggerFactory.getLogger(TaskServiceDemo.class);

    @Async
    public void asyncMethod(CountDownLatch count) throws InterruptedException {
        LOGGER.info("异步执行的方法逻辑");
        Thread.sleep(500);
        count.countDown();
    }

//    @Scheduled(cron = "* * * * * *", zone = "GMT+8")
//    public void scheduledMethod1() throws InterruptedException {
//        LOGGER.info("定时任务的方法逻辑1111");
//        Thread.sleep(1000);
//    }
//
//    @Scheduled(cron = "* * * * * *", zone = "GMT+8")
//    public void scheduledMethod2() throws InterruptedException {
//        LOGGER.info("定时任务的方法逻辑2222");
//        Thread.sleep(1000);
//    }
//
//    @Scheduled(cron = "* * * * * *", zone = "GMT+8")
//    public void scheduledMethod3() throws InterruptedException {
//        LOGGER.info("定时任务的方法逻辑3333");
//        Thread.sleep(1000);
//    }
//
//    @Scheduled(cron = "* * * * * *", zone = "GMT+8")
//    public void scheduledMethod4() throws InterruptedException {
//        LOGGER.info("定时任务的方法逻辑4444");
//        Thread.sleep(1000);
//    }
}
