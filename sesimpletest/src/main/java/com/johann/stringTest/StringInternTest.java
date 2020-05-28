package main.java.com.johann.stringTest;

/**
 * @ClassName StringInternTest
 * @Description TODO
 * @Author Johann
 * @Date 2019-11-13 15:36
 **/
public class StringInternTest {

    public static void main(String[] args) {
        // 字符串常量池中加入 "some"字符串
        String a = "some";
        // 下面这一句，实际执行步骤是：创建一个StringBuilder对象，将所相加的字符串append起来，最后调用StringBuilder的toString方法，在堆中创建一个"paramsome"对象,没有加入字符串常量池
        String param = "param" + a;
        // 下面这一句，实际执行步骤是：创建一个StringBuilder对象，将所相加的字符串append起来，最后调用StringBuilder的toString方法，在堆中创建一个"paramsome"对象,没有加入字符串常量池
        //String param = new String("param" + a);
        System.out.println(param.intern()==param);
        String paramSome = "paramsome";
        System.out.println(param == paramSome);// true

//        System.out.println("==================================");
//
//        // "par"+"b" 在编译阶段，会被优化为 "parb"(可以通过编译完成后的class文件查看)，所以等同于 new String("parb")
//        String param1 = new String("par" + "b");
//        // 上一句会在堆中创建一个字面量为"parb"的对象，在字符串常量池中创建一个字面量为"parb"的字符串。该句无影响
//        param1.intern();
//        // 指向字符串常量池中的
//        String paramSame1 = "parb";
//        System.out.println(param1 == paramSame1);// false
//
//        System.out.println("==================================");

//        // 编译阶段优化为 "helloworld",在字符串常量池中创建一个字面量为 "helloworld"的字符串
//        String s1 = "hello"+"world";
//        String s0 = "helloworld";
//        System.out.println(s1==s0);// true
//
//        System.out.println("==================================");
//
//        String s2 = "nihao";
//        String s3 = "java";
//        // 下面这一句，实际执行步骤是：创建一个StringBuilder对象，将所相加的字符串append起来，最后调用StringBuilder的toString方法，在堆中创建一个"nihaojava"对象
//        // 但是，在字符串常量池中没有加入 "nihaojava"字符串。s4引用指向堆中对象
//        String s4 = s2+s3;
//        // 在字符串常量池中加入 字面量为“nihaojava”的堆中对象的引用
//        s4.intern();
//        // s5指向字符串常量池中字面量为 "nihaojava"的字符串，实际上这个字符串是堆中字面量为 "nihaojava"对象的引用
//        String s5 = "nihaojava";
//        System.out.println(s4==s5); // true
    }
}