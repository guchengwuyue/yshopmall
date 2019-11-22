package co.yixiang.redisson;

import org.redisson.api.RBlockingQueue;
import org.redisson.api.RDelayedQueue;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * 消费已经到点的延时job服务，通过job参数调用业务执行器实现
 */
@Component
public class JobTimer {

    static final String CUSTOMER_JOB_TIMER_JOBS = "customer_job_timer_jobs";
    @Autowired
    private RedissonClient client;

    @Autowired
    private ApplicationContext context;

    ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors() * 2);
    @PostConstruct
    public void startJobTimer() {
        RBlockingQueue blockingQueue = client.getBlockingQueue(CUSTOMER_JOB_TIMER_JOBS);
        RDelayedQueue delayedQueue = client.getDelayedQueue(blockingQueue);
        new Thread(() -> {
            while (true) {
                try {
                    DelayJob job = (DelayJob) blockingQueue.take();
                    executorService.execute(new ExecutorTask(context, job));
                } catch (Exception e) {
                    e.printStackTrace();
                    try {
                        TimeUnit.SECONDS.sleep(60);
                    } catch (Exception ex) {

                    }
                }finally {
                    delayedQueue.destroy();
                }
            }
        }).start();
    }
    class ExecutorTask implements Runnable {

        private ApplicationContext context;

        private DelayJob delayJob;

        public ExecutorTask(ApplicationContext context, DelayJob delayJob) {
            this.context = context;
            this.delayJob = delayJob;
        }

        @Override
        public void run() {
            ExecuteJob service = (ExecuteJob) context.getBean(delayJob.getAClass());
            service.execute(delayJob);
        }
    }
}
