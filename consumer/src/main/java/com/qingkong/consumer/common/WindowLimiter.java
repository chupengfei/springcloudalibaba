package com.qingkong.consumer.common;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

@Slf4j
public class WindowLimiter {

    private LoadingCache<Long, AtomicLong> counter =
            CacheBuilder.newBuilder()
                    .expireAfterWrite(10, TimeUnit.SECONDS)
                    .build(new CacheLoader<Long, AtomicLong>() {
                        @Override
                        public AtomicLong load(Long seconds) throws Exception {
                            return new AtomicLong(0);
                        }
                    });
    private ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(5);
    //限流阈值
    private long limit = 15;

    /**
     * 滑动时间窗口
     * 每隔1s累加前5s内每1s的请求数量，判断是否超出限流阈值
     */
    public void slideWindow() {
        scheduledExecutorService.scheduleWithFixedDelay(() -> {
            try {
                long time = System.currentTimeMillis() / 1000;
                //每秒发送随机数量的请求
                int reqs = (int) (Math.random() * 5) + 1;
                counter.get(time).addAndGet(reqs);
                long nums = 0;
                // time windows 5 s
                for (int i = 0; i < 5; i++) {
                    nums += counter.get(time - i).get();
                }
                log.info("time=" + time + ",nums=" + nums);
                if (nums > limit) {
                    log.info("限流了,nums=" + nums);
                }
            } catch (Exception e) {
                log.error("slideWindow error", e);
            } finally {
            }
        }, 5000, 1000, TimeUnit.MILLISECONDS);
    }

    public static void main(String[] args) throws InterruptedException {
//        ReentrantLock lock = new ReentrantLock();
//        Condition condition = lock.newCondition();
//        new Thread(() -> {
//            try {
//                lock.lock();
//                TimeUnit.SECONDS.sleep(5);
//                System.out.println(Thread.currentThread().getName() + ": 我被await");
//                condition.await();
//                TimeUnit.SECONDS.sleep(5);
//                System.out.println(Thread.currentThread().getName() + ": 我被唤醒了");
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            } finally {
//                lock.unlock();
//            }
//        }, "线程1").start();
//
//        new Thread(() -> {
//            try {
//                lock.lock();
//                TimeUnit.SECONDS.sleep(5);
//                System.out.println(Thread.currentThread().getName() + ": 我被await");
//                condition.await();
//                TimeUnit.SECONDS.sleep(5);
//                System.out.println(Thread.currentThread().getName() + ": 我被唤醒了");
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            } finally {
//                lock.unlock();
//            }
//        }, "线程2").start();
//        new Thread(() -> {
//            try {
//                lock.lock();
//                TimeUnit.SECONDS.sleep(5);
//                System.out.println(Thread.currentThread().getName() + ": 我signalAll");
//                condition.signal();
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            } finally {
//                lock.unlock();
//            }
//        },"线程3").start();

        CountDownLatch latch = new CountDownLatch(1);
        CountDownLatch latch1 = new CountDownLatch(1);
        Thread thread1 = new Thread(()->{

            System.out.println("1");
            latch.countDown();
        },"xiancheng1");
        Thread thread2 = new Thread(()->{
            try {
                latch.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("2");
            latch1.countDown();
        },"xiancheng2");
        Thread thread3 = new Thread(()->{
            try {
                latch1.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("3");
        },"xiancheng3");
//        thread1.start();
//        thread1.join();
//        thread2.start();
//        thread2.join();
//        thread3.start();
//        thread3.join();

        thread1.start();
//        thread1.join();
        thread2.start();
//        thread2.join();
        thread3.start();
//        thread3.join();


//        ExecutorService executorService = Executors.newSingleThreadExecutor();
//        executorService.execute(thread1);
//        executorService.execute(thread2);
//        executorService.execute(thread3);


    }
}
