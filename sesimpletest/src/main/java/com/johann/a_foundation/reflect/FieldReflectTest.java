package com.johann.a_foundation.reflect;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;

/**
 * @ClassName: FieldReflectTest
 * @Description: 字段反射信息
 * @Author: Johann
 * @Date: 2021-10-28
 * @Refer: https://www.w3cschool.cn/java/java-field-reflection.html
 **/
public class FieldReflectTest {

    public static void main(String[] args) {
        FieldClass c = new FieldClass(1);
        Class filedClass = FieldClass.class;
        filedClass = c.getClass();

        ArrayList<String> fieldsDesciption = getDeclaredFieldsList(filedClass);
        System.out.println("Declared Fields for " + filedClass.getName());
        for (String desc : fieldsDesciption) {
            System.out.println(desc);
        }

        fieldsDesciption = getFieldsList(filedClass);
        System.out.println("\nAccessible Fields for " + filedClass.getName());
        for (String desc : fieldsDesciption) {
            System.out.println(desc);
        }


        // 根据字段名，获取指定的字段
        try{
            Field f = filedClass.getField("SUPER_FINAL");
            int mod = f.getModifiers() & Modifier.fieldModifiers();
            String modifiers = Modifier.toString(mod);

            Class type = f.getType();
            String typeName = type.getName();

            String fieldName = f.getName();

            System.out.println("获取指定字段： modifiers:"+modifiers+" typeName:"+typeName+" fieldName:"+fieldName);
        }catch (NoSuchFieldException e){
            e.printStackTrace();
        }

    }

    /** 返回所有可访问的公共字段，包括在类中声明或继承自超类的。
    * @Description:
    * @Param: [c] 
    * @return: java.util.ArrayList<java.lang.String> 
    * @Author: Johann 
    * @Date: 2021/10/29 
    */ 
    public static ArrayList<String> getFieldsList(Class c) {
        // 返回所有可访问的公共字段(public 修饰的字段)，包括在类中声明或继承自超类的。
        Field[] fields = c.getFields();
        ArrayList<String> fieldsList = getFieldsDesciption(fields);
        return fieldsList;
    }

    /** 返回所有只出现在类的声明中(不是从继承的字段)的字段。
    * @Description:
    * @Param: [c] 
    * @return: java.util.ArrayList<java.lang.String> 
    * @Author: Johann 
    * @Date: 2021/10/29 
    */ 
    public static ArrayList<String> getDeclaredFieldsList(Class c) {
        // 返回所有只出现在类的声明中(不是从继承的字段)的字段，所有修饰符修饰的字段。
        Field[] fields = c.getDeclaredFields();
        ArrayList<String> fieldsList = getFieldsDesciption(fields);
        return fieldsList;
    }

    /** 获取字段的详细信息
    * @Description: 获取字段的详细信息
    * @Param: [fields] 
    * @return: java.util.ArrayList<java.lang.String> 
    * @Author: Johann 
    * @Date: 2021/10/29 
    */ 
    public static ArrayList<String> getFieldsDesciption(Field[] fields) {
        ArrayList<String> fieldList = new ArrayList<>();

        for (Field f : fields) {
            int mod = f.getModifiers() & Modifier.fieldModifiers();
            String modifiers = Modifier.toString(mod);

            Class type = f.getType();
            String typeName = type.getName();
            //String typeName = type.getTypeName();
            //String typeName = type.getSimpleName();

            String fieldName = f.getName();

            fieldList.add("modifiers:"+modifiers+" typeName:"+typeName+" fieldName:"+fieldName);
        }

        return fieldList;
    }
}

class FieldSuperClass{
    private int private_superid;
    public int super_id;
    public static int SUPER_STATIC;
    public static final int SUPER_FINAL = 11;

    public FieldSuperClass(){
    }
}
class FieldClass extends FieldSuperClass{
    private int private_id;
    public int id;
    public static int STATIC;
    public final int FINAL;

    public FieldClass(int x){
        FINAL = x;
    }
}