package main.java.com.johann.threadTest;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;
import java.util.concurrent.RunnableFuture;

/**
 * @ClassName: ThreadCreateway
 * @Description: java线程的实现方式
 * @Author: Johann
 * @Date: 2019-12-11 11:22
 **/
public class ThreadCreateway {

    public static void main(String[] args) {
        // 方式一 继承 Thread 类
        Thread a = new ExtendsThread("继承Thread");
        a.start();//start方法是一个native方法。

        // 方式二 实现 Runnable 接口
        Runnable runnable = new ImplementsRunnable();//初始化实现Runnable接口的类
        Thread b = new Thread(runnable,"实现Runnable");//对象作为参数传入
        b.start();

        // 方式三 实现 Callable 接口
        // 执行Callable任务后，可以获取一个Future的对象，在该对象上调用get就可以获取到Callable任务的返回值。
        Callable callable = new ImplementsCallable();
        RunnableFuture<Integer> futureTask = new FutureTask<Integer>(callable);
        Thread c = new Thread(futureTask,"实现Callable");
        c.start();
        //得到返回值
        try {
            System.out.println("返回值是: 【" + futureTask.get()+"】");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
/**
 * @Description: 方式1 - - 继承Thread类
 * @Author: Johann
 * @Date: 2019/12/11
*/
class ExtendsThread extends Thread{
    public ExtendsThread(){

    }
    public ExtendsThread(String threadName){
        super();
        this.setName(threadName);
    }
    @Override
    public void run() {
        System.out.println("ExtendsThread 继承了Thread类");
    }
}
/** 
* @Description: 方式2 - -  实现了 Runnable接口【无返回值的任务】
* @Author: Johann
* @Date: 2019/12/11 
*/ 
class ImplementsRunnable implements Runnable{
    @Override
    public void run() {
        System.out.println("ImplementsRunnable 实现了Runnable接口");
    }
}
/** 
* @Description: 方式3 - - 实现Callable接口【有返回值的任务】
* @Param:  
* @return:  
* @Author: Johann 
* @Date: 2019/12/11 
*/ 
class ImplementsCallable implements Callable {
    @Override
    public String call() throws Exception {
        String returnStr = "";
        for(int i = 0;i<5;i++){
            returnStr = "当前线程信息 ： "+Thread.currentThread().getName()+" - - - "+i;
            System.out.println(returnStr);
        }
        return returnStr;
    }
}



