package com.johann.java8;

/**
 * @ClassName: Java8MultiExtends
 * @Description: java8多继承的问题
 * @Author: Johann
 * @Date: 2020-05-25
 **/
public class Java8MultiExtends extends itclass implements Itface{

}

class Example implements  Itface{

    int c = 100;
    public static void main(String[] args) {
        /**
         *  接口中只存在类变量，接口 "itface" 和"itface2"中的a，b，默认的修饰符是 public static final。
         *
         */
        Itface it = new Example();
        System.out.println("it a : " + it.a);
        System.out.println("it b : " + it.b);
        // 该引用只能调用父类中定义的方法和变量
        //System.out.println("it c : " + it.c);
        System.out.println("it doit : " + it.doit());
        System.out.println("it doit : " + it.likeit());

        Example ex = new Example();
        System.out.println("ex a : " +ex.a);//编译错误 ： Reference to 'a' is ambiguous,both 'itface' and 'itface2' match
        System.out.println("ex b : "+ex.b);//编译错误 ： Reference to 'a' is ambiguous,both 'itface' and 'itface2' match
        System.out.println("ex c : " + ex.c);
        System.out.println("ex doit : "+ex.doit());
        System.out.println("ex doit : "+ex.likeit());
    }
}

/**
*  java8 以后，接口允许有默认方法。那么当一个类 实现多个接口时，这些接口中有同名方法，那么这个类会调用哪个接口中的方法？
 *  规则有三：
 *   1，类中的方法优先级最高，类或父类中声明的方法的优先级高于任何声明为默认方法的优先级。
 *   2，如果第一条无法判断，那么子接口的优先级更高：方法签名相同时，优先选择拥有最具体实现的默认方法的接口， 即如果B继承了A，那么B就比A更加具体。
 *   3，如果还是无法判断，继承了多个接口的类必须通过显式覆盖和调用期望的方法， 显式地选择使用哪一个默认方法的实现
*/


/**
 * 下面这个栗子中，类实现了接口Itface，Itface2
 * Itface2继承自Itface，因此Itface2更具体。所以调用的 Itface2接口中的同名方法
*/
class Example1 implements Itface,Itface2{

    public static void main(String[] args) {
        Itface it = new Example1();
        System.out.println("it a : " +it.a);
        System.out.println("it b : "+it.b);
        System.out.println("it doit : "+it.doit());
        // 该引用只能调用父类中定义的方法和变量
        //System.out.println("it sayit : "+it.sayit());
        System.out.println("it likeit : "+it.likeit());

        Itface2 it2 = new Example1();
        System.out.println("it2 a : " +it2.a);
        System.out.println("it2 b : "+it2.b);
        System.out.println("it2 doit : "+it2.doit());
        System.out.println("it2 sayit : "+it2.sayit());
        System.out.println("it2 likeit : "+it2.likeit());

        Example1 ex = new Example1();
        //System.out.println("ex a : " +ex.a);//编译错误 ： Reference to 'a' is ambiguous,both 'itface' and 'itface2' match
        //System.out.println("ex b : "+ex.b);//编译错误 ： Reference to 'a' is ambiguous,both 'itface' and 'itface2' match
        System.out.println("ex doit : "+ex.doit());
        System.out.println("ex doit : "+ex.sayit());
        System.out.println("ex doit : "+ex.likeit());
    }
}

/**
 *  类继承了 itclassNo 类，貌似符合第一条 "类或父类中声明的方法的优先级高于任何声明为默认方法的优先级" 。
 *  但是，这个父类并没有对接口中的默认方法进行覆盖实现，所有编译器还是会选择 Itface2 接口中的默认方法。
*/
class Example2 extends itclassNo implements Itface,Itface2{

    public static void main(String[] args) {
        Itface it = new Example2();
        System.out.println("it a : " +it.a);
        System.out.println("it b : "+it.b);
        System.out.println("it doit : "+it.doit());
        // 该引用只能调用父类中定义的方法和变量
        //System.out.println("it sayit : "+it.sayit());
        System.out.println("it likeit : "+it.likeit());

        Itface2 it2 = new Example2();
        System.out.println("it2 a : " +it2.a);
        System.out.println("it2 b : "+it2.b);
        System.out.println("it2 doit : "+it2.doit());
        System.out.println("it2 sayit : "+it2.sayit());
        System.out.println("it2 likeit : "+it2.likeit());

        Example2 ex = new Example2();
        //System.out.println("ex a : " +ex.a);//编译错误 ： Reference to 'a' is ambiguous,both 'itface' and 'itface2' match
        //System.out.println("ex b : "+ex.b);//编译错误 ： Reference to 'a' is ambiguous,both 'itface' and 'itface2' match
        System.out.println("ex doit : "+ex.doit());
        System.out.println("ex doit : "+ex.sayit());
        System.out.println("ex doit : "+ex.likeit());
    }
}

/**
 * 父类对接口的默认方法，进行覆盖实现，满足第一个条件，故选择父类中的方法。
*/
class Example3 extends itclass implements Itface,Itface2{

