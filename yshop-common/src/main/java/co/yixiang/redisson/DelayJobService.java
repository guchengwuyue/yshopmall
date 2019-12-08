package co.yixiang.redisson;

import org.redisson.api.RBlockingQueue;
import org.redisson.api.RDelayedQueue;
import org.redisson.api.RQueue;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * Created by kl on 2018/7/20.
 * Content :订单延时job服务
 */
@Component
public class DelayJobService {

    @Autowired
    private RedissonClient client;

    /**
     * 添加超时任务到redis队列
     * @param job  任务
     * @param delay 超时时间
     */
    public void submitJob(Map<String,Object> job, Long delay){
        RQueue<Map<String,Object>> blockingQueue = client.getQueue(JobTimer.CUSTOMER_JOB_TIMER_JOBS);
        RDelayedQueue<Map<String,Object>> delayedQueue = client.getDelayedQueue(blockingQueue);
        delayedQueue.offer(job,delay,TimeUnit.MILLISECONDS);
        delayedQueue.destroy();
    }
}
