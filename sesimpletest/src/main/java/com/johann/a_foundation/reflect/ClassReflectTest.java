package com.johann.a_foundation.reflect;

import java.io.Serializable;
import java.lang.reflect.Modifier;
import java.lang.reflect.TypeVariable;

/**
 * @ClassName: ClassReflectTest
 * @Description: 类反射信息
 * @Author: Johann
 * @Date: 2021-10-28
 * @Refer: https://www.w3cschool.cn/java/java-class-reflection.html
 **/
public class ClassReflectTest {

    public static void main(String[] args) {

        //JohannClass johannClass = new JohannClass();
        String classDesciption = getClassDescription(JohannClass.class);
        System.out.println(classDesciption);

    }

    /** 
    * @Description: 获取类的详细描述
    * @Param: [c] 
    * @return: java.lang.String 
    * @Author: Johann 
    * @Date: 2021/10/28 
    */ 
    public static String getClassDescription(Class c){

        StringBuffer classDesc = new StringBuffer();
        int modifierBits = 0;
        String keyword = "";

        System.out.println("Modifier.interfaceModifiers(): "+Modifier.interfaceModifiers());
        System.out.println("Modifier.classModifiers(): "+Modifier.classModifiers());
        System.out.println("getModifiers() : "+c.getModifiers());

        // 判断该类是否是接口
        if(c.isInterface()){
            // 获取修饰符的code
            // getModifiers() 返回类的所有修饰符
            modifierBits = c.getModifiers() & Modifier.interfaceModifiers();

            // 为何判断该类是否是接口的时候，还要再次判断是否是注解类型？这是因为，[isAnnotation]源码注释中有这句话：as all annotation types are also interfaces。所有注解皆是接口
            if(c.isAnnotation()){
                keyword = "@interface";
            }else{
                keyword = "interface";
            }
        }else if(c.isEnum()){
            modifierBits = c.getModifiers() & Modifier.classModifiers();
            keyword = "enum";
        }

        modifierBits = c.getModifiers() & Modifier.classModifiers();
        keyword = "class";

        // 根据修饰符的code获取对应的字符串
        // getModifiers()方法返回一个整数。我们必须调用 java.lang.reflect.Modifier.toString(int modifiers)以获得修饰符的文本形式。
        String modifiers = Modifier.toString(modifierBits);
        classDesc.append(modifiers);
        classDesc.append(" " + keyword);
        String simpleName = c.getSimpleName();
        classDesc.append(" " + simpleName);

        // 获取类的泛型类型
        String genericParms = getGenericTypeParams(c);
        classDesc.append(genericParms);

        // 获取超类的名称
        Class superClass = c.getSuperclass();
        if (superClass != null) {
            String superClassSimpleName = superClass.getSimpleName();
            classDesc.append("  extends " + superClassSimpleName);
        }

        // 获取类的接口
        String interfaces = getClassInterfaces(c);
        if (interfaces != null) {
            classDesc.append("  implements " + interfaces);
        }
        return classDesc.toString();
    }
    
    /**
     *  获取类的泛型类型
    * @Description: 获取类的泛型类型
    * @Param: [c] 
    * @return: java.lang.String 
    * @Author: Johann 
    * @Date: 2021/10/28 
    */ 
    public static String getGenericTypeParams(Class c) {
        StringBuilder sb = new StringBuilder();

        // 返回一个泛型声明的类型变量数组
        TypeVariable<?>[] typeParms = c.getTypeParameters();

        if (typeParms.length > 0) {
            String[] paramNames = new String[typeParms.length];
            for (int i = 0; i < typeParms.length; i++) {
                paramNames[i] = typeParms[i].getTypeName();
            }
            sb.append("<");
            String parmsList = String.join(",", paramNames);
            sb.append(parmsList);
            sb.append(">");
        }
        System.out.println("泛型声明的类型变量："+sb.toString());
        return sb.toString();
    }

    /**
     *  获取类的接口
    * @Description: 返回一个数组，数组中中是这个对象对应类的接口
     *  1，如果这个对象是一个类，那么返回的是这个类实现的接口，数组中的顺序与 implements后面接口的顺序相同；
     *  2，如果这个对象是一个接口，那么返回的是这个接口继承的接口，数组中的顺序与 extends 后面接口的顺序相同；
     *  3，如果这个对象是一个基本类型[primitive type]或是void，那么返回是个长度为 0 的数组；
     *  4，如果这个对象是个数组，那么返回的数组为{Cloneable,Serializable}
    * @Param: [c] 
    * @return: java.lang.String 
    * @Author: Johann 
    * @Date: 2021/10/28 
    */ 
    public static String getClassInterfaces(Class c) {

        // 测试数组
        //String[] strings = {"1","2"};
        //c = strings.getClass();
        //打印结果： 对象实现的接口：Cloneable, Serializable

        Class[] interfaces = c.getInterfaces();
        String interfacesList = null;
        if (interfaces.length > 0) {
            String[] interfaceNames = new String[interfaces.length];
            for (int i = 0; i < interfaces.length; i++) {
                interfaceNames[i] = interfaces[i].getSimpleName();
            }
            interfacesList = String.join(", ", interfaceNames);
        }
        System.out.println("对象实现的接口："+interfacesList);
        return interfacesList;
    }

}

final class JohannClass implements Cloneable, Serializable {
    private String name = "johann";
    private int age = 28;

    public JohannClass(){

    }

    public JohannClass(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public JohannClass clone(){
        try {
            return (JohannClass) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public String toString() {
        return "JohannClass{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}