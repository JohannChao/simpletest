package main.java.com.johann.threadTest;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * @ClassName: ThreadPoolCreateway
 * @Description: 线程池常见的四种创建方式
 * @Author: Johann
 * @Date: 2019-12-11 16:26
 **/
public class ThreadPoolCreateway {

    public static void main(String[] args) {
        testCachedThreadPool();

    }

    /**
    *  线程池创建方式一 ： 创建一个可以根据需要创建新线程的线程池。
     *  Executors.newCachedThreadPool()
    */
    public static void testCachedThreadPool(){
        /**
         * Creates a thread pool that creates new threads as needed, but
         * will reuse previously constructed threads when they are
         * available.  These pools will typically improve the performance
         * of programs that execute many short-lived asynchronous tasks.
         * Calls to {@code execute} will reuse previously constructed
         * threads if available. If no existing thread is available, a new
         * thread will be created and added to the pool. Threads that have
         * not been used for sixty seconds are terminated and removed from
         * the cache. Thus, a pool that remains idle for long enough will
         * not consume any resources. Note that pools with similar
         * properties but different details (for example, timeout parameters)
         * may be created using {@link ThreadPoolExecutor} constructors.
         *
         * 创建一个线程池，该线程池根据需要创建新线程，但将重用以前构造的可用线程。
         * 这些池通常会提高执行许多短期异步任务的程序的性能。
         * 调用{@code execute}方法将重用以前构造的线程（如果线程可用）。
         * 如果没有现有线程可用，将创建一个新线程并将其添加到池中。
         * 未使用60秒的线程将被终止并从缓存中删除。因此，长时间空闲的池不会消耗任何资源。
         * 注意，可以使用{@link ThreadPoolExecutor}构造函数创建具有相似属性但不同细节(例如超时参数)的池。
         *
         *  new ThreadPoolExecutor(0, Integer.MAX_VALUE, 60L, TimeUnit.SECONDS, new SynchronousQueue<Runnable>());
         *  追寻源码发现:
         *      初始线程池中的线程个数为0[corePoolSize:0]；
         *      线程池原则上支持的最大线程数是 Integer.MAX_VALUE；
         *      空闲的线程，销毁时间由 60L 和 TimeUnit.SECONDS 确定。
        */
        ExecutorService cachedThreadPool = Executors.newCachedThreadPool();


        Callable<String> callable = new ImplementsCallable();
        List<Future> futureList = new ArrayList<Future>();

        //Future<String> future = cachedThreadPool.submit(callable);
        for (int i=0;i<5;i++){
            //提交一个返回值的任务以供执行，并返回一个表示该任务未决结果的Future。Futrue的get方法可以获取任务的返回结果
            Future<String> future = cachedThreadPool.submit(callable);
            futureList.add(future);
//            try {
//                //如果没有这一步，会出现多个不同的线程，也会有线程重用；如果使得主线程睡一下，那么只用一个线程就够了
//                Thread.sleep(0, 1);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
        }

        //关闭线程池
        cachedThreadPool.shutdown();
        try{
            if(futureList!=null && futureList.size()>0){
                for(Future f : futureList){
                    System.out.println("cachedThreadPool 执行完返回信息 ： 【"+f.get()+"】");
                }
            }
        }catch (InterruptedException interrupterException){
            interrupterException.printStackTrace();
        }catch (ExecutionException executionException){
            executionException.printStackTrace();
        }
    }

    /** 
    *  线程池创建方式二 ：
    */
    public static void testFixedThreadPool(){
        
    }

}
