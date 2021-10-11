package com.johann.classload;

/**
 * @ClassName: JavaSuperTest
 * @Description: Java中在什么情况下，子类需要在自己的构造函数中添加 super()
 *  如果父类中没有声明任何构造器【默认空构造器】，或者父类中声明了有参构造器，且又主动声明了无参构造器，此时子类的构造器中不加 super()或super(...) 不会报错，
 *  但其实已经默认引用了 父类的空构造器。虽然不会报错，但有些属性可能不会被初始化赋值。
 *
 * @Author: Johann
 * @Date: 2020-09-18 15:10
 **/
public class JavaSuperTest {

    public static void main(String[] args) {
//        Eagle e = new Eagle("能飞","老鹰","大型猛禽");
//        System.out.println(e.getFly()+" - "+e.getName()+" - "+e.getBody());

        Eagle e = new Eagle("能飞");
        System.out.println(e.getFly()); //子类没有加super(fly),默认引用父类的空构造器，结果为null，fly属性未初始化赋值
    }
}

/**
* @Description: 父类
* @Param:
* @return:
* @Author: Johann
* @Date: 2020/9/18
*/
class Bird{
    private String fly;

    public String getFly() {
        return fly;
    }

    public void setFly(String fly) {
        this.fly = fly;
    }

    /**
    *   若没有声明构造器，则默认会生成该构造器。
     *  如果声明了有参构造器，如果不声明空构造器，则有参构造器会覆盖默认的空构造器。
    */
    public Bird(){

    }

    /**
    *   如果不加这个构造函数,则下方 public Eagle(String fly){} 构造函数不加super不报错。默认调用的是 父类Bird 中的无参构造器
     *  加上这个构造函数时，如果下方构造器不加 super，则报错
     *
     *  Error:(61, 29) java: 无法将类 main.java.com.johann.classload.Bird中的构造器 Bird应用到给定类型;
     *   需要: java.lang.String
     *   找到: 没有参数
     *   原因: 实际参数列表和形式参数列表长度不同
    */
    public Bird(String fly){
        this.fly = fly;
    }
}

class Eagle extends Bird{
    private String body;

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Eagle(){

    }

    public Eagle(String fly){

    }

//    public Eagle(String fly){
//        super(fly);
//    }

    public Eagle(String fly, String body){
        //super(); 如果不写，其实此处默认调用了父类的无参构造器。所以，如果父类的无参构造器被覆盖掉，则此处报错
        //super(fly); 当然你也可以主动调用父类的有参构造器
        this.body = body;
    }
}