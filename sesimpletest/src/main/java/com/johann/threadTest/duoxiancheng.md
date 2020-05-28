
在谈多线程之前，首先要明确以下几个概念。

1，多线程：是指这个程序（一个进程）运行时产生了不止一个线程。

2，并行和并发：
    
1）并行：多个cpu实例或者多台机器同时执行一段代码逻辑，是真正的同时。

2）并发：通过cpu调度算法，让用户看上去同时执行，实际上从cpu层面上来看并不是真正的同时执行。并发往往在场景中公共的资源，那么针对这个公共的资源往往产生瓶颈，我们会用TPS或QPS来反应这个系统的处理能力。

3，线程安全：经常用来描绘一段代码。指在并发的情况下，该代码经过多线程使用，线程的调度顺序不影响任何结果。这个时候使用多线程，我们只需要关注系统的内存，cpu是不是够用即可；反过来，线程不安全就意味着线程的调度顺序会影响最终的结果

4，同步：Java中的同步指的是通过人为的控制和调度，保证共享资源的多线程访问成为线程安全，来保证结果的准确。使用@synchronized关键字可以保证线程的安全。

---

### 线程的状态
```java
### 线程状态

源码：java.lang Thread
```java
    /**
     * A thread state.  A thread can be in one of the following states:
     * <ul>
     * <li>{@link #NEW}<br>
     *     A thread that has not yet started is in this state.
     *     </li>
     * <li>{@link #RUNNABLE}<br>
     *     A thread executing in the Java virtual machine is in this state.
     *     </li>
     * <li>{@link #BLOCKED}<br>
     *     A thread that is blocked waiting for a monitor lock
     *     is in this state.
     *     </li>
     * <li>{@link #WAITING}<br>
     *     A thread that is waiting indefinitely for another thread to
     *     perform a particular action is in this state.
     *     </li>
     * <li>{@link #TIMED_WAITING}<br>
     *     A thread that is waiting for another thread to perform an action
     *     for up to a specified waiting time is in this state.
     *     </li>
     * <li>{@link #TERMINATED}<br>
     *     A thread that has exited is in this state.
     *     </li>
     * </ul>
     *
     * <p>
     * A thread can be in only one state at a given point in time.
     线程在给定时间点只能处于一种状态。
     * These states are virtual machine states which do not reflect
     * any operating system thread states.
     这些状态是虚拟机状态，不反映任何操作系统线程状态。
     
     *
     * @since   1.5
     * @see #getState
     */
    public enum State {
        /**
         * Thread state for a thread which has not yet started.
         线程状态：尚未启动的线程
         */
        NEW,

        /**
         * Thread state for a runnable thread.  A thread in the runnable
         * state is executing in the Java virtual machine but it may
         * be waiting for other resources from the operating system
         * such as processor.
        线程状态：可运行状态的线程。
        处于可运行状态的线程正在Java虚拟机中执行，但它可能正在等待来自操作系统的其他资源
        例如处理器
         */
        RUNNABLE,

        /**
         * Thread state for a thread blocked waiting for a monitor lock.
         线程状态：线程状态被阻塞等待监视器锁定

         * A thread in the blocked state is waiting for a monitor lock
         * to enter a synchronized block/method or
         * reenter a synchronized block/method after calling
         处于阻塞状态的线程正在等待监视器锁定以进入同步块/方法
         或是在被调用后重新进入同步块/方法
         
         * {@link Object#wait() Object.wait}.
         */
        BLOCKED,

        /**
         * Thread state for a waiting thread.
         线程状态：处于等待状态的线程

         * A thread is in the waiting state due to calling one of the
         * following methods:
         由于调用以下方法之一，线程处于等待状态：
         
         * <ul>
         *   <li>{@link Object#wait() Object.wait} with no timeout</li>
         *   <li>{@link #join() Thread.join} with no timeout</li>
         *   <li>{@link LockSupport#park() LockSupport.park}</li>
         * </ul>
         *
         * <p>A thread in the waiting state is waiting for another thread to
         * perform a particular action.
         处于等待状态的线程正在等待另一个线程执行特定操作
         
         *
         * For example, a thread that has called <tt>Object.wait()</tt>
         * on an object is waiting for another thread to call
         * <tt>Object.notify()</tt> or <tt>Object.notifyAll()</tt> on
         * that object. A thread that has called <tt>Thread.join()</tt>
         * is waiting for a specified thread to terminate.
         
         例如：在某个对象等待另一个线程调用 <tt>Object.notify()</tt> 或 <tt>Object.notifyAll()</tt> 方法唤醒它的的时候，一个线程调用了 <tt>Object.wait()</tt> 方法.
         调用<tt> Thread.join（）</ tt>的线程正在等待指定的线程终止。
         *
         */
        WAITING,

        /**
         * Thread state for a waiting thread with a specified waiting time.
         线程状态：具有指定等待时间的线程等待状态。
         
         * A thread is in the timed waiting state due to calling one of
         * the following methods with a specified positive waiting time:
         由于调用以下包含指定等待时间参数的方法之一，线程处于该状态：
         
         * <ul>
         *   <li>{@link #sleep Thread.sleep}</li>
         *   <li>{@link Object#wait(long) Object.wait} with timeout</li>
         *   <li>{@link #join(long) Thread.join} with timeout</li>
         *   <li>{@link LockSupport#parkNanos LockSupport.parkNanos}</li>
         *   <li>{@link LockSupport#parkUntil LockSupport.parkUntil}</li>
         * </ul>
         */
        TIMED_WAITING,

        /**
         * Thread state for a terminated thread.
         * The thread has completed execution.
         线程状态：线程终止状态
         线程已完成执行
         */
        TERMINATED;
    }
```

线程的状态转换

【这里是个图片】
[线程状态转换.PNG]

线程在Running的过程中可能会遇到阻塞(Blocked)情况：

1）调用join()和sleep()方法，sleep()时间结束或被打断，join()中断,IO完成都会回到Runnable状态，等待JVM的调度。

2）调用wait()，使该线程处于等待池(wait blocked pool),直到notify()/notifyAll()，线程被唤醒被放到锁定池(lock blocked pool )，释放同步锁使线程回到可运行状态（Runnable）

3）对Running状态的线程加同步锁(Synchronized)使其进入(lock blocked pool ),同步锁被释放进入可运行状态(Runnable)。

此外，在runnable状态的线程是处于被调度的线程，此时的调度顺序是不一定的。Thread类中的yield方法可以让一个running状态的线程转入runnable。



