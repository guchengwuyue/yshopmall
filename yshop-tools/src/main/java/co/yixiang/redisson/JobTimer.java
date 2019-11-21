//package co.yixiang.redisson;
//
//import cn.hutool.core.util.ObjectUtil;
//import org.redisson.api.RBlockingQueue;
//import org.redisson.api.RDelayedQueue;
//import org.redisson.api.RedissonClient;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.ApplicationContext;
//import org.springframework.stereotype.Component;
//import javax.annotation.PostConstruct;
//import java.util.concurrent.ExecutorService;
//import java.util.concurrent.Executors;
//import java.util.concurrent.TimeUnit;
//
///**
// * 消费已经到点的延时job服务，通过job参数调用业务执行器实现
// */
//@Component
//public class JobTimer {
//
//    static final String jobsTag = "customer_jobtimer_jobs";
//    @Autowired
//    private RedissonClient client;
//
//    @Autowired
//    private ApplicationContext context;
//
//    ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors() * 2);
//
//    @PostConstruct
//    public void startJobTimer() {
//        RBlockingQueue<DelayJob> blockingQueue = client.getBlockingQueue(jobsTag);
//
//        new Thread() {
//            @Override
//            public void run() {
//                while (true) {
//                    try {
//                        DelayJob job = blockingQueue.take();
//                        if(ObjectUtil.isNotNull(job)){
//                            executorService.execute(new ExecutorTask(context, job));
//                            RDelayedQueue<DelayJob> delayedQueue = client.getDelayedQueue(blockingQueue);
//                            delayedQueue.destroy();
//                        }
//                    } catch (Exception e) {
//                        RDelayedQueue<DelayJob> delayedQueue = client.getDelayedQueue(blockingQueue);
//                        delayedQueue.destroy();
//                        e.printStackTrace();
//                        try {
//                            TimeUnit.SECONDS.sleep(60);
//                        } catch (Exception ex) {
//                        }
//                    }
//                }
//            }
//        }.start();
//    }
//
//    class ExecutorTask implements Runnable {
//
//        private ApplicationContext context;
//
//        private DelayJob delayJob;
//
//        public ExecutorTask(ApplicationContext context, DelayJob delayJob) {
//            this.context = context;
//            this.delayJob = delayJob;
//        }
//
//        @Override
//        public void run() {
//            ExecuteJob service = (ExecuteJob) context.getBean(delayJob.getAClass());
//            service.execute(delayJob);
//        }
//    }
//
//}
