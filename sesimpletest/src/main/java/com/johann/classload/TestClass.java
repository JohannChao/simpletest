package com.johann.classload;

/**
 * @ClassName TestClass
 * @Description TODO
 * @Author Johann
 * @Date 2019-07-20 13:56
 **/
public class TestClass {

    public static void main(String[] args) {

        //静态内部类实例化格式： OutClass.StaticInner staticInner = new OutClass.StaticInner();
        OuterClass.StaticInnerClass staticInnerClass = new OuterClass.StaticInnerClass();
        staticInnerClass.innerMethod();
        OuterClass.StaticInnerClass.innerStaticMethod();

        //OuterClass out=OuterClass.getInstance();

        System.out.println("加载完成 。。。。");
    }
}
