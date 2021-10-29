package com.johann.javaProblem;

import com.google.common.collect.ImmutableList;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @ClassName: ImmutableObjectTets
 * @Description: Java中的不可变对象
 * @Author: Johann
 * @Date: 2021-09-09
 **/
public class ImmutableObjectTets {

    public static void main(String[] args) {
        // 不可变对象
        ImmutableObject immutableObject = new ImmutableObject(10);
        // 校验带来的副作用
        testV(immutableObject);
        System.out.println(immutableObject.toString());

        // String，不可变对象
        String s1 = "I love China";
        String s2 = s1;
        System.out.println(s1 == s2);
        System.out.println(s1.replace("China","CHINA"));
        System.out.println(s1);
        System.out.println(s2);
        System.out.println(s1 == s2);

        // 不可变集合
        List<Integer> list = new ArrayList<Integer>();
        list.add(1);
        System.out.println("list: "+list);

        // JDK 自带的不可变集合
        List list2 = Arrays.asList(list.toArray());
        List list3 = new ArrayList(list); // elementData = c.toArray();
        List unModifiableList = Collections.unmodifiableList(list);
        List unModifiableList2 = Collections.unmodifiableList(list2);
        List unModifiableList3 = Collections.unmodifiableList(list3);

        // 使用Google的Guava包中提供的 ImmutableList
        ImmutableList immutableList = ImmutableList.copyOf(list);

        list.add(2);
        System.out.println("unModifiableList: "+unModifiableList);
        System.out.println("unModifiableList2: "+unModifiableList2);
        System.out.println("unModifiableList3: "+unModifiableList3);
        System.out.println("immutableList: "+immutableList);


        // 反射的方法 修改不可变对象
        try{
//            String s = "Hello World";
//            System.out.println("s = " + s);
//            Field valueFieldOfString = String.class.getDeclaredField("value");
//            valueFieldOfString.setAccessible(true);
//            char[] value = (char[]) valueFieldOfString.get(s);
//            value[5] = '_';
//            System.out.println("s = " + s);

            ImmutableObject immutableObject1 = new ImmutableObject(20);
            Field objectFileds = ImmutableObject.class.getDeclaredField("values");
            // 如果setAccessible(false)【默认即为 false】，则 java 会对这个反射对象进行校验，并禁止对 “private”修饰的字段，进行修改
            // java.lang.IllegalAccessException: Class com.johann.javaProblem.ImmutableObjectTets can not access a member of class com.johann.javaProblem.ImmutableObject with modifiers "private"
            objectFileds.setAccessible(true);
            int v = objectFileds.getInt(immutableObject1);
            System.out.println(immutableObject1.toString() + "; v="+v);
            objectFileds.setInt(immutableObject1,22);
            System.out.println(immutableObject1.toString());



        }catch (Exception e){
            System.out.println(e.toString());
        }
    }

    public static boolean testV(ImmutableObject o){
        if(o.getValues() > 9){
            o.setValues(9);
            return false;
        }
        return true;
    }

}

/**
 *  只提供了 getter 方法，一旦实例化，就无法被改变。
 */
class ImmutableObject {
    private int values;
    public ImmutableObject(int values) {
        this.values = values;
    }
    public int getValues() {
        return this.values;
    }

    public void setValues(int values) {
        this.values = values;
    }

    @Override
    public String toString() {
        return "ImmutableObject{" +
                "values=" + values +
                '}';
    }
}
