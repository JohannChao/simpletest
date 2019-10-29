package main.java.com.johann.javacollection;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;


public class SetTest {

    public static void main(String [] args) {
        // 假设现在一个学生类，有学号和姓名，我现在hashcode方法重写的时候，只将学号参与计算，会出现什么情况？
        Set<StudentSet> hashSet = new HashSet<StudentSet>();
        StudentSet s = new StudentSet(11,"java");
        hashSet.add(s);
        System.out.println(hashSet);
        s.setCode(15);
        hashSet.add(s);
        System.out.println(hashSet);
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