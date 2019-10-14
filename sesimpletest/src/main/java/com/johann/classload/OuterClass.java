package main.java.com.johann.classload;

/**
 * @ClassName OuterClass
 * @Description 类加载过程中的执行顺序
 * @Author Johann
 * @Date 2019-07-20 11:38
 **/
public class OuterClass {

    //添加静态变量
    static final OuterClass outer = new OuterClass();

    //饿汉式单例模式
    public static OuterClass getInstance(){
        return outer;
    }

    public OuterClass(){
        System.out.println("外部类的构造函数=====");
    }

    static {
        System.out.println("外部类中的静态块=====");
    }

    {
        System.out.println("外部类中的代码块=====");
    }

    /**
     * @Author Johann
     * @Description 静态内部类
     * @Date 14:40 2019-07-20
     * @Param
     * @return
     **/
    static class StaticInnerClass{

        public StaticInnerClass() {
            System.out.println("静态内部类的构造函数。。。");
        }

        static {
            System.out.println("静态内部类中的静态块。。。");
        }

        {
            System.out.println("静态内部类中的代码块。。。");
        }

        static void innerStaticMethod() {
            System.out.println("静态内部类中的静态方法。。。");
        }

        void innerMethod(){
            System.out.println("静态内部类中的方法。。。");
        }

    }

    /**
     * @Author Johann
     * @Description 非静态内部类
     * @Date 14:48 2019-07-20
     * @Param
     * @return
     **/
     class InnerClass{

        public InnerClass() {
            System.out.println("非静态内部类的构造函数 #####");
        }

        {
            System.out.println("非静态内部类中的代码块 #####");
        }

        void innerMethod(){
            System.out.println("非静态内部类中的方法 #####");
        }

    }

    public static void main(String[] args) {
        //OuterClass out = new OuterClass();
        //OuterClass out=OuterClass.getInstance();

        //静态内部类实例化格式： OutClass.StaticInner staticInner = new OutClass.StaticInner();
        OuterClass.StaticInnerClass staticInnerClass = new OuterClass.StaticInnerClass();
        staticInnerClass.innerMethod();
        OuterClass.StaticInnerClass.innerStaticMethod();


        //非静态内部类实例化格式： OutClass.InnerClass innerClass = outClassInstance.new InnerClass();
//        OuterClass.InnerClass innerClass = out.new InnerClass();
//        innerClass.innerMethod();

        System.out.println("加载完成");
    }
}
