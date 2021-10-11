package com.johann.javacollection;
import com.sun.xml.internal.ws.util.StringUtils;

import	java.io.ObjectStreamClass;

import java.lang.reflect.Field;
import java.util.*;

/**
 * @Author Johann
 * @Description //TODO
 * @Date 14:53 2019-10-30
 * @Param 
 * @return 
 **/
public class SetTest {

    public static void main(String [] args) {
        // 假设现在一个学生类，有学号和姓名，我现在hashcode方法重写的时候，只将学号参与计算，会出现什么情况？
        // hash碰撞几率增加.重写hashcode方法是，参与的字段要与重写equals方法是参与的字段相同。
        // 1，学号参与equals方法的重写，会导致增加hash碰撞几率。
        // 2, 学号没有参与equals方法重写，会导致两个相同的对象，hashcode值不同。

        // 往set里面put一个学生对象，然后将这个学生对象的学号改了，再put进去，可以放进set么？并讲出为什么
        // 可以放进去
        Set<StudentSet> hashSet = new HashSet<StudentSet>();
        StudentSet s = new StudentSet(11,"java");
        hashSet.add(s);
        System.out.println(hashSet);
        s.setCode(15);
        hashSet.add(s);
        System.out.println(hashSet);

        StudentSet stoo = new StudentSet(15, "java");
        hashSet.add(stoo);
        System.out.println(hashSet);

        /**###########################################################*/
        System.out.println("#######################################################");

        Set<StudentSetTwo> hashSetTwo = new HashSet<StudentSetTwo>();
        StudentSetTwo sTwo = new StudentSetTwo(11,"java");
        hashSetTwo.add(sTwo);
        System.out.println(hashSetTwo);
        sTwo.setCode(15);
        hashSetTwo.add(sTwo);
        System.out.println(hashSetTwo);

        StudentSetTwo sTwotoo = new StudentSetTwo(15, "java");
        hashSetTwo.add(sTwotoo);
        System.out.println(hashSetTwo);

        /**###########################################################*/
        System.out.println("#######################################################");

        Set<String> strHashSet = new HashSet<String> ();
        String a = "16";
        strHashSet.add(a);
        System.out.println(strHashSet);
        a = "17";
        strHashSet.add(a);
        System.out.println(strHashSet);


//        Map<StudentSet,String> hashMap = new HashMap<StudentSet,String>();
//        StudentSet s = new StudentSet(11,"java");
//        System.out.println(s.hashCode());
//        hashMap.put(s,"11");
//        System.out.println("hashMap.size = "+hashMap.size());
//
//        Class clazz = hashMap.getClass();
//        try{
//            Field field = clazz.getDeclaredField("table");
//            field.setAccessible(true);
//            Object[] objArray = (Object[]) field.get(hashMap);
//            System.out.println(objArray.length);
//            System.out.println(Arrays.toString(objArray));
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//
//        System.out.println("/*****************************************************************************************/");
//        /*****************************************************************************************/
//
//        s.setCode(16);
//        System.out.println(s.hashCode());
//        hashMap.put(s,"16");
//        System.out.println("hashMap.size = "+hashMap.size());
//
//        try{
//            Field field = clazz.getDeclaredField("table");
//            field.setAccessible(true);
//            Object[] objArray = (Object[]) field.get(hashMap);
//            System.out.println(objArray.length);
//            System.out.println(Arrays.toString(objArray));
//
//            Map.Entry en = (Map.Entry) objArray [11];
//
//            StudentSet s11 = (StudentSet) en.getKey();
//            String v11 = (String)en.getValue();
//            System.out.println("s11 : "+s11+"---v11 : "+v11);
//            System.out.println(hashMap.get(s11));
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//
//        System.out.println("/*****************************************************************************************/");
//        /*****************************************************************************************/
//
//        s.setCode(11);
//        System.out.println(s.hashCode());
//        hashMap.put(s,"11");
//        System.out.println("hashMap.size = "+hashMap.size());
//
//        try{
//            Field field = clazz.getDeclaredField("table");
//            field.setAccessible(true);
//            Object[] objArray = (Object[]) field.get(hashMap);
//            System.out.println(objArray.length);
//            System.out.println(Arrays.toString(objArray));
//
//            Map.Entry en = (Map.Entry) objArray [0];
//
//            StudentSet s16 = (StudentSet) en.getKey();
//            String v16 = (String)en.getValue();
//            System.out.println("s16 : "+s16+"---v16 : "+v16);
//            System.out.println(hashMap.get(s16));
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//
//        System.out.println("/*****************************************************************************************/");
//        /*****************************************************************************************/
//
//        try{
//            hashMap.put(new StudentSet(1,"1"),"1");
//            Field field = clazz.getDeclaredField("table");
//            field.setAccessible(true);
//            Object[] objArray = (Object[]) field.get(hashMap);
//            System.out.println("objArray.length : "+objArray.length);
//            System.out.println(Arrays.toString(objArray));
//            System.out.println("hashMap.size = "+hashMap.size());
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//        System.out.println("############################################################################");
//        try{
//            hashMap.put(new StudentSet(0,"0"),"0");
//            Field field = clazz.getDeclaredField("table");
//            field.setAccessible(true);
//            Object[] objArray = (Object[]) field.get(hashMap);
//            System.out.println("objArray.length : "+objArray.length);
//            System.out.println(Arrays.toString(objArray));
//            System.out.println("hashMap.size = "+hashMap.size());
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//
//        System.out.println("############################################################################");
//        try{
//            hashMap.put(new StudentSet(5,"5"),"5");
//            Field field = clazz.getDeclaredField("table");
//            field.setAccessible(true);
//            Object[] objArray = (Object[]) field.get(hashMap);
//            System.out.println("objArray.length : "+objArray.length);
//            System.out.println(Arrays.toString(objArray));
//            System.out.println("hashMap.size = "+hashMap.size());
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//
//        System.out.println("############################################################################");
//        try{
//            hashMap.put(new StudentSet(6,"6"),"6");
//            Field field = clazz.getDeclaredField("table");
//            field.setAccessible(true);
//            Object[] objArray = (Object[]) field.get(hashMap);
//            System.out.println("objArray.length : "+objArray.length);
//            System.out.println(Arrays.toString(objArray));
//            System.out.println("hashMap.size = "+hashMap.size());
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//
//        System.out.println("############################################################################");
//        try{
//            hashMap.put(new StudentSet(7,"7"),"7");
//            Field field = clazz.getDeclaredField("table");
//            field.setAccessible(true);
//            Object[] objArray = (Object[]) field.get(hashMap);
//            System.out.println("objArray.length : "+objArray.length);
//            System.out.println(Arrays.toString(objArray));
//            System.out.println("hashMap.size = "+hashMap.size());
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//
//        System.out.println("############################################################################");
//        try{
//            hashMap.put(new StudentSet(8,"8"),"8");
//            Field field = clazz.getDeclaredField("table");
//            field.setAccessible(true);
//            Object[] objArray = (Object[]) field.get(hashMap);
//            System.out.println("objArray.length : "+objArray.length);
//            System.out.println(Arrays.toString(objArray));
//            System.out.println("hashMap.size = "+hashMap.size());
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//
//        System.out.println("############################################################################");
//        try{
//            hashMap.put(new StudentSet(9,"9"),"9");
//            Field field = clazz.getDeclaredField("table");
//            field.setAccessible(true);
//            Object[] objArray = (Object[]) field.get(hashMap);
//            System.out.println("objArray.length : "+objArray.length);
//            System.out.println(Arrays.toString(objArray));
//            System.out.println("hashMap.size = "+hashMap.size());
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//
//        System.out.println("############################################################################");
//        try{
//            hashMap.put(new StudentSet(10,"10"),"10");
//            Field field = clazz.getDeclaredField("table");
//            field.setAccessible(true);
//            Object[] objArray = (Object[]) field.get(hashMap);
//            System.out.println("objArray.length : "+objArray.length);
//            System.out.println(Arrays.toString(objArray));
//            System.out.println("hashMap.size = "+hashMap.size());
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//
//        System.out.println("############################################################################");
//        try{
//            hashMap.put(new StudentSet(11,"11"),"11");
//            Field field = clazz.getDeclaredField("table");
//            field.setAccessible(true);
//            Object[] objArray = (Object[]) field.get(hashMap);
//            System.out.println("objArray.length : "+objArray.length);
//            System.out.println(Arrays.toString(objArray));
//            System.out.println("hashMap.size = "+hashMap.size());
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//
//        System.out.println("############################################################################");
//        try{
//            hashMap.put(new StudentSet(12,"12"),"12");
//            Field field = clazz.getDeclaredField("table");
//            field.setAccessible(true);
//            Object[] objArray = (Object[]) field.get(hashMap);
//            System.out.println("objArray.length : "+objArray.length);
//            System.out.println(Arrays.toString(objArray));
//            System.out.println("hashMap.size = "+hashMap.size());
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//
//        System.out.println("############################################################################");
//        try{
//            hashMap.put(new StudentSet(13,"13"),"13");
//            Field field = clazz.getDeclaredField("table");
//            field.setAccessible(true);
//            Object[] objArray = (Object[]) field.get(hashMap);
//            System.out.println("objArray.length : "+objArray.length);
//            System.out.println(Arrays.toString(objArray));
//            System.out.println("hashMap.size = "+hashMap.size());
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//
//        System.out.println("############################################################################");
//        try{
//            hashMap.put(new StudentSet(13,"13"),"13");
//            Field field = clazz.getDeclaredField("table");
//            field.setAccessible(true);
//            Object[] objArray = (Object[]) field.get(hashMap);
//            System.out.println("objArray.length : "+objArray.length);
//            System.out.println(Arrays.toString(objArray));
//            System.out.println("hashMap.size = "+hashMap.size());
//        }catch (Exception e){
//            e.printStackTrace();
//        }

    }

}
class StudentSet {
    private Integer code;
    private String name;