    public static void main(String[] args) {
        Itface it = new Example3();
        System.out.println("it a : " +it.a);
        System.out.println("it b : "+it.b);
        System.out.println("it doit : "+it.doit());
        // 该引用只能调用父类中定义的方法和变量
        //System.out.println("it sayit : "+it.sayit());
        System.out.println("it likeit : "+it.likeit());

        Itface2 it2 = new Example3();
        System.out.println("it2 a : " +it2.a);
        System.out.println("it2 b : "+it2.b);
        System.out.println("it2 doit : "+it2.doit());
        System.out.println("it2 sayit : "+it2.sayit());
        System.out.println("it2 likeit : "+it2.likeit());

        Example3 ex = new Example3();
        //System.out.println("ex a : " +ex.a);//编译错误 ： Reference to 'a' is ambiguous,both 'itface' and 'itface2' match
        //System.out.println("ex b : "+ex.b);//编译错误 ： Reference to 'a' is ambiguous,both 'itface' and 'itface2' match
        System.out.println("ex doit : "+ex.doit());
        System.out.println("ex doit : "+ex.sayit());
        System.out.println("ex doit : "+ex.likeit());
    }
}


/**
 *  如果还是无法判断，继承了多个接口的类必须通过显式覆盖和调用期望的方法， 显式地选择使用哪一个默认方法的实现。
*/
class Example4 implements Itface,ItfaceCopy{

    // 如果没有下面这个方法，则类报错
    // Example4 inherits unrelated defaults for doit() from types Itface and ItfaceCopy
    public String doit(){
        return ItfaceCopy.super.doit();
    }

    public static void main(String[] args) {
        Itface it = new Example4();
        System.out.println("it a : " +it.a);
        System.out.println("it b : "+it.b);
        System.out.println("it doit : "+it.doit());
        System.out.println("it likeit : "+it.likeit());

        ItfaceCopy itcopy = new Example4();
        System.out.println("itcopy a : " +itcopy.a);
        System.out.println("itcopy b : "+itcopy.b);
        System.out.println("itcopy doit : "+itcopy.doit());

        Example4 ex = new Example4();
        System.out.println("ex doit : "+ex.doit());
        System.out.println("ex doit : "+ex.likeit());
    }
}

/**
 *   Itface3，Itface4 继承了  Itface ,但是这两个接口都没有对 父接口进行默认实现。故，还是选择父接口的默认方法。
*/
class Example5 implements Itface3,Itface4{

    public static void main(String[] args) {
        Itface3 it3 = new Example5();
        System.out.println("it3 a : " +it3.a);
        System.out.println("it3 b : "+it3.b);
        System.out.println("it3 doit : "+it3.doit());
        System.out.println("it3 likeit : "+it3.likeit());

        Itface4 it4 = new Example5();
        System.out.println("it4 a : " +it4.a);
        System.out.println("it4 b : "+it4.b);
        System.out.println("it4 doit : "+it4.doit());
        System.out.println("it4 likeit : "+it4.likeit());

        Example5 ex = new Example5();
        System.out.println("ex doit : "+ex.doit());
        System.out.println("ex doit : "+ex.likeit());
    }
}

/**
 *   Itface2，Itface3 继承了Itface ,Itface2 对父接口中的默认方法进行了默认覆盖。故选用 Itface2 中的方法
*/
class Example6 implements Itface2,Itface3{

    public static void main(String[] args) {
        Itface2 it2 = new Example6();
        System.out.println("it2 a : " +it2.a);
        System.out.println("it2 b : "+it2.b);
        System.out.println("it2 doit : "+it2.doit());
        System.out.println("it2 sayit : "+it2.sayit());
        System.out.println("it2 likeit : "+it2.likeit());

        Itface3 it3 = new Example6();
        System.out.println("it3 a : " +it3.a);
        System.out.println("it3 b : "+it3.b);
        System.out.println("it3 doit : "+it3.doit());
        System.out.println("it3 likeit : "+it3.likeit());

        Example6 ex = new Example6();
        System.out.println("ex doit : "+ex.doit());
        System.out.println("ex doit : "+ex.likeit());
    }
}

/**
*   结合  Example4 和 Example5
*/
class Example7 implements Itface2,Itface2Copy{

    public String doit(){
        return Itface2Copy.super.doit();
    }
    public String sayit(){
        return Itface2Copy.super.doit();
    }

    public static void main(String[] args) {
        Itface2 it2 = new Example7();
        System.out.println("it2 a : " +it2.a);
        System.out.println("it2 b : "+it2.b);
        System.out.println("it2 doit : "+it2.doit());
        System.out.println("it2 sayit : "+it2.sayit());
        System.out.println("it2 likeit : "+it2.likeit());

        Itface2Copy it2Copy = new Example7();
        System.out.println("it2Copy a : " +it2Copy.a);
        System.out.println("it2Copy b : "+it2Copy.b);
        System.out.println("it2Copy doit : "+it2Copy.doit());
        System.out.println("it2Copy likeit : "+it2Copy.likeit());

        Example7 ex = new Example7();
        System.out.println("ex doit : "+ex.doit());
        System.out.println("ex doit : "+ex.likeit());
    }
}


interface Itface{
    int a = 12;
    int b = 20;
    default String doit(){
        return "itface";
    };
    default String likeit(){
        return "likeitface";
    };
}

interface ItfaceCopy{
    int a = 121;
    int b = 201;
    default String doit(){
        return "ItfaceCopy";
    };
}

interface Itface2 extends Itface{
    int a = 14;
    int b = 22;
    default String doit(){
        return "doitface2";
    };
    default String sayit(){
        return "sayitface2";
    };
}

interface Itface2Copy extends Itface{
    int a = 14;
    int b = 22;
    default String doit(){
        return "doItface2Copy";
    };
    default String sayit(){
        return "sayItface2Copy";
    };
}


interface Itface3 extends Itface{

}

interface Itface4 extends Itface{

}


class itclassNo implements Itface{
    int a = 13;
    static int b = 21;
}

class itclass implements Itface{
    int a = 13;
    static int b = 21;

    @Override
    public String doit() {
        return "itclass";
    }
}



