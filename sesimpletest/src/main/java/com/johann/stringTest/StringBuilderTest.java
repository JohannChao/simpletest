package main.java.com.johann.stringTest;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * @ClassName StringBuilderTest
 * @Description TODO
 * @Author Johann
 * @Date 2019-11-20 10:31
 **/
public class StringBuilderTest {
    public static void main(String[] args) {
        StringBuilder sb = new StringBuilder();
//        System.out.println("capacity : "+sb.capacity());
//        System.out.println("length : "+sb.length());
//        System.out.println("toString : "+sb.toString());
//
//        System.out.println("================================");
//
//        StringBuilder sb1 = new StringBuilder(1000);
//        System.out.println("capacity : "+sb1.capacity());
//        System.out.println("length : "+sb1.length());
//        System.out.println("toString : "+sb1.toString());
//
//        System.out.println("================================");
//
//        StringBuilder sb2 = new StringBuilder("Java");
//        System.out.println("capacity : "+sb2.capacity());
//        System.out.println("length : "+sb2.length());
//        System.out.println("toString : "+sb2.toString());
//
//        System.out.println("================================");
//
//        CharSequence seq = new StringBuilder("String");
//        System.out.println(seq.chars());
//        StringBuilder sb3 = new StringBuilder(seq);
//        System.out.println("capacity : "+sb3.capacity());
//        System.out.println("length : "+sb3.length());
//        System.out.println("toString : "+sb3.toString());
//
//        System.out.println("===============================");
//
//        StringBuilder sb = new StringBuilder();

        sb.append("123456789012345");
        sb.append(" ");
        System.out.println("capacity : "+sb.capacity());
        System.out.println("length : "+sb.length());
        sb.append("7");
        System.out.println("capacity : "+sb.capacity());
        System.out.println("length : "+sb.length());
        System.out.println("toString : "+sb.toString());

        /**
         * 如果当前底层char数组的长度大于当前字符串的长度，调整char数组的长度为当前字符串长度
         **/
        sb.trimToSize();
        System.out.println("capacity : "+sb.capacity());
        System.out.println("length : "+sb.length());
//        StringBuilder sbb2 = new StringBuilder("jav");
//        sbb2.append("1234567890123456");
//        System.out.println(sbb2.capacity());
//        sbb2.append("7");
//        System.out.println(sbb2.capacity());


//        sb.append(1);//int
//        sb.append(" ");
//        sb.append(222l);//long
//        sb.append(" ");
//        sb.append(2.2f);//float
//        sb.append(" ");
//        sb.append(3.333d);//double
//        sb.append(" ");
//        sb.append(false);//boolean 添加布尔类型的数据，实际取值为 "true"或"false"字符串，而不是"0"或"1"
//        sb.append(" ");
//        sb.append('c');//char
//        sb.append(" ");
//        sb.append("JAVA");//String
//        sb.append(" ");
//        CharSequence seq = new StringBuilder("String");
//        sb.append(seq,1,3);// append(CharSequence,开始位置[包含],结束位置[不包含])
//        sb.append(" ");
//        String s = null;// 参数值是null时，null会转换为字符串 “null”
//        sb.append(s);
//        char [] chars = {'j','a','v','a','m','e'};
//        sb.append(chars,2,3);// append(CharsArray,开始位置[包含],往后截取的长度)
//        System.out.println(sb.toString());


//        /**
//         * insert 方法中，第一个参数 offset的取值范围为 0<= offset <= StringBuilder.length()
//         *
//         * The {@code offset} argument must be greater than or equal to
//         * {@code 0}, and less than or equal to the {@linkplain #length() length}
//         * of this sequence.
//         *
//         **/
//        sb.insert(0,10); // int
//        //System.out.println("length : "+sb.length());
//        sb.insert(2,'A'); // char
//        //System.out.println("length : "+sb.length());
//        sb.insert(3,223l); // long
//        //System.out.println("length : "+sb.length());
//        sb.insert(6,2.3f); // float
//        //System.out.println("length : "+sb.length());
//        sb.insert(9,2.222); // double
//        //System.out.println("length : "+sb.length());
//        sb.insert(14,false); // boolean 插入布尔类型的数据，实际取值为 "true"或"false"字符串，而不是"0"或"1"
//        //System.out.println("length : "+sb.length());
//        System.out.println(sb.toString());
//
//        // 当 offset 参数的值不等于当前字符串的长度时，可以看到此时会在指定的 offset 位置，插入新的值，
//        // 原来的 offset 之后的字符会紧跟在新的值后面。而不会被覆盖
//        sb.insert(17,true);
//        //System.out.println("length : "+sb.length());
//        System.out.println(sb.toString());
//
//        char [] chars = new char[]{'c','h','a','r','s'};
//        sb.insert(23,chars); // char[] 字符数组
//        //System.out.println("length : "+sb.length());
//        System.out.println(sb.toString());
//        sb.insert(28,"  "); // 插入字符串，空字符串有几个空格，就占几个长度
//        //System.out.println("length : "+sb.length());
//        System.out.println(sb.toString());
//        sb.insert(30,"11");
//        //System.out.println("length : "+sb.length());
//        System.out.println(sb.toString());
//
//        CharSequence seq = new StringBuilder("String");
//        sb.insert(32,seq);
//        //System.out.println("length : "+sb.length());
//        System.out.println(sb.toString());
//
//        sb.insert(1,chars,2,3);// 插入char数组的子数组。 insert(在哪个位置插入，char数组，从char数组的哪个位置截取[包含]，截取的子数组长度)
//        //System.out.println("length : "+sb.length());
//        System.out.println(sb.toString());
//
//        sb.insert(0,seq,0,3);// insert(在哪个位置插入，CharSequence，截取字符序列的开始位置[包含]，结束位置[不包含])
//        //System.out.println("length : "+sb.length());
//        System.out.println(sb.toString());

//        sb.append("String123456");
//        StringBuilder sb1 = sb.delete(0,3); //删除指定区间位置的元素。delete(开始位置[包含]，结束位置[不包含])
//        System.out.println("sb : "+sb.toString());
//        System.out.println("sb1 : "+sb1.toString());
//        sb.deleteCharAt(1); //删除指定位置的元素
//        System.out.println("sb : "+sb.toString());
//        System.out.println("sb1 : "+sb1.toString());// b和sb1 的引用，指向的是堆中的同一个对象

//        sb.append("String123456");
//        sb.replace(0,5,"1234"); //将指定区间位置的元素，替换为另外的字符串，区间范围不一定要等于待替换的字符串长度。replace(开始位置[包含]，结束位置[不包含]，带替换字符串)
//        System.out.println("sb : "+sb.toString());

//        sb.append("String123456");
//        System.out.println("sb : "+sb.toString());
//        sb.reverse(); // 字符串反转
//        System.out.println("sb : "+sb.toString());
    }
}
