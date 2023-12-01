package z.z.config;

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

    /**
     * 配置异步执行的 任务执行线程池
     * @return TaskExecutor
     */
    @Bean(name = "taskExecutor")
    public TaskExecutor asyncExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(4); // 核心线程数
        executor.setMaxPoolSize(4); // 最大线程数
        executor.setQueueCapacity(2000); // 队列容量
        executor.setThreadNamePrefix("异步线程-");// 线程名称前缀
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
        scheduler.setPoolSize(2); // 线程池大小
        scheduler.setThreadNamePrefix("定时任务-");// 线程名称前缀
        scheduler.initialize(); // 开启线程池
        return scheduler;
    }
}
