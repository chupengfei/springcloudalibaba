package com.qingkong.product.task;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

@Component
@Slf4j
public class TimerTask {

    private static AtomicInteger i = new AtomicInteger();
    private ScheduledExecutorService executorService =
            Executors.newScheduledThreadPool(Runtime.getRuntime().availableProcessors(), new TimerThreadFactory());

    @PostConstruct
    public void init() {
        startTask();
    }

    private void startTask() {
        executorService.scheduleWithFixedDelay(new TimerThreadTask(), 2, 60, TimeUnit.SECONDS);
    }

    static class TimerThreadFactory implements ThreadFactory {

        @Override
        public Thread newThread(Runnable r) {
            Thread thread = new Thread(r);
            thread.setName("Timer task .");
            thread.setDaemon(true);
            return thread;
        }
    }

    static class TimerThreadTask implements Runnable {

        @Override
        public void run() {
            log.error("程序运行了{}分钟", i.getAndIncrement());
        }
    }

}
