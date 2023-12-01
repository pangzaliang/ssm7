package z.z.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

@EnableScheduling //启动定时任务
@EnableAsync //启动异步任务
@Configuration
public class SpringAsyncAndTaskConfig {

    @Value("${asyncExecutor.corePool}")
    private Integer corePool;
    @Value("${asyncExecutor.maxPool}")
    private Integer maxPool;
    @Value("${asyncExecutor.queueCapacity}")
    private Integer queueCapacity;
    @Value("${asyncExecutor.threadNamePrefix}")
    private String threadNamePrefix;


    @Value("${taskScheduler.poolSize}")
    private Integer schedulerPoolSize;
    @Value("${taskScheduler.threadNamePrefix}")
    private String schedulerThreadNamePrefix;


    /**
     * 配置异步执行的 任务执行线程池
     * @return TaskExecutor
     */
    @Bean(name = "taskExecutor")
    public TaskExecutor asyncExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(corePool); // 核心线程数
        executor.setMaxPoolSize(maxPool); // 最大线程数
        executor.setQueueCapacity(queueCapacity); // 队列容量
        executor.setThreadNamePrefix(threadNamePrefix);// 线程名称前缀
        executor.initialize(); // 开启线程池
        return executor;
    }

    /**
     * 配置定时任务的 任务调度线程池
     * @return TaskExecutor
     */
    @Bean(name = "taskScheduler")
    public TaskExecutor taskScheduler() {
        ThreadPoolTaskScheduler scheduler = new ThreadPoolTaskScheduler();
        scheduler.setPoolSize(schedulerPoolSize); // 线程池大小
        scheduler.setThreadNamePrefix(schedulerThreadNamePrefix);// 线程名称前缀
        scheduler.initialize(); // 开启线程池
        return scheduler;
    }
}
