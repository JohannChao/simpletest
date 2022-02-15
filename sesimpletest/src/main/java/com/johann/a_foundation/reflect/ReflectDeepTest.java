package com.johann.a_foundation.reflect;


/**
 * @ClassName: ReflectDeepTest
 * @Description: TODO
 * @Author: Johann
 * @Date: 2021-11-04 16:27
 * https://www.w3cschool.cn/java/java-reflex.html
 **/
public class ReflectDeepTest {
    
    /**
     * java反射中包含以下类
     * java.lang.Class;
     * java.lang.reflect.Constructor;
     * java.lang.reflect.Field;
     * java.lang.reflect.Method;
     * java.lang.reflect.Modifier;
     *
    */

    public static void main(String[] args) {
        ReflectDeepTest reflectDeepTest = new ReflectDeepTest();
        //
        System.out.println(reflectDeepTest.getClass().getName());
    }
}
