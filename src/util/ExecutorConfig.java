package util;

import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.*;

/**
 * @author Administrator
 */
public class ExecutorConfig {
    private ExecutorConfig(){
        throw new RuntimeException("配置类，不允许创建");
    }
    public static ThreadPoolExecutor getExecutor(){
        ThreadPoolExecutor executor = new ThreadPoolExecutor(1, 5, 50,
                TimeUnit.SECONDS, new ArrayBlockingQueue<>(100), Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.AbortPolicy());
        return executor;
    }
}
