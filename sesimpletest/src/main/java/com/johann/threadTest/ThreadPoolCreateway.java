package main.java.com.johann.threadTest;

import java.util.ArrayList;
import java.util.Calendar;
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
        //testCachedThreadPool();
        //testFixedThreadPool();
        //testSingleThreadExecutor();
        testScheduledThreadPool();
    }

    /**
    *  线程池创建方式一 ： 创建一个可以根据需要创建新线程的线程池。
     *  Executors.newCachedThreadPool(); corePoolSize 为 0
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

        //关闭线程池
        cachedThreadPool.shutdown();
    }

    /** 
    *  线程池创建方式二 ：创建一个大小固定的线程池。
     *      Executors.newFixedThreadPool(int corePoolSize)
    */
    public static void testFixedThreadPool(){

        /**
         * Creates a thread pool that reuses a fixed number of threads
         * operating off a shared unbounded queue.  At any point, at most
         * {@code nThreads} threads will be active processing tasks.
         * If additional tasks are submitted when all threads are active,
         * they will wait in the queue until a thread is available.
         * If any thread terminates due to a failure during execution
         * prior to shutdown, a new one will take its place if needed to
         * execute subsequent tasks.  The threads in the pool will exist
         * until it is explicitly {@link ExecutorService#shutdown shutdown}.
         *
         * 创建一个线程池，该线程池重用在共享无界队列上操作的固定数量的线程。任何时候，大多数{@code nThreads}线程处于处理任务的状态。
         * 如果在所有线程都处于活动状态时提交额外的任务，它们将在队列中等待，直到有一个线程可用为止。
         * 如果任何线程在关闭之前的执行过程中由于失败而终止，那么如果需要执行后续任务，则需要一个新线程来替代它。
         * 池中的线程将一直存在，除非显式执行{@link ExecutorService#shutdown shutdown}。
         *
         *   new ThreadPoolExecutor(nThreads, nThreads,0L, TimeUnit.MILLISECONDS,new LinkedBlockingQueue<Runnable>());
        */
        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(5);

        Callable<String> callable = new ImplementsCallable();
        List<Future> futureList = new ArrayList<Future>();

        //如果创建的线程池够用，不会重用。如果不够，则会重用
        for (int i=0;i<7;i++){
            //提交一个返回值的任务以供执行，并返回一个表示该任务未决结果的Future。Futrue的get方法可以获取任务的返回结果
            Future<String> future = fixedThreadPool.submit(callable);
            futureList.add(future);
        }

        try{
            if(futureList.size()>0){
                for(Future f : futureList){
                    System.out.println("fixedThreadPool 执行完返回信息 ： 【"+f.get()+"】");
                }
            }
        }catch (InterruptedException interrupterException){
            interrupterException.printStackTrace();
        }catch (ExecutionException executionException){
            executionException.printStackTrace();
        }

        //关闭线程池
        fixedThreadPool.shutdown();
    }


    /**
    *   创建方式三 ：创建一个只有一个线程的线程池
     *   Executors.newSingleThreadExecutor();
     *   注意区别于 newFixedThreadPool(1);
    */
    public static void testSingleThreadExecutor(){

        /**
         * Creates an Executor that uses a single worker thread operating
         *  off an unbounded queue. (Note however that if this single
         * thread terminates due to a failure during execution prior to
         * shutdown, a new one will take its place if needed to execute
         *  subsequent tasks.)  Tasks are guaranteed to execute
         * sequentially, and no more than one task will be active at any
         * given time. Unlike the otherwise equivalent
         *  {@code newFixedThreadPool(1)} the returned executor is
         * guaranteed not to be reconfigurable to use additional threads.
         *
         * 创建一个执行程序，该执行程序使用一个工作线程在一个无界队列上操作。
         * (但是请注意，如果这个线程在关闭之前的执行过程中由于失败而终止，那么在需要执行后续任务时，将需要一个新的线程来替代它。)
         * 任务保证按顺序执行，并且在任何给定时间都不会有多个任务处于活动状态。
         * 与其等价的创建方式{@code newFixedThreadPool(1)}不同，返回的执行器保证不可重新配置以使用其他线程。
         *
         *      newSingleThreadExecutor()
        */
        ExecutorService singleThreadExecutor = Executors.newSingleThreadExecutor();

        Runnable implementsRunnable = new ImplementsRunnable();
        List<Future> fList = new ArrayList<>();
        for(int i=0;i<5;i++){
            //提交一个返回值的任务以供执行，并返回一个表示该任务未决结果的Future。Futrue的get方法可以获取任务的返回结果
            //如果参数只有 Runnable,任务执行成功后，返回的结果为 null；
            //如果参数包含有 Runnable和result,那么任务执行成功后，返回的结果就是 result
            Future<String> f =  singleThreadExecutor.submit(implementsRunnable,"执行成功 - - "+i);
            fList.add(f);
        }

        try{
            if(fList.size()>0){
                for(Future f : fList){
                    System.out.println("singleThreadExecutor 执行完返回信息 ： 【"+f.get()+"】");
                }
            }
        }catch (InterruptedException interrupterException){
            interrupterException.printStackTrace();
        }catch (ExecutionException executionException){
            executionException.printStackTrace();
        }

        //关闭线程池
        singleThreadExecutor.shutdown();
    }

    /**
     *  创建方式四 ：创建一个线程池，大小可以设置，此线程支持定时以及周期性的任务
     *  Executors.newScheduledThreadPool(int corePoolSize);
     *
     *  public ScheduledFuture<?> scheduleAtFixedRate(Runnable command,long initialDelay,long period,TimeUnit unit);
     *  public ScheduledFuture<?> scheduleWithFixedDelay(Runnable command,long initialDelay,long delay,TimeUnit unit);
     *  scheduleAtFixedRate 和 scheduleWithFixedDelay 方法的差异？
     *  相同点：
     *      1）首次执行任务，都是在初始延迟时间 initialDelay 后执行。且之后的任务都是间隔一段时间 period(delay) 后执行；
     *      2）线程池中的线程执行的任务都可以被取消 concle，如果任务被取消，Future.get()方法会抛出异常 CancellationException
     *  不同点：
     *      scheduleAtFixedRate 两个连续任务的执行间隔时间，是按照两个连续任务的开始时间来计算的，即 【nextTask.startTime - thisTask.startTime】;
     *      scheduleWithFixedDelay 两个连续任务的执行间隔时间，是按照下一个任务的开始时间距离上一个任务的结束时间计算的，即 【nextTask.startTime - thisTask.endTime】;
    */
    public static void testScheduledThreadPool(){

        /**
         * Creates a thread pool that can schedule commands to run after a given delay, or to execute periodically.
         * corePoolSize ： the number of threads to keep in the pool,even if they are idle
         *
         * 创建一个线程池，该线程池可以根据调度命令在给定的延迟后运行，或定期执行。
        */
        ExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(5);

        Runnable implementsRunnable = new ImplementsRunnable();
        Callable<String> implementsCallable = new ImplementsCallable();

        System.out.println("开始执行时间 ： "+Calendar.getInstance().get(Calendar.HOUR_OF_DAY)+":"+Calendar.getInstance().get(Calendar.MINUTE)+":"+Calendar.getInstance().get(Calendar.SECOND));

        /**
         * 定时延迟执行方法一
         * public ScheduledFuture<?> schedule(Runnable command,long delay, TimeUnit unit);
         * Creates and executes a one-shot action that becomes enabled after the given delay.
         *
         * 指定延迟之后，创建且执行一次的任务
         * 延迟执行 实现Runnable接口的任务。执行成功，返回结果为null
        */
        //Future f = ((ScheduledExecutorService) scheduledThreadPool).schedule(implementsRunnable,10,TimeUnit.SECONDS);

        /**
        * 定时延迟执行方法二
         * public ScheduledFuture<?> schedule(Callable<V> callable,,long delay, TimeUnit unit);
         * Creates and executes a ScheduledFuture that becomes enabled after the given delay.
         *
         * @return a ScheduledFuture that can be used to extract result or cancel
         * ScheduledFuture，可用于提取结果或取消任务
        */
//        Future f = ((ScheduledExecutorService) scheduledThreadPool).schedule(implementsCallable,10,TimeUnit.SECONDS);
//        try {
//            //Thread.sleep(4000, 0);
//            Thread.sleep(15000, 0);
//            /**
//             * Future.cancle(boolean mayInterruptIfRunning);方法说明：
//             *  Attempts to cancel execution of this task.  This attempt will
//             *  fail if the task has already completed, has already been cancelled,
//             *  or could not be cancelled for some other reason. If successful,
//             *  and this task has not started when {@code cancel} is called,
//             *  this task should never run.  If the task has already started,
//             *  then the {@code mayInterruptIfRunning} parameter determines
//             *  whether the thread executing this task should be interrupted in
//             *  an attempt to stop the task.
//             *  试图取消此任务的执行。
//             *  如果任务已经完成，已经取消，或者由于其他原因无法取消，则此尝试将失败。如果成功，并且在调用{@code cancel}时此任务尚未启动，则此任务不应运行。
//             *  如果任务已经启动，那么{@code mayInterruptIfRunning}参数决定执行此任务的线程是否应该中断，以试图停止任务。
//             *
//             *  <p>After this method returns, subsequent calls to {@link #isDone} will
//             *  always return {@code true}.  Subsequent calls to {@link #isCancelled}
//             *  will always return {@code true} if this method returned {@code true}.
//             *  在此方法返回后，对{@link #isDone}的后续调用将始终返回{@code true}。
//             *  如果这个方法返回{@code true}，那么对{@link # iscancel}的后续调用将始终返回{@code true}。
//             *
//             *  @param mayInterruptIfRunning {@code true} if the thread executing this
//             *  task should be interrupted; otherwise, in-progress tasks are allowed
//             *  to complete
//             *  如果参数值为true，那么正在执行该任务的线程会被中断;否则，将允许正在进行的任务完成。
//             *
//             *  @return {@code false} if the task could not be cancelled,
//             *  typically because it has already completed normally;
//             *  {@code true} otherwise
//             *  如果任务无法被取消，则返回 false。如果任务无法被取消，通常是由于这个任务已经正常执行完。
//             *  否则，返回 true
//             *
//            */
//            boolean cancleflag = f.cancel(true);
//            System.out.println("### cancleflag : "+cancleflag);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }


        /**
        *   定时延迟执行方法三
         *  public ScheduledFuture<?> scheduleAtFixedRate(Runnable command,long initialDelay,long period,TimeUnit unit);
         *
         *  Creates and executes a periodic action that becomes enabled first
         *      * after the given initial delay, and subsequently with the given
         *      * period; that is executions will commence after
         *      * {@code initialDelay} then {@code initialDelay+period}, then
         *      * {@code initialDelay + 2 * period}, and so on.
         *      * If any execution of the task
         *      * encounters an exception, subsequent executions are suppressed.
         *      * Otherwise, the task will only terminate via cancellation or
         *      * termination of the executor.  If any execution of this task
         *      * takes longer than its period, then subsequent executions
         *      * may start late, but will not concurrently execute.
         *  创建并执行一个周期性的操作，该操作在给定的初始延迟之后首先启用，然后在给定的时间段内启用;
         *  即执行将在{@code initialDelay}、{@code initialDelay+period}、{@code initialDelay+ 2 * period}之后开始，依此类推。
         *  如果任务的任何执行遇到异常，则禁止后续执行。
         *  否则，任务将仅通过执行程序的取消或终止而终止。
         *  如果此任务的任何执行时间超过其周期，则后续执行可能会延迟开始，但不会并发执行。
         *
         *  @return a ScheduledFuture representing pending completion of
         *      *         the task, and whose {@code get()} method will throw an
         *      *         exception upon cancellation
         *  一个ScheduledFuture表示任务的挂起完成，它的{@code get()}方法将在取消时抛出异常
        */
        Future f = ((ScheduledExecutorService) scheduledThreadPool).scheduleAtFixedRate(implementsRunnable,10,5,TimeUnit.SECONDS);
        Long dateStart = Calendar.getInstance().getTimeInMillis();

        boolean flag = true;
        Long dateEnd;
        while(flag){
            try{
                Thread.sleep(1000);
                dateEnd = Calendar.getInstance().getTimeInMillis();
                if(dateEnd-dateStart>1000*32){
                    boolean cancleflag = f.cancel(true);
                    System.out.println("### cancleflag : "+cancleflag);
                    flag = false;
                }
            }catch (InterruptedException e) {
                e.printStackTrace();
            }
        }


        /**
        *    定时延迟执行方法四
         *   public ScheduledFuture<?> scheduleWithFixedDelay(Runnable command,long initialDelay,long delay,TimeUnit unit);
         *   Creates and executes a periodic action that becomes enabled first
         *      * after the given initial delay, and subsequently with the
         *      * given delay between the termination of one execution and the
         *      * commencement of the next.  If any execution of the task
         *      * encounters an exception, subsequent executions are suppressed.
         *      * Otherwise, the task will only terminate via cancellation or
         *      * termination of the executor.
         *   创建并执行一个周期性操作，该操作首先在给定的初始延迟之后启用，然后在一个执行终止到下一个执行开始之间的给定延迟中启用。
         *   如果任务的任何执行遇到异常，则禁止后续执行。否则，任务将仅通过执行程序的取消或终止而终止。
        */
//        Future f = ((ScheduledExecutorService) scheduledThreadPool).scheduleWithFixedDelay(implementsRunnable,10,5,TimeUnit.SECONDS);
//        Long dateStart = Calendar.getInstance().getTimeInMillis();
//
//        boolean flag = true;
//        Long dateEnd;
//        while(flag){
//            try{
//                Thread.sleep(1000);
//                dateEnd = Calendar.getInstance().getTimeInMillis();
//                if(dateEnd-dateStart>1000*32){
//                    boolean cancleflag = f.cancel(true);
//                    System.out.println("### cancleflag : "+cancleflag);
//                    flag = false;
//                }
//            }catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }

        try{
            if(f==null){
                System.out.println("scheduledThreadPool 延迟执行完返回信息 ： 【返回结果Future为 NULL】");
                System.out.println("结束执行时间 ： "+Calendar.getInstance().get(Calendar.HOUR_OF_DAY)+":"+Calendar.getInstance().get(Calendar.MINUTE)+":"+Calendar.getInstance().get(Calendar.SECOND));
            }else{
                System.out.println("scheduledThreadPool 延迟执行完返回信息 ： 【"+f.get()+"】");
                System.out.println("结束执行时间 ： "+Calendar.getInstance().get(Calendar.HOUR_OF_DAY)+":"+Calendar.getInstance().get(Calendar.MINUTE)+":"+Calendar.getInstance().get(Calendar.SECOND));
            }
        }catch (InterruptedException interrupterException){
            interrupterException.printStackTrace();
        }catch (ExecutionException executionException){
            executionException.printStackTrace();
        }catch (CancellationException cancellationException){
            System.out.println("### 任务被终止。。。。。。 ");
        }

        //关闭线程池
        scheduledThreadPool.shutdown();
    }
}
