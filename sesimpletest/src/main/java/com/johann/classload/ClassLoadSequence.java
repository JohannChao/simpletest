package com.johann.classload;

import java.lang.reflect.InvocationTargetException;

/**
 * @ClassName ClassLoadSequence
 * @Description 验证加载顺序,通过 DEBUG step into 验证加载顺序
 * @Author Johann
 * @Date 2019-10-14
 **/
public class ClassLoadSequence {

    public static long date=System.currentTimeMillis();//1
    public int num=1;//4---7
    static{
        System.out.println("ClassLoadSequence 静态块："+System.currentTimeMillis());//2
    }
    public ClassLoadSequence(){//3---6
        System.out.println("ClassLoadSequence 构造函数："+System.currentTimeMillis());//5---8
    }
    static class Inner{
        public static long date=System.currentTimeMillis();//10
    }
    public long getDate(){
        return Inner.date;//9---11
    }
    public static void main(String[] args) throws NoSuchMethodException, SecurityException,InstantiationException, IllegalAccessException,
    IllegalArgumentException, InvocationTargetException

    {
        ClassLoadSequence classLoadSequence=new ClassLoadSequence();
        System.out.println("构造对象 classLoadSequence ： "+classLoadSequence);
        ClassLoadSequence classLoadSequence1=new ClassLoadSequence();
        System.out.println("构造对象 classLoadSequence1 ： "+classLoadSequence1);

        System.out.println("main方法---classLoadSequence : "+classLoadSequence.getDate());
        System.out.println("main方法---classLoadSequence1 : "+classLoadSequence1.getDate());
    }
}
