package main.java.com.johann.threadTest;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.RunnableFuture;

/**
 * @ClassName: SleepWaitTest
 * @Description: TODO
 * @Author: Johann
 * @Date: 2019-12-13 10:01
 **/
public class SleepWaitTest {

    private static final Object lockObj = new Object();

    public static void main(String[] args) {
        //SleepWaitTest.testSleep();
        //SleepWaitTest.testWait();
        //SleepWaitTest.testYield();
        SleepWaitTest.testJoin();

        /**
        * 验证线程sleep的时候，是否会让出CPU资源
        */
//        int count = 0;
//        for (int i=0;i<10000;i++){
//            try{
//                //Thread.sleep(10000);
//                count += i;
//                System.out.println(i+" count : "+count);
//            }catch (Exception e){
//                e.printStackTrace();
//            }
//        }
    }



    /**
     *  sleep方法在Threa类中。这个方法不会释放资源锁，只会阻塞线程。在休眠指定的时长后，进入可运行状态，重新参与竞争。
     *  通过main方法中的CPU占用率对比，我们可知线程调用sleep方法的时候，会让出CPU占用给其他线程使用。
     *
     *  总结：某个线程执行sleep方法后，它将释放对CPU的占用，但是并不释放锁。
     *  因此，和该线程共有锁的其他线程，由于没有获取锁，继续等待。而无需等待该线程释放锁的其他线程，参与竞争，一旦获取到CPU时间片立刻执行【立即执行的意思是，立即参与CPU时间片的竞争】。
     *
     *  线程执行sleep(0)，并不是毫无意义的，它可以触发操作系统OS重新进行一次CPU时间片竞争。
     *
     *      * Causes the currently executing thread to sleep (temporarily cease
     *      * execution) for the specified number of milliseconds, subject to
     *      * the precision and accuracy of system timers and schedulers. The thread
     *      * does not lose ownership of any monitors.
     *      根据系统计时器和调度器的精度和准确性，使当前执行的线程休眠(临时停止执行)指定的毫秒数。
     *      线程不会失去任何锁的所有权。
     *
     *      * @param  millis
     *      *         the length of time to sleep in milliseconds
     *      线程休眠的时长(单位:毫秒)
     *
     *      * @throws  IllegalArgumentException
     *      *          if the value of {@code millis} is negative
     *      如果休眠时长传入的是负数，则抛出异常 IllegalArgumentException
     *
     *      * @throws  InterruptedException
     *      *          if any thread has interrupted the current thread. The
     *      *          <i>interrupted status</i> of the current thread is
     *      *          cleared when this exception is thrown.
     *      如果任何线程中断了当前线程。当前线程的<i>中断状态</i>在抛出此异常时被清除。
     *
    */
    public static void testSleep(){
        Thread thread1 = new Thread(new SleepThread(),"SleepThread");
        Thread thread2 = new Thread(new SleepThreadCopy(),"SleepThreadCopy");
        try{
            thread2.sleep(1000);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        thread1.start();
        thread2.start();
    }
    static class SleepThread implements Runnable{

        @Override
        public void run() {
            synchronized (lockObj){
                System.out.println(Thread.currentThread().getName()+"### SleepThread run ......");
                try{
                    Thread.sleep(5000);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName()+"### SleepThread run again......");
            }
        }
    }
    static class SleepThreadCopy implements Runnable{
        @Override
        public void run() {
            synchronized (lockObj){
                System.out.println(Thread.currentThread().getName()+"### SleepThreadCopy run ......");
            }
        }
    }



    /**
     *  wait 是Object类中的方法。线程A的某个对象调用wait方法，会释放该对象的锁，同时线程A进入该对象的等待池中，不再参与锁竞争。
     *
     *  ### 锁池:假设线程A已经拥有了某个对象(注意:不是类)的锁，而其它的线程想要调用这个对象的某个synchronized方法(或者synchronized块)，
     *           由于这些线程在进入对象的synchronized方法之前必须先获得该对象的锁的拥有权，但是该对象的锁目前正被线程A拥有，所以这些线程就进入了该对象的锁池中。
     *
     *  ### 等待池:假设一个线程A调用了某个对象的wait()方法，线程A就会释放该对象的锁后，进入到了该对象的等待池中。
     *

     *  notify()方法和notifyAll()方法都是唤醒线程的方法，所谓的唤醒，意思是将该对象等待池中的线程放入到锁池中，锁池中的线程可以参与该对象的锁竞争。
     *  notify()方法和notifyAll()方法的不同处在于，notify()每次只唤醒一个线程，具体唤醒哪个由JVM决定。而notifyAll()方法会唤醒该对象等待池中的所有线程。
     *  代码参照：WaitAndNotify.java
     *
     *      * Causes the current thread to wait until another thread invokes the
     *      * {@link java.lang.Object#notify()} method or the
     *      * {@link java.lang.Object#notifyAll()} method for this object.
     *      * In other words, this method behaves exactly as if it simply
     *      * performs the call {@code wait(0)}.
     *      导致当前线程等待，直到另一个线程调用该对象的{@link java.lang.Object#notify()}方法或{@link java.lang.Object#notifyAll()}方法。
     *      换句话说，这个方法表现得与它简单地执行调用{@code wait(0)}方法完全一样。
     *
     *      * The current thread must own this object's monitor.
     *      * The thread releases ownership of this monitor and waits until another thread
     *      * notifies threads waiting on this object's monitor to wake up
     *      * either through a call to the {@code notify} method or the
     *      * {@code notifyAll} method.
     *      * The thread then waits until it can re-obtain ownership of the monitor and resumes execution.
     *      当前线程必须拥有此对象的锁。
     *      线程释放此锁的所有权，并等待着直到另一个线程通过调用{@code notify}方法或{@code notifyAll}方法通知等待在该对象的锁上的线程醒来。
     *      该线程继续等待直到它重新获取到该对象的锁的所有权，然后重新执行。
     *
     *      * As in the one argument version, interrupts and spurious wakeups are
     *      * possible, and this method should always be used in a loop:
     *      * <pre>
     *      *     synchronized (obj) {
     *      *         while (&lt;condition does not hold&gt;)
     *      *             obj.wait();
     *      *         ... // Perform action appropriate to condition
     *      *     }
     *      * </pre>
     *      * This method should only be called by a thread that is the owner
     *      * of this object's monitor. See the {@code notify} method for a
     *      * description of the ways in which a thread can become the owner of
     *      * a monitor.
     *      此方法应且仅由获得该对象的锁的所有权的线程调用。查看{@code notify}方法，了解线程成为对象锁所有者的方式。
     *
     *      * @throws  IllegalMonitorStateException  if the current thread is not
     *      *               the owner of the object's monitor.
     *      如果当前线程，没有获得该对象的锁的所有权，则抛出异常 IllegalMonitorStateException
     *
     *      * @throws  InterruptedException if any thread interrupted the
     *      *             current thread before or while the current thread
     *      *             was waiting for a notification.  The <i>interrupted
     *      *             status</i> of the current thread is cleared when
     *      *             this exception is thrown.
     *      如果任何线程在当前线程等待通知之前或在当前线程等待通知期间中断当前线程。
     *      当前线程的<i>中断状态</i>在抛出此异常时被清除
     *
     *      * @see        java.lang.Object#notify()
     *      * @see        java.lang.Object#notifyAll()
    */
    public static void testWait(){
        Thread thread1 = new Thread(new WaitThread(),"WaitThread");
        Thread thread2 = new Thread(new WaitThreadCopy(),"WaitThreadCopy");
        thread1.start();
        thread2.start();
    }
    static class WaitThread implements Runnable{

        @Override
        public void run() {
            synchronized (lockObj){
                System.out.println(Thread.currentThread().getName()+"### WaitThread run ......");
                try{
                    lockObj.wait();
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName()+"### WaitThread over ......");
            }
        }
    }
    static class WaitThreadCopy implements Runnable{
        public void run() {
            synchronized (lockObj){
                System.out.println(Thread.currentThread().getName()+"### WaitThreadCopy run ......");
                lockObj.notifyAll();
                try{
                    Thread.sleep(5000);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName()+"### WaitThreadCopy over ......");
            }
        }
    }


    /**
     *    Yield 是Thread类中的方法。线程A调用 yield方法，表示线程A愿意暂停当前线程，让出对CPU 时间片的使用，但是，此时线程A并不会释放资源锁。
     *    此时，线程A的状态不会进入阻塞状态，而是会重新会回到就绪状态，参与CPU时间片的竞争。
     *    即实际使用中，它可能在调用yiedl()方法进入就绪状态后，马上又获取到了时间片的使用权，重新被执行。
     *    还有一点和 sleep 不同的是 yield 方法只能使同优先级或更高优先级的线程有执行的机会。
     *
     *
     *      * A hint to the scheduler that the current thread is willing to yield
     *      * its current use of a processor. The scheduler is free to ignore this
     *      * hint.
     *      提示调度程序，当前线程愿意放弃当前对处理器的使用。调度器可以忽略这个提示。
     *
     *      * <p> Yield is a heuristic attempt to improve relative progression
     *      * between threads that would otherwise over-utilise a CPU. Its use
     *      * should be combined with detailed profiling and benchmarking to
     *      * ensure that it actually has the desired effect.
     *      Yield是一种启发式尝试，旨在改善线程之间的相对进程，否则将会过度使用CPU。
     *      它的使用应该与详细的概要分析和基准测试相结合，以确保它实际上具有预期的效果。
     *
     *      * <p> It is rarely appropriate to use this method. It may be useful
     *      * for debugging or testing purposes, where it may help to reproduce
     *      * bugs due to race conditions. It may also be useful when designing
     *      * concurrency control constructs such as the ones in the
     *      * {@link java.util.concurrent.locks} package.
     *      很少适合使用这种方法。它可能有助于调试或测试目的，在这些目的中，它可能有助于重现由于竞争条件造成的bug。
     *      在设计诸如{@link java.util.concurrent.locks}包中的并发控制结构时，它可能也很有用。
    */
    public static void testYield(){
        Thread thread1 = new Thread(new YieldThread(),"YieldThread");
        Thread thread2 = new Thread(new YieldThreadCopy(),"YieldThreadCopy");
        thread1.start();
        thread2.start();
    }
    static class YieldThread implements Runnable{
        @Override
        public void run() {
            synchronized (lockObj){
                System.out.println(Thread.currentThread().getName()+"### YieldThread run ......");
                Thread.yield();
                System.out.println(Thread.currentThread().getName()+"### YieldThread over ......");
            }
        }
    }
    static class YieldThreadCopy implements Runnable{
        @Override
        public void run() {
            synchronized (lockObj){
                System.out.println(Thread.currentThread().getName()+"### YieldThreadCopy run ......");
                System.out.println(Thread.currentThread().getName()+"### YieldThreadCopy over ......");
            }
        }
    }

    /**
    *   等待调用join()执行完，才能继续往下执行。如示例中： thread2.join(); 只有当thread2，即JoinThreadCopy线程执行完之后，主线程才能继续往下执行。
     *  当调用 thread2.join(); 后，thread1 并不会让步与thread2，这两个线程会竞争资源锁lockObj，谁竞争到，谁先执行。
     *
     *  Waits for this thread to die.
     *  等待线程死亡
     *      *
     *      * <p> An invocation of this method behaves in exactly the same
     *      * way as the invocation
     *      *
     *      * <blockquote>
     *      * {@linkplain #join(long) join}{@code (0)}
     *      * </blockquote>
     *      *
     *      * @throws  InterruptedException
     *      *          if any thread has interrupted the current thread. The
     *      *          <i>interrupted status</i> of the current thread is
     *      *          cleared when this exception is thrown.
     *
     *
    */
    public static void testJoin(){
        Integer c = null;
        Callable callable = new JoinThread();
        RunnableFuture<Integer> futureTask = new FutureTask<Integer>(callable);
        Thread thread1 = new Thread(futureTask,"实现Callable测试Join");
        Thread thread2 = new Thread(new JoinThreadCopy(),"JoinThreadCopy");

        thread1.start();
        thread2.start();
        try{
            //thread1.join();
            thread2.join();
            c = futureTask.get();
            System.out.println("等待结果中......");
            System.out.println(Thread.currentThread().getName()+"### new c : "+c);
        }catch (InterruptedException e){
            e.printStackTrace();
        }catch (ExecutionException e1){
            e1.printStackTrace();
        }
    }
    static class JoinThread implements Callable{
        @Override
        public Integer call() throws Exception {
            synchronized (lockObj){
                Integer count = 19;//查询队列中的数据，得到值
                System.out.println(Thread.currentThread().getName()+"### JoinThread run ...... count : "+count);
                if(count > 20){
                    count = count >> 2;
                }else if (count > 0 && count < 20){
                    count = count << 2;
                }else{
                    count = ~count;
                }
                try{
                    Thread.sleep(5000);
                    System.out.println(Thread.currentThread().getName()+"### JoinThread run ......  sleep 5000 ");
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName()+"### JoinThread run ...... return count : "+count);
                return count;
            }
        }
    }
    static class JoinThreadCopy implements Runnable{
        @Override
        public void run() {
            synchronized (lockObj){
                System.out.println(Thread.currentThread().getName()+"### JoinThreadCopy run ......");
                try{
                    Thread.sleep(5000);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName()+"### JoinThreadCopy over ......");
            }
        }
    }

}
