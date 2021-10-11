package com.johann.java8;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;
import java.util.function.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @ClassName: StreamReduction
 * @Description: Stream 规约操作（reduction operation）
 * @Author: Johann
 * @Date: 2020-10-09
 **/
public class StreamReduction {

    /**
     *  规约操作（reduction operation）又被称作折叠操作（fold），是通过某个连接动作将所有元素汇总成一个汇总结果的过程。
     *  元素求和、求最大值或最小值、求出元素总个数、将所有元素转换成一个列表或集合，都属于规约操作。
     *  Stream类库有两个通用的规约操作reduce()和collect()，也有一些为简化书写而设计的专用规约操作，比如sum()、max()、min()、count()等。
    */

    /**
     *  1,reduce 操作
     *  reduce操作可以实现从一组元素中生成一个值，sum()、max()、min()、count()等都是reduce操作，将他们单独设为函数只是因为常用。
     *  reduce()的方法定义有三种重写形式：
     *      a. {@link java.util.stream.Stream#reduce(BinaryOperator)}
     *      b. {@link java.util.stream.Stream#reduce(Object, BinaryOperator)}
     *      c. {@link java.util.stream.Stream#reduce(Object, BiFunction, BinaryOperator)}
     *  虽然函数定义越来越长，但语义不曾改变，多的参数只是为了指明初始值（参数identity），或者是指定并行执行时多个部分结果的合并方式（参数combiner）。
     *
     *   Optional是（一个）值的容器，使用它可以避免null值的麻烦
    */

    public static void testReduce(){
        // 找出长度最长的单词
        Stream<String> stream = Stream.of("I","love","China","!");
//        Optional<String> maxoptional = stream.reduce(new BinaryOperator<String>() {
//            @Override
//            public String apply(String s, String s2) {
//                if(s.length() > s2.length())
//                    return s;
//                else
//                    return s2;
//            }
//        });

        Optional<String> maxoptional = stream.reduce((s, s2) -> s.length()>s2.length() ? s :s2);

//        Optional<String> maxoptional = stream.max((s,s1) -> s.length()-s1.length());

        System.out.println("最长的元素是 ： "+maxoptional.get());


        // 求一组单词的长度和
        Stream<String> sumstream = Stream.of("I","love","China","!");
//        Integer lengthSum = sumstream.reduce(0,  // 初始值 (1)
//                new BiFunction<Integer, String, Integer>() {    // 结果累加器 (2)
//                    @Override
//                    public Integer apply(Integer integer, String s) {
//                        return integer + s.length();
//                    }
//                },
//                new BinaryOperator<Integer>() {     // 部分结果拼接器，并行执行时才会用到 (3)
//                    @Override
//                    public Integer apply(Integer integer, Integer integer2) {
//                        return integer + integer2;
//                    }
//                }
//        );

        Integer lengthSum =  sumstream.reduce(0,(sum,s) -> sum+s.length(),(sum1,sum2) -> sum1+sum2);
        System.out.println("元素长度和 ： "+lengthSum);
    }

    /**
     *  2,collect 方法
     *
     */

    /**
     *  {@link java.util.stream.Stream#collect(Collector)} 方法注释翻译
     *
     * Performs a <a href="package-summary.html#MutableReduction">mutable
     * reduction</a> operation on the elements of this stream using a
     * {@code Collector}.
     * 使用{@code Collector}在此流的元素上执行可变的归约操作。
     *
     * A {@code Collector} encapsulates the functions used as arguments to
     * {@link java.util.stream.Stream#collect(Supplier, BiConsumer, BiConsumer)},
     * allowing for reuse of collection strategies and composition of collect operations such as
     * multiple-level grouping or partitioning.
     * {@code Collector}封装了用作{@link #collect}的参数的函数，
     * 从而允许重用收集策略并组成收集操作，例如多级分组或分区。
     *
     * <p>If the stream is parallel, and the {@code Collector}
     * is {@link Collector.Characteristics#CONCURRENT concurrent}, and
     * either the stream is unordered or the collector is
     * {@link Collector.Characteristics#UNORDERED unordered},
     * then a concurrent reduction will be performed
     * (see {@link Collector} for details on concurrent reduction.)
     * 如果流是并行的，并且{@code Collector}是{@link Collector.Characteristics＃CONCURRENT并发}，
     * 并且无论流是无序的，或者收集器是{@link Collector.Characteristics＃UNORDERED无序}，则并发归约将被执行。
     * （有关并行减少的详细信息，请参见{@link Collector}。）
     *
     * <p>This is a <a href="package-summary.html#StreamOps">terminal
     * operation</a>.
     * 这是终端操作
     *
     * <p>When executed in parallel, multiple intermediate results may be
     * instantiated, populated, and merged so as to maintain isolation of
     * mutable data structures.
     * 当并行执行时，可以实例化，填充和合并多个中间结果，以保持可变数据结构的隔离。
     * Therefore, even when executed in parallel with non-thread-safe data structures (such as {@code ArrayList}), no
     * additional synchronization is needed for a parallel reduction.
     * 因此，即使与非线程安全数据结构（例如{@code ArrayList}）并行执行时，也不需要其他同步来进行并行缩减。
     *
     *  {@link java.util.stream.Stream#collect(Collector)}注释 API
     *  (1) The following will accumulate strings into an ArrayList:
     *   以下将字符串累积到ArrayList中：
     *      > List<String> asList = stringStream.collect(Collectors.toList());
     *
     *  (2) The following will classify {@code Person} objects by city:
     *   以下内容将按城市对{@code Person}对象进行分类：
     *      > Map<String, List<Person>> peopleByCity = personStream.collect(Collectors.groupingBy(Person::getCity));
     *
     *  (3)The following will classify {@code Person} objects by state and city,cascading two {@code Collector}s together:
     *   以下内容将按州和城市对{@code Person}对象进行分类，并将两个{@code Collector}层叠在一起：
     *      > Map<String, Map<String, List<Person>>> peopleByStateAndCity
     *                      = personStream.collect(Collectors.groupingBy(Person::getState,Collectors.groupingBy(Person::getCity)));
     */

