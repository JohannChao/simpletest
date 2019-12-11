package main.java.com.johann.threadTest;


public class BasicsThreadTest {


    public static void main(String[] args) {
        //线程共享数据
        MyThread mythread=new MyThread();
        //下列线程都是通过mythread对象创建的
        Thread a=new Thread(mythread,"A");
        Thread b=new Thread(mythread,"B");
        Thread c=new Thread(mythread,"C");
        Thread d=new Thread(mythread,"D");
        Thread e=new Thread(mythread,"E");
        a.start();
        b.start();
        c.start();
        d.start();
        e.start();
        //此时发现出现了不正确的数据。
        //这是因为在大多数jvm中，count--的操作分为如下下三步： 1，取得原有count值； 2，计算i -1； 3，对i进行赋值。
        //所以多个线程同时访问时出现问题就是难以避免的了。解决办法是 ： 在run方法前加上synchronized关键字即可得到正确答案。

        //线程不共享数据
        MyThreadCopy copy_a = new MyThreadCopy("Copy_A");
        MyThreadCopy copy_b = new MyThreadCopy("Copy_B");
        MyThreadCopy copy_c = new MyThreadCopy("Copy_C");
        copy_a.start();
        copy_b.start();
        copy_c.start();
    }
}
class MyThread extends Thread {

    private int count = 5;

    @Override
    public void run() { //加上 synchronized关键字，即可得到正确的答案
        super.run();
        count--;
        System.out.println("由 " + MyThread.currentThread().getName() + " 计算，count=" + count);
    }
}

class MyThreadCopy extends Thread {

    private int count = 5;
    public MyThreadCopy(String name) {
        super();
        this.setName(name);
    }
    @Override
    public void run() {
        super.run();
        while (count > 0) {
            count--;
            System.out.println("由 " + MyThread.currentThread().getName() + " 计算，count=" + count);
        }
    }
}
