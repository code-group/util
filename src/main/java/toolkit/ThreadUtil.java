package toolkit;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.util.concurrent.*;

/**
 * @author: zhull
 * <P>date: 2017/12/12</P>
 * <P>description: 线程池工具类</P>
 */
public class ThreadUtil {

    private ThreadPoolExecutor executor;

    private int corePoolSize = 10;
    private int maximumPoolSize = 15;
    private long keepAliveTime = 200;
    private TimeUnit unit = TimeUnit.MILLISECONDS;
    private LinkedBlockingQueue workQueue = new LinkedBlockingQueue<Runnable>();

    /**
     * 私有构造方法
     */
    private ThreadUtil() {
        ThreadFactory threadFactory = new ThreadFactoryBuilder().setNameFormat("thread-pool-%d").build();
        executor = new ThreadPoolExecutor(corePoolSize, maximumPoolSize, keepAliveTime, unit,
                workQueue, threadFactory, new ThreadPoolExecutor.AbortPolicy());
    }

    /**
     * 获取实例
     */
    public static final ThreadUtil getInstance() {
        return LazyHolder.INSTANCE;
    }

    /**
     * 线程池获取方法
     *
     * @return
     */
    private ThreadPoolExecutor getExecutor() {
        return executor;
    }

    /**
     * 执行任务 抛入线程池
     *
     * @param t
     */
    public void execute(Thread t) {
        executor.execute(t);
    }

    public void execute(Runnable t) {
        executor.execute(t);
    }

    /**
     * 返回 Future
     * Future.get()可获得返回结果
     *
     * @return
     */
    @SuppressWarnings({"unchecked", "rawtypes"})
    public Future<?> submit(Callable t) {
        return getExecutor().submit(t);
    }

    /**
     * 销毁线程池
     */
    public void shutdown() {
        getExecutor().shutdown();
    }

    private static class LazyHolder {
        private static final ThreadUtil INSTANCE = new ThreadUtil();
    }
}