    /**
     *  Map<String,Integer> map = Stream.of(strings).collect(Collectors.toMap(Function.identity(),String::length));
     *  Function是一个接口，那么Function.identity()是什么意思呢？这要从两方面解释：
     *      a. Java 8允许在接口中加入具体方法。接口中的具体方法有两种，default方法和static方法，identity()就是Function接口的一个静态方法。
     *      b. Function.identity()返回一个输出跟输入一样的Lambda表达式对象，等价于形如t -> t形式的Lambda表达式。
     *
     *  上面的解释是不是让你疑问更多？不要问我为什么接口中可以有具体方法，也不要告诉我你觉得t -> t比identity()方法更直观。
     *  接口中的default方法是一个无奈之举，在Java 7及之前要想在定义好的接口中加入新的抽象方法是很困难甚至不可能的，因为所有实现了该接口的类都要重新实现。
     *  试想在Collection接口中加入一个stream()抽象方法会怎样？
     *  default方法就是用来解决这个尴尬问题的，直接在接口中实现新加入的方法。
     *  既然已经引入了default方法，为何不再加入static方法来避免专门的工具类呢！
     */

    /**
     *  方法引用
     *  诸如String::length的语法形式叫做方法引用（method references），这种语法用来替代某些特定形式Lambda表达式。
     *  如果Lambda表达式的全部内容就是调用一个已有的方法，那么可以用方法引用来替代Lambda表达式。
     *  方法引用可以细分为四类：
     *      a. 引用静态方法 Integer::sum
     *      b. 引用某个对象的方法 list::add
     *      c. 引用某个类的方法 String::length
     *      d. 引用构造方法 HashMap::new
     */

    /**
     *  收集器
     *  将一个Stream转换成一个容器，至少需要两样东西:
     *      1，目标容器是什么？ArrayList还是HashSet，或者是个TreeMap。
     *      2，新元素如何添加到容器中？是List.add()还是Map.put()。
     *      3，如果并行的进行规约，还需要告诉collect() 多个部分结果如何合并成一个。
     *  因此，java中将collect()方法定义 {@link java.util.stream.Stream#collect(Supplier, BiConsumer, BiConsumer)}
     *  但，如果是这样，我们每次调用collect() 方法都传入这三个参数太麻烦了。此时，收集器（Collecttor）就是对这三个参数的简单封装
     *  也因此，collect()方法还提供另外的重载，即{@link java.util.stream.Stream#collect(Collector)}。
     *
     *  Collectors工具类（可以类比为Collection{@link java.util.Collection}接口的工具类Collecttions{@link java.util.Collections}）
     *  该工具类提供一些静态方法，用于生成各种常用的Collector，如栗子中的 Collectors.toList()方法
     *  也可以用 Collectors.toCollection() {@link java.util.stream.Collectors#toCollection(Supplier)} 人为指定容器类型 (HashSet,ArrayList)
     *
     *  使用collect()生成Map
     *  前面已经说过Stream背后依赖于某种数据源，数据源可以是数组、容器等，但不能是Map。反过来从Stream生成Map是可以的，但我们要想清楚Map的key和value分别代表什么，根本原因是我们要想清楚要干什么。
     *  通常在三种情况下collect()的结果会是Map：
     *      a.使用Collectors.toMap()生成的收集器，用户需要指定如何生成Map的key和value。
     *      b.使用Collectors.partitioningBy()生成的收集器，对元素进行二分区操作时用到。
     *      c.使用Collectors.groupingBy()生成的收集器，对元素做group操作时用到。
     *
    */

