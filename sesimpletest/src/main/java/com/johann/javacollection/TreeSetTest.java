package main.java.com.johann.javacollection;

import java.util.Comparator;
import java.util.Set;
import java.util.TreeSet;

/**
 * @ClassName TreeSetTest
 * @Description TreeSet排序测试
 * @Author Johann
 **/
public class TreeSetTest {

    public static void main(String[] args) {

        // 1，TreeSet使用1 对象没有实现Comparable接口，需要在TreeSet的构造函数中新增匿名内部类Comparator，重新compare方法
        Set<Student> studentSet = new TreeSet<Student>(new Comparator<Student>() {
            @Override
            public int compare(Student o1, Student o2) {
                if(o1.getCode() > o2.getCode()){
                    return 1;
                }else if (o1.getCode() < o2.getCode()){
                    return -1;
                }else {
                    return 0;
                }
            }
        });
        studentSet.add(new Student(112,"zyh112"));
        studentSet.add(new Student(112,"zyhcopy112"));
        studentSet.add(new Student(111,"zyh111"));
        studentSet.add(new Student(113,"zyh113"));
        System.out.println(studentSet);

        // 2，TreeSet使用2 对象实现了Comparable接口，重写compareTo方法。无需在TreeSet的构造函数中新增匿名内部类
        Set<student2> student2Set = new TreeSet<student2>();
        student2Set.add(new student2(312,"zyh312"));
        student2Set.add(new student2(312,"zyhcopy312"));
        student2Set.add(new student2(311,"zyh311"));
        student2Set.add(new student2(313,"zyh313"));
        System.out.println(student2Set);

        //3， TreeSet不能使用实现Comparator接口的对象。报异常:student1 cannot be cast to java.lang.Comparable
        Set<student1> student1Set = new TreeSet<student1>();
        student1Set.add(new student1(212,"zyh212"));
        student1Set.add(new student1(212,"zyhcpoy212"));
        student1Set.add(new student1(211,"zyh211"));
        student1Set.add(new student1(213,"zyh213"));
        System.out.println(student1Set);
    }
}


/**
 * 待使用的普通对象
 */
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

/**
 * 实现Comparator接口的对象
 */
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

/**
 * 实现Comparable接口的对象
 */
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