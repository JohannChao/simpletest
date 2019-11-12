package main.java.com.johann.stringTest;

/**
 * @ClassName StringTest
 * @Description TODO
 * @Author Johann
 * @Date 2019-10-31 18:09
 **/
public class StringTest {

    public static void main(String[] args) {


        //在堆中创建一个 “hello”,在全局字符串常量池[StringTable]中没有"hello"
        String s3 = new String("hel") + new String("lo");

        //在字符串池中创建一个 “hello”
        String s4 = "hello";

        //判断字符串池中是否存在“hello”,如果存在，直接返回字符串池中的对象；如果字符串池中的不存在，则把堆中的字符串对象引用添加到字符串池中
        String s5 = s3.intern();
        System.out.println("s5 == s3 : "+(s5 == s3));
        System.out.println("s5 == s4 : "+(s5 == s4));
        System.out.println("s3 == s4 : "+(s3 == s4));

        System.out.println("======================");

        //在堆中新建一个“good”
        String s6 = new String("go") +new String("od");

        //判断字符串池中是否存在“good”,如果存在，直接返回字符串池中的对象；如果字符串池中的不存在，则把堆中的字符串对象引用添加到字符串池中（注意不是copy一份，而是move）
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

        System.out.println("=================================");


        String s15 = new String("lo"+"ve");//这一句我们只知道在堆中创建了"love"对象,并不知道在 StringTable中是否放入一个"love"
        //s15.intern();//如果上一句执行完，如果在StringTable中放入了"love"，那么下面的执行结果就是false；如果在StringTable中没有放入了"love"，执行该句，会把堆中“love”对象的引用放入到StringTable中，即下面的结果是 true
        //String s16 = "love";
        String s16 = s15.intern();;
        System.out.println("s15 == s16 : "+(s15==s16));// fasle 说明执行 new String("lo"+"ve"); 时，在StringTable中已经放入了“love”

        System.out.println("=================================");

        String s17 = new String("mi"+"ne");
        //String s17 = new String("mi");

        String s18 = new String("m")+new String("i");//只会在堆中创建"mi",在StringTable不放入“mi”
        //String s18 = new StringBuilder().append("m").append("i").toString();
        System.out.println("s17 == s18 : "+(s18.intern()==s18));

        System.out.println("==================================");
        String s19 = "live";
        String s20 = s19.concat("");
        System.out.println("s19==s20 : "+(s19==s20));

        String s21 = new String("chi");
        String s22 = s21.concat("");
        System.out.println("s21==s22 : "+(s21==s22));

        System.out.println("=======================================");
        String a = "a";
        String param = new String("param" + a);
        String paramSame = param.intern();
        System.out.println(param == paramSame);

    }
}
