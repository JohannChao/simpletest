package main.java.com.johann.stringTest;

/**
 * @ClassName StringTest
 * @Description TODO
 * @Author Johann
 * @Date 2019-10-31 18:09
 **/
public class StringTest {

    public static void main(String[] args) {


        //在堆中创建一个 “hello”
        String s3 = new String("hel") + new String("lo");

        //在字符串池中创建一个 “hello”
        String s4 = "hello";

        //判断字符串池中是否存在“hello”,如果存在，直接返回字符串池中的对象；如果字符串池中的不存在，则把堆中的字符串对象添加到字符串池中
        String s5 = s3.intern();
        System.out.println("s5 == s3 : "+(s5 == s3));
        System.out.println("s5 == s4 : "+(s5 == s4));
        System.out.println("s3 == s4 : "+(s3 == s4));

        System.out.println("======================");

        //在堆中新建一个“good”
        String s6 = new String("go") +new String("od");

        //判断字符串池中是否存在“good”,如果存在，直接返回字符串池中的对象；如果字符串池中的不存在，则把堆中的字符串对象添加到字符串池中（注意不是copy一份，而是move）
        String s7 = s6.intern();

        //判断字符串池中是否存在“good”，若不存在则新建一个“good”；若存在，直接返回这个“good”对象
        String s8 = "good";
        System.out.println("s6 == s7 : "+(s6 == s7));
        System.out.println("s7 == s8 : "+(s7 == s8));
        System.out.println("s6 == s8 : "+(s6 == s8));

        System.out.println("=================================");

        String s11=new String("111")+new String("11");
        String s12=new String("1")+new String("1111");
        String s13=s11.intern();
        String s14=s12.intern();
        System.out.println(s11==s13);
        System.out.println(s11==s14);
    }
}
