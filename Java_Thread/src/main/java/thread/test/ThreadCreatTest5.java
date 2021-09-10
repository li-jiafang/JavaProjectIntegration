package thread.test;

import thread.creat.Runnable2;
import thread.creat.Thread1;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Author: ljf
 * @Create: 2021/9/10 11:56
 * @Description:
 **/

/**
 * 线程池不允许使用Executors去创建，而是通过ThreadPoolExecutor的方式，这样的处理方式让写的同学更加明确线程池的运行规则，规避资源耗尽的风险。 说明：Executors返回的线程池对象的弊端如下：
 * 1）FixedThreadPool和SingleThreadPool:
 *   允许的请求队列长度为Integer.MAX_VALUE，可能会堆积大量的请求，从而导致OOM。
 * 2）CachedThreadPool:
 *   允许的创建线程数量为Integer.MAX_VALUE，可能会创建大量的线程，从而导致OOM。
 *
 *   Positive example 1：
 *     //org.apache.commons.lang3.concurrent.BasicThreadFactory
 *     ScheduledExecutorService executorService = new ScheduledThreadPoolExecutor(1,
 *         new BasicThreadFactory.Builder().namingPattern("example-schedule-pool-%d").daemon(true).build());
 *
 *
 *
 * Positive example 2：
 *     ThreadFactory namedThreadFactory = new ThreadFactoryBuilder()
 *         .setNameFormat("demo-pool-%d").build();
 *
 *     //Common Thread Pool
 *     ExecutorService pool = new ThreadPoolExecutor(5, 200,
 *         0L, TimeUnit.MILLISECONDS,
 *         new LinkedBlockingQueue<Runnable>(1024), namedThreadFactory, new ThreadPoolExecutor.AbortPolicy());
 *
 *     pool.execute(()-> System.out.println(Thread.currentThread().getName()));
 *     pool.shutdown();//gracefully shutdown
 *
 *
 *
 * Positive example 3：
 *     <bean id="userThreadPool"
 *         class="org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor">
 *         <property name="corePoolSize" value="10" />
 *         <property name="maxPoolSize" value="100" />
 *         <property name="queueCapacity" value="2000" />
 *
 *     <property name="threadFactory" value= threadFactory />
 *         <property name="rejectedExecutionHandler">
 *             <ref local="rejectedExecutionHandler" />
 *         </property>
 *     </bean>
 *     //in code
 *     userThreadPool.execute(thread);
 *
 */
public class ThreadCreatTest5 {

    //
    /**
     * 线程池不允许使用Executors去创建，而是通过ThreadPoolExecutor的方式，这样的处理方式让写的同学更加明确线程池的运行规则，规避资源耗尽的风险。 说明：Executors返回的线程池对象的弊端如下：
     *  ---- 会创建大量线程导致OOM
     */
    public static ExecutorService service = Executors.newFixedThreadPool(10);

    public static void main(String[] args) {
        System.out.println("开始执行方法:");

        service.execute(new Runnable2());

        for (int i = 0; i < 100; i++) {
            System.out.println("主线程:"+Thread.currentThread().getName()+i+"开始执行");
        }
    }
}
