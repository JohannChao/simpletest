package com.johann.stringTest;

import java.util.StringJoiner;

/**
 * @ClassName StringTest
 * @Description TODO
 * @Author Johann
 * @Date 2019-10-31 18:09
 **/
public class StringTest {

    public static void main(String[] args) {


//        //在堆中创建一个 “hello”,在全局字符串常量池[StringTable]中没有"hello"
//        String s3 = new String("hel") + new String("lo");
//
//        //在字符串池中创建一个 “hello”
//        String s4 = "hello";
//
//        //判断字符串池中是否存在“hello”,如果存在，直接返回字符串池中的对象；如果字符串池中的不存在，则把堆中的字符串对象引用添加到字符串池中
//        String s5 = s3.intern();
//        System.out.println("s5 == s3 : "+(s5 == s3));
//        System.out.println("s5 == s4 : "+(s5 == s4));
//        System.out.println("s3 == s4 : "+(s3 == s4));
//
//        System.out.println("======================");
//
//        //在堆中新建一个“good”
//        String s6 = new String("go") +new String("od");
//
//        //判断字符串池中是否存在“good”,如果存在，直接返回字符串池中的对象；如果字符串池中的不存在，则把堆中的字符串对象引用添加到字符串池中（注意不是copy一份，而是move）
//        String s7 = s6.intern();
//
//        //判断字符串池中是否存在“good”，若不存在则新建一个“good”；若存在，直接返回这个“good”对象
//        String s8 = "good";
//        System.out.println("s6 == s7 : "+(s6 == s7));
//        System.out.println("s7 == s8 : "+(s7 == s8));
//        System.out.println("s6 == s8 : "+(s6 == s8));
//
//        System.out.println("=================================");
//
//        String s11=new String("111")+new String("11");
//        String s12=new String("1")+new String("1111");
//        String s13=s11.intern();
//        String s14=s12.intern();
//        System.out.println(s11==s13);
//        System.out.println(s11==s14);
//
//        System.out.println("=================================");
//
//
//        String s15 = new String("lo"+"ve");//这一句我们只知道在堆中创建了"love"对象,并不知道在 StringTable中是否放入一个"love"
//        //s15.intern();//如果上一句执行完，如果在StringTable中放入了"love"，那么下面的执行结果就是false；如果在StringTable中没有放入了"love"，执行该句，会把堆中“love”对象的引用放入到StringTable中，即下面的结果是 true
//        //String s16 = "love";
//        String s16 = s15.intern();;
//        System.out.println("s15 == s16 : "+(s15==s16));// fasle 说明执行 new String("lo"+"ve"); 时，在StringTable中已经放入了“love”
//
//        System.out.println("=================================");
//
//        String s17 = new String("mi"+"ne");
//        //String s17 = new String("mi");
//
//        String s18 = new String("m")+new String("i");//只会在堆中创建"mi",在StringTable不放入“mi”
//        //String s18 = new StringBuilder().append("m").append("i").toString();
//        System.out.println("s17 == s18 : "+(s18.intern()==s18));
//
//        System.out.println("==================================");
//        String s19 = "live";
//        String s20 = s19.concat("");
//        System.out.println("s19==s20 : "+(s19==s20));
//
//        String s21 = new String("chi");
//        String s22 = s21.concat("");
//        System.out.println("s21==s22 : "+(s21==s22));
//
//        System.out.println("=======================================");
//        String a = "a";
//        String param = new String("param" + a);
//        String paramSame = param.intern();
//        System.out.println(param == paramSame);

//        String s001 = "zhong";
//        String s002 = s001.concat("guo");
//        //s002.intern();
//        System.out.println(s001);
//        System.out.println(s002);
//        String s003 = "zhongguo";
//        System.out.println(s002==s003);


        //[深入理解JAVA虚拟机第二版] 57页的坑
        // 引用： https://www.cnblogs.com/thisiswhy/p/12317742.html
        String str1 = new StringBuilder("计算机").append("软件").toString();
        //String str1 = new String("计算机软件");
        String str1intern = str1.intern();
        //System.out.println("str1 地址 ："+System.identityHashCode(str1)+" str1intern 地址 ： "+System.identityHashCode(str1intern));
        //System.out.println("str1 hashcode : "+str1.hashCode()+" str1intern hashcode : "+str1intern.hashCode());
        //System.out.println(str1intern.equals(str1));
        System.out.println(str1intern == str1);// 1.8 true  1.7 true  1.6 false

        String str5 = new String("你好") + new String("Johann");
        //String str5 = "你好" + "Johann";//在运行中常量池中存入字符串“你好Johann”
        //System.out.println(str5.intern() == str5);//

        String str5copy = new String("你好" + "Johann");
        System.out.println(str5copy.intern() == str5);//


        //String str3 = new StringBuilder("hello").append("word").toString();
        //String str3 = new String("helloword");
        char [] chars = {'h','e','l','l','o','w','o','r','d'};
        String str3 = new String(chars);
        String str3intern = str3.intern();
        //System.out.println("str3 地址 ："+System.identityHashCode(str3)+" str3intern 地址 ： "+System.identityHashCode(str1intern));
        //System.out.println("str3 hashcode : "+str3.hashCode()+" str3intern hashcode : "+str3intern.hashCode());
        //System.out.println(str3intern.equals(str3));
        System.out.println(str3intern == str3);// 1.8 true  1.7 true  1.6 false

        String str2 = new StringBuilder("ja").append("va").toString();
        String str2intern = str2.intern();
        //System.out.println("str2 hashcode : "+str2.hashCode()+" str2intern hashcode : "+str2intern.hashCode());
        //System.out.println(str2intern.equals(str2));
        System.out.println(str2intern == str2);// 1.8 false  1.7 false  1.6 false

//        String str4 = new StringBuilder("vo").append("id").toString();
//        String str4intern = str4.intern();
//        System.out.println("str4 hashcode : "+str4.hashCode()+" str4intern hashcode : "+str4intern.hashCode());
//        System.out.println(str4intern.equals(str4));
//        System.out.println(str4intern == str4);// 1.8 false  1.7 false  1.6 false

        StringJoiner sj = new StringJoiner("*","#","。");
        String sjStr = sj.toString();
        System.out.println("sjStr : "+sjStr);
        sj.add("我");
        sj.add("和");
        sj.add("你");
        System.out.println(sj.toString());
    }
}