    public StudentSet(){}

    public StudentSet(Integer code, String name) {
        this.code = code;
        this.name = name;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "StudentSet{" +
                "code=" + code +
                ", name='" + name + '\'' +
                '}';
    }

    /**
     * @Author Johann
     * @Description 重写equals方法
     **/
    @Override
    public boolean equals(Object o) {
        if(o==null){
            return false;
        }else if (this == o){//是否与当前对象相同
            return true;
        }else if ((o.getClass() != this.getClass())){//是否与当前对象类相同，这种方式的比较相对于 !(obj instanceof Test) 比较会更好一些，因为一旦有子类，后面的这种比较容易出错。
            return false;
        } else {
            StudentSet studentSet = (StudentSet) o;

            if((studentSet.getCode()).equals(code)&&(studentSet.getName().equalsIgnoreCase(name))){//code相同，name相同，则认为相同
                return true;
            }else{
                return false;
            }
        }
    }


    /**
     * @Author Johann
     * @Description 重写hashcode方法
     **/
    @Override
    public int hashCode() {
        int hash = 0;
        hash = 31*hash + ((code==null) ? 0 :code.hashCode());
        hash = 31*hash + ((name==null) ? 0 :name.hashCode());
        return hash;
    }
}
class StudentSetTwo {
    private Integer code;
    private String name;

    public StudentSetTwo(){}

    public StudentSetTwo(Integer code, String name) {
        this.code = code;
        this.name = name;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "StudentSetTwo{" +
                "code=" + code +
                ", name='" + name + '\'' +
                '}';
    }
}