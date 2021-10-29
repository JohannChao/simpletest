package com.johann.a_foundation.reflect;

/**
 * @ClassName: ClassTest
 * @Description: TODO
 * @Author: Johann
 * @Date: 2021-10-27
 **/
public class ClassTest {

    public static void main(String[] args) {
        // Class类的实例对象，用于记录类描述信息。
        // Class类没有公共的构造方法，无法通过new运算符实例化；只能通过对象的getClass方法，或是通过Class.forName(…)来获得实例。
        Class<?> _class;

        // 1,根据对象获取class实例
        String str = "";
        _class = str.getClass();
        System.out.println("对象.getClass: "+_class);
        // 2,根据类获取class实例
        _class = Integer.class;
        System.out.println("类.class: "+_class);
        // 3,使用Class.forName获取class实例
        try{
            _class = Class.forName("java.lang.String");
            System.out.println("Class.forName: "+_class);
        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }

        // Class 中的其他方法
        // 获取超类的Class
        System.out.println("getSuperclass: "+ _class.getSuperclass());
        // 获取类加载器
        ClassLoader cl = _class.getClassLoader();
        // 判断是否是注解类型
        System.out.println(_class.isAnnotation());
        // 判断是否是数组类
        System.out.println(_class.isArray());
        // 为类创建一个新实例
        try{
            Object obj =  _class.newInstance();
            System.out.println("obj: "+obj.getClass());
        }catch(Exception e1){
            e1.printStackTrace();
        }
    }


}