    public static void collectTest(){
        //  collect方法注释中提供的方法，注意关注 Collectors 这个类
        String [] strings = {"I","love","China","Hebei","Handan"};
        // 如果不使用收集器Collector如何实现？
        List<String> stringList =  Stream.of(strings).collect(ArrayList::new,ArrayList::add,ArrayList::addAll);
        //List<String> stringList =  Stream.of(strings).collect(Collectors.toList());

        // 如果不使用收集器Collector如何实现？
        Set<String> stringSet = Stream.of(strings).collect(HashSet::new,HashSet::add,HashSet::addAll);
        //Set<String> stringSet = Stream.of(strings).collect(Collectors.toSet());
        System.out.println(stringList);
        System.out.println(stringSet);

        // 人为指定容器的实际类型 ArrayList,HashSet
        List<String> stringList1 =  Stream.of(strings).collect(Collectors.toCollection(ArrayList::new));
        System.out.println(stringList1);
        Set<String> stringSet1 =  Stream.of(strings).collect(Collectors.toCollection(HashSet::new));
        System.out.println(stringSet1);

        //String [] strings = {"I","love","China","Hebei","Handan","love"};
        // java.lang.IllegalStateException: Duplicate key 4

        // 如果不使用收集器Collector如何实现？
        //Stream.of(strings).collect(HashMap::new,)
        Map<String,Integer> map = Stream.of(strings).collect(Collectors.toMap(Function.identity(),String::length));
        System.out.println(map.keySet());

        // a. 用Collectors.toMap()生成的收集器，用户需要指定如何生成Map的key和value
        List<CourseGrade> liliGrade = new ArrayList<>();
        liliGrade.add(new CourseGrade("MATH",80.0));
        liliGrade.add(new CourseGrade("ENGLISH",50.5));
        liliGrade.add(new CourseGrade("CHINESE",90.3));
        liliGrade.add(new CourseGrade("PHYSICS",88.2));
        liliGrade.add(new CourseGrade("CHEMISTRY",70.2));
        liliGrade.add(new CourseGrade("BIOLOGY",60.3));
        Student lili = new Student("lili",0,"高二（4）班",liliGrade);

        List<CourseGrade> meiGrade = new ArrayList<>();
        meiGrade.add(new CourseGrade("MATH",55.4));
        meiGrade.add(new CourseGrade("ENGLISH",90.5));
        meiGrade.add(new CourseGrade("CHINESE",45.6));
        meiGrade.add(new CourseGrade("PHYSICS",77.3));
        meiGrade.add(new CourseGrade("CHEMISTRY",66.4));
        meiGrade.add(new CourseGrade("BIOLOGY",65.8));
        Student mei = new Student("mei",0,"高二（4）班",meiGrade);

        List<CourseGrade> leiGrade = new ArrayList<>();
        leiGrade.add(new CourseGrade("MATH",94.3));
        leiGrade.add(new CourseGrade("ENGLISH",98.4));
        leiGrade.add(new CourseGrade("CHINESE",55.7));
        leiGrade.add(new CourseGrade("PHYSICS",61.8));
        leiGrade.add(new CourseGrade("CHEMISTRY",76.9));
        leiGrade.add(new CourseGrade("BIOLOGY",94.2));
        Student lei = new Student("lei",0,"高二（4）班",leiGrade);

        List<CourseGrade> mingGrade = new ArrayList<>();
        mingGrade.add(new CourseGrade("MATH",89.4));
        mingGrade.add(new CourseGrade("ENGLISH",78.2));
        mingGrade.add(new CourseGrade("CHINESE",59.2));
        mingGrade.add(new CourseGrade("PHYSICS",96.0));
        mingGrade.add(new CourseGrade("CHEMISTRY",97.5));
        mingGrade.add(new CourseGrade("BIOLOGY",92.5));
        Student ming = new Student("ming",0,"高二（4）班",mingGrade);

        List<Student> students = new ArrayList<>();
        students.add(lili);
        students.add(mei);
        students.add(lei);
        students.add(ming);
        System.out.println(lili.computeGPA());
        //Map<Student, Double> studentToGPA = students.stream().collect(Collectors.toMap(Function.identity(),Student::computeGPA));
        //System.out.println(studentToGPA);

    }

    public static void main(String[] args) {
        //testReduce();
        collectTest();
        System.out.println(CourseCredit.BIOLOGY.getCredit());
        
    }
}
@FunctionalInterface
interface stu{
    Double computeGPA();
}

