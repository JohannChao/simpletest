package main.java.com.johann.jvm;

import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;

/**
 * @ClassName: JavaReference Java四种引用区别
 * @Description: TODO https://www.jianshu.com/p/ade51a91dfd6
 * @Author: Johann
 **/
public class JavaReference {


    public static void main(String[] args) {
        weakReference();
        //softReference();
        //phantomReference();
    }

    /** 
    * @Description:  强引用
     *  1，强引用是我们在编程过程中使用的最简单的引用，如代码String s=”abc”中变量s就是字符串对象”abc”的一个强引用。
     *  2，任何被强引用指向的对象都不能被垃圾回收器回收，这些对象都是在程序中需要的。
     *  3，开发中用的最多的就是强引用。
     *
    * @Param: [] 
    * @return: void 
    * @Author: Johann 
    */
    public static void strongReference(){
        String s="abc";//这是一个强引用
    }


    /**
    * @Description:  弱引用
     *  1，弱引用通过WeakReference类实现，弱引用和软引用很像但是弱引用的级别更低。
     *  2，对于只有弱引用的对象而言，当系统垃圾回收机制运行时，不管系统内存是否足够，总会回收该对象所占用的内存（立即回收的方式）。
     *  当然并不是说当一个对象只有弱引用时，它就会立即被回收—-正如那些使用引用的对象一样，必须等到系统垃圾回收机制运行时才会被回收。
     *
    * @Param: []
    * @return: void
    * @Author: Johann
    */
    public static void weakReference(){
        String str = new String("Struts2权威指南");
        //创建一个弱引用，让这个弱引用引用到“Struts2权威指南”字符串
        WeakReference weakReference = new WeakReference(str);
        //切断str引用和“Struts2权威指南”字符串之间的引用
        str = null;
        //取出弱引用所引用的对象
        System.out.println(weakReference.get());
        //强制进行垃圾回收
        System.gc();
        System.runFinalization();
        //再次取出弱引用所引用的对象
        System.out.println(weakReference.get());

        /**
         *  运行结果 ：
         *  Struts2权威指南
         *  null
         */
    }


    /** 
    * @Description: 软引用
     *  1，软引用需要通过SoftReference类来实现。
     *  2，当一个对象只具有软引用时，它有可能被垃圾回收机制回收。
     *  3，对于只有软引用的对象而言，当系统内存空间足够时，它不会被系统回收，程序也可使用该对象；当系统内存空间不足时，系统将会回收它。
     *
    * @Param: [] 
    * @return: void 
    * @Author: Johann 
    * @Date: 2020/6/9 
    */ 
    public static void softReference(){
        String str = new String("Struts2权威指南");
        //创建一个软引用，让这个弱引用引用到“Struts权威指南”字符串
        SoftReference softReference = new SoftReference(str);
        //切断str引用和“Struts2权威指南”字符串之间的引用
        str = null;
        //取出软引用所引用的对象
        System.out.println(softReference.get());
        //强制进行垃圾回收
        System.gc();
        System.runFinalization();
        //再次取出软引用所引用的对象
        System.out.println(softReference.get());

        /**
         *  运行结果 ：
         *  Struts2权威指南
         *  Struts2权威指南
         */
    }


    /** 
    * @Description:  虚引用
     *  1，虚引用通过PhantomReference类实现，虚引用完全类似于没有引用。
     *  2，虚引用对对象本身没有太大的影响，对象甚至感觉不到虚引用的存在。如果一个对象只有一个虚引用时。那它和没有引用的效果大致相同。
     *  虚引用主要用于跟踪对象被垃圾回收的状态，虚引用不能单独使用，虚引用必须和引用队列（ReferenceQueue）联合使用。
     *
    * @Param: [] 
    * @return: void 
    * @Author: Johann 
    */
    public static void phantomReference(){
        //创建一个字符串引用
        String str = new String("Struts2权威指南");
        //创建一个引用队列
        ReferenceQueue referenceQueue = new ReferenceQueue();
        //创建一个虚引用 让次虚引用引用到“Struts2权威指南”字符串
        PhantomReference phantomReference = new PhantomReference(str, referenceQueue);
        //切断str和“Struts2权威指南”之间的引用关系
        str = null;
        //取出虚引用所引用的对象，并不能通过虚引用访问被引用的对象，
        //所以此处输出的应该是null
        System.out.println(phantomReference.get());
        System.out.println(referenceQueue.poll());
        //强制进行垃圾回收
        System.gc();
        System.runFinalization();
        //取出引用队列最先进入队列中的引用于phantomReference进行比较
        System.out.println(referenceQueue.poll());
        System.out.println(referenceQueue.poll() == phantomReference);

        /**
         *  运行结果 ：
         *  null
         *  null
         *  java.lang.ref.PhantomReference@1b6d3586
         *  false
        */

        /**
         *  因为系统无法通过虚引用来获取被引用的对象，所以在执行第一个System.out.println(phantomReference.get())的时候，打印出来的是空值。即使此时系统并未进行强制垃圾回收）
         *  当程序强制垃圾回收后，只有虚引用引用的字符串对象将会被垃圾回收，当被引用的对象被回收后，对象的引用将被添加到关联的引用队列中去，
         *  也就时说，虚引用指向的字符串对象会被垃圾回收器回收，而自己本身将被添加到与之关联的引用队列中去。
        */
    }

    /**
     *  引用队列 ReferenceQueue
     *
     *  1，引用队列由java.lang.ref.ReferenceQueue类来表示，它用于保存被回收后对象的引用。
     *  当把软引用、弱引用和引用队列联合使用的时候，系统在回收被引用的对象之后，将把被回收对象对应的应用添加到关联的引用队列中。
     *  与软引用和弱引用不同的是，虚引用在对象被释放之后，将把已经回收对象对应的虚引用添加它的关联引用队列中，这是得可以在对象被回收之前采取行动。
     *
     *  2，软引用和弱引用可以单独使用，但是虚引用不能单独使用，单独使用虚引用没有太大的意义。
     *  虚引用的主要作用就是跟踪对象被垃圾回收的状态，程序可以通过检查与虚引用关联的引用队列中是否已经包含了该虚引用，从而了解虚引用所引用对象是否即将被回收。
     *
    */

}
