package main.java.com.johann.java8;

import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.function.BinaryOperator;

/**
 * @ClassName: LambdaTest
 * @Description: TODO
 * @Author: Johann
 * @Date: 2020-09-24
 * @reference: https://github.com/CarpenterLee/JavaLambdaInternals
 **/
public class LambdaTest {

    public static void main(String[] args) {

        /* <p> */
        // Lambda and Anonymous Classes(I)
        // 下文将介绍如何使用Lambda表达式简化匿名内部类的书写，但Lambda表达式并不能取代所有的匿名内部类，只能用来取代函数接口（Functional Interface）的简写。
        // ### 匿名内部类的对比
        // 1.7及以前
        new Thread(new Runnable() { //匿名类 接口名
            @Override
            public void run() {// 方法名
                System.out.println("hello");
            }
        }
        ).start();

        // 1.8 Lymbda表达式. 省略了匿名类的 接口名和函数名
        new Thread(
                //() -> System.out.println("hello Lymbda")
                () -> {
                    System.out.println("hello Lymbda"); //多行的话，写在代码块中
                    System.out.println("hello Lymbda2");
                }
        ).start();

        // ### 函数带参数的匿名类
        // 1.7
        List<String> list = Arrays.asList("Love","I","China");
        Collections.sort(list, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                if(o1 == null)
                    return -1;
                else if (o2 == null)
                    return 1;
                else
                    return o1.length() - o2.length();
            }
        });
        System.out.println(list.toString());

        /**
        *  1.8 Lymbda 表达式
         * 不只省略了 接口名和函数名，连同函数参数的参数类型也一同省略了
         * 这得益于javac的类型推断机制，编译器能够根据上下文信息推断出参数的类型，当然也有推断失败的时候，这时就需要手动指明参数类型了
        */
        List<String> listLymbda = Arrays.asList("Love","I","China");
        Collections.sort(listLymbda,(o1, o2) -> {
        //Collections.sort(listLymbda,(String o1, String o2) -> {
            if(o1 == null)
                return -1;
            else if (o2 == null)
                return 1;
            else
                return o1.length() - o2.length();
        });
        System.out.println(listLymbda.toString());

        /**
         * Lymbda表达式 简写的依据
         *
         * 能够使用Lambda的依据是必须有相应的函数接口（函数接口，是指内部只有一个抽象方法的接口）
         * 这一点跟Java是强类型语言吻合，也就是说你并不能在代码的任何地方任性的写Lambda表达式
         * 实际上Lambda的类型就是对应函数接口的类型。
         *
         * Lambda表达式另一个依据是类型推断机制，在上下文信息足够的情况下，编译器可以推断出参数表的类型，而不需要显式指名
        */
        ActionListener listener = event -> System.out.println("button clicked");

        BinaryOperator<Long> add = (Long x, Long y) -> x + y;//
        BinaryOperator<Long> addImplicit = (x, y) -> x + y;//  类型推断


        // 自定义函数接口
        MyInterface<String> mingzi = str -> System.out.println(str);

        // 还可以这么写
        MyStream<String> myStream = new MyStream<String>();
        myStream.setList(listLymbda);
        myStream.myForEach(anyThing -> System.out.println(anyThing));
        /* </p> */


        /* <p> */
        // Lambda and Anonymous Classes(II)
        // 下文将进一步区分Lambda表达式和匿名内部类在JVM层面的区别
        // 经过前文介绍，我们看到Lambda表达式似乎只是为了简化匿名内部类书写，这看起来仅仅通过语法糖在编译阶段把所有的Lambda表达式替换成匿名内部类就可以了。
        // 但实时并非如此。在 JVM 层面，Lambda表达式和匿名内部类有着明显的差别。


        // 匿名内部类本质上仍是一个类，只是无需我们自己命名，编译器会自动为这个匿名内部类命名。
        // 因此，有一个匿名内部类的类，经过编译，会生成两个 class 文件。

        // Lambda表达式通过invokedynamic指令实现，书写Lambda表达式不会产生新的类。
        // 反编译之后我们发现Lambda表达式被封装成了主类的一个私有方法，并通过invokedynamic指令进行调用。

        // ### 推论，this引用的意义
        // 既然Lambda表达式不是内部类的简写，那么Lambda内部的this引用也就跟内部类对象没什么关系了。在Lambda表达式中this的意义跟在表达式外部完全一样。
        new  LambdaTest().r1.run();
        new  LambdaTest().r2.run();
        //输出的结果显示，打印了两遍 "Lymbda表达式中的 this "，而不是两个引用地址【注：调用的是 外部类（姑且这么称之）的 已重写的toString()方法】
        /* </p> */


    }

    Runnable r1 = () -> { System.out.println(this); }; // this 默认调用 toString()方法
    Runnable r2 = () -> { System.out.println(toString()); };

    public String toString() { return "Lymbda表达式中的 this "; }
}

// 注释@FunctionalInterface，是可选的，用于帮助检查当前的接口，是否符合 函数接口的定义，如果这个接口不止一个函数，则注释报错提示。
// Multiple non-overriding abstract methods found in interface main.java.com.johann.java8.myInterface
@FunctionalInterface
interface MyInterface<T>{
    void print(T t);
    //void do(T t);
}

class MyStream<T>{
    private List<T> list;

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    public void myForEach(MyInterface myInterface){
        for(T t : list){
            myInterface.print(t);
        }
    }
}