class Student {
    private String name;
    private Integer sex;
    private String className;
    private List<CourseGrade> courseGrades;

    public Student(){
        super();
    }

    public Student(String name,Integer sex,String className,List<CourseGrade> courseGrades){
        this.name = name;
        this.sex = sex;
        this.className = className;
        this.courseGrades = courseGrades;
    }

    /**
    * @Description: 计算平均绩点 (绩点*学分+...)/(学分+...)
    * @Param: [] 
    * @return: java.lang.Double 
    * @Author: Johann 
    * @Date: 2020/10/15 
    */ 
    public Double computeGPA(){
        List<CourseGrade> courseGrades = this.getCourseGrades();
        BigDecimal gradeMulCreditSum = new BigDecimal(0);
        BigDecimal creditSum = new BigDecimal(0);
        courseGrades.forEach(courseGrade -> {
            System.out.println(courseGrade.getCourseName()+" : "+courseGrade.getGrade());
            System.out.println(courseGrade.getCourseName()+" : "+CourseCredit.getCredit(courseGrade.getCourseName()));
            System.out.println(gradeMulCreditSum);
            System.out.println(creditSum);
            gradeMulCreditSum.add(new BigDecimal(courseGrade.getGrade())
                    .multiply(new BigDecimal(CourseCredit.getCredit(courseGrade.getCourseName()))));
            creditSum.add(new BigDecimal(CourseCredit.getCredit(courseGrade.getCourseName())));
        });
        System.out.println(gradeMulCreditSum);
        System.out.println(creditSum);
        return gradeMulCreditSum.divide(creditSum,2, RoundingMode.HALF_UP).doubleValue();
    }
    /** 
    * @Description: 获取平均分 
    * @Param: [] 
    * @return: java.lang.Double 
    * @Author: Johann 
    * @Date: 2020/10/15 
    */ 
    public Double computeAverageScore(){
        List<CourseGrade> courseGrades = this.getCourseGrades();
        BigDecimal gradeSum = new BigDecimal(0);
        BigDecimal courseSum = new BigDecimal(0);
        courseGrades.forEach(courseGrade -> {
            gradeSum.add(new BigDecimal(courseGrade.getGrade()));
            courseSum.add(new BigDecimal(1));
        });
        return gradeSum.divide(courseSum,2, RoundingMode.HALF_UP).doubleValue();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public List<CourseGrade> getCourseGrades() {
        return courseGrades;
    }

    public void setCourseGrades(List<CourseGrade> courseGrades) {
        this.courseGrades = courseGrades;
    }

}
/** 
* @Description: 各个科目成绩 
* @Param:  
* @return:  
* @Author: Johann 
* @Date: 2020/10/15 
*/ 
class CourseGrade{
    private String courseName;
    private Double grade;

    public CourseGrade(){
        super();
    }

    public  CourseGrade(String courseName,Double grade){
        this.courseName = courseName;
        this.grade = grade;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public Double getGrade() {
        return grade;
    }

    public void setGrade(Double grade) {
        this.grade = grade;
    }
}

/** 
* @Description: 学分绩点
* @Param:  
* @return:  
* @Author: Johann 
* @Date: 2020/10/15 
*/ 
class GradePoint{
     Integer getGrades(Double grade){
        if(grade >= 90){
            return 6;
        }
        if(grade >= 80){
            return 5;
        }
        if(grade >= 70){
            return 4;
        }
        if(grade >= 60){
            return 3;
        }
        if(grade >= 40){
            return 2;
        }
        if(grade >= 20){
            return 1;
        }
        return 0;
    }
}
/** 
* @Description: 科目学分
* @Param:  
* @return:  
* @Author: Johann 
* @Date: 2020/10/15 
*/ 
enum CourseCredit {
    MATH("MATH",4),
    ENGLISH("ENGLISH",4),
    CHINESE("CHINESE",4),
    PHYSICS("PHYSICS",3),
    CHEMISTRY("CHEMISTRY",3),
    BIOLOGY("BIOLOGY",3);

    private String courseName;
    private Integer credit;

    private CourseCredit(String courseName,Integer credit){
        this.courseName = courseName;
        this.credit = credit;
    }
    /** 
    * @Description: 根据学科获取各科目学分
    * @Param: [courseName] 
    * @return: java.lang.Integer 
    * @Author: Johann 
    * @Date: 2020/10/15 
    */ 
    public static Integer getCredit(String courseName) {
        for (CourseCredit c : CourseCredit.values()) {
            if (c.getCourseName() == courseName) {
                return c.getCredit();
            }
        }
        return 0;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public Integer getCredit() {
        return credit;
    }

    public void setCredit(Integer credit) {
        this.credit = credit;
    }
}


