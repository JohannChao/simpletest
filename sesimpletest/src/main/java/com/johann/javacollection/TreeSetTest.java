package main.java.com.johann.javacollection;

import java.util.Comparator;

/**
 * @ClassName TreeSetTest
 * @Description TreeSet排序测试
 * @Author Johann
 * @Date 2019-10-21 17:35
 **/
public class TreeSetTest {

}

class Student {
    private Integer code;
    private String name;

    public Student(){}

    public Student(Integer code, String name) {
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
        return "Student{" +
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
        } else if(o instanceof Student){//是否与当前对象类相同
            Student student = (Student) o;
            if((student.getCode()).equals(code)){//code相同，认定为是同一对象
                return true;
            }else{
                return false;
            }
        }else {
            return false;
        }
    }
    /**
     * @Author Johann
     * @Description 重写hashcode方法
     **/
    @Override
    public int hashCode() {
        return (code==null) ? 0 :code.hashCode();
    }
}

class student1 implements Comparator<student1> {
    private Integer code;
    private String name;

    public student1(){}

    public student1(Integer code, String name) {
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
        return "Student{" +
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
        } else if(o instanceof Student){//是否与当前对象类相同
            Student student = (Student) o;
            if((student.getCode()).equals(code)){//code相同，认定为是同一对象
                return true;
            }else{
                return false;
            }
        }else {
            return false;
        }
    }
    /**
     * @Author Johann
     * @Description 重写hashcode方法
     **/
    @Override
    public int hashCode() {
        return (code==null) ? 0 :code.hashCode();
    }

    /**
     * @Author Johann
     * @Description 重写compare方法
     **/
    @Override
    public int compare(student1 o1, student1 o2) {
        if(o1.getCode() > o2.getCode()){
            return 1;
        }else if (o1.getCode() < o2.getCode()){
            return -1;
        }else {
            return 0;
        }
    }
}

class student2 implements Comparable<student2>{
    private Integer code;
    private String name;

    public student2(){}

    public student2(Integer code, String name) {
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
        return "Student{" +
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
        } else if(o instanceof Student){//是否与当前对象类相同
            Student student = (Student) o;
            if((student.getCode()).equals(code)){//code相同，认定为是同一对象
                return true;
            }else{
                return false;
            }
        }else {
            return false;
        }
    }
    /**
     * @Author Johann
     * @Description 重写hashcode方法
     **/
    @Override
    public int hashCode() {
        return (code==null) ? 0 :code.hashCode();
    }

    /**
     * @Author Johann
     * @Description 重写compareTo方法
     **/
    @Override
    public int compareTo(student2 o) {
        if(code > o.getCode()){
            return 1;
        }else if (code < o.getCode()){
            return -1;
        }else {
            //return 0;
            return name.compareTo(o.name);
        }
    }
}