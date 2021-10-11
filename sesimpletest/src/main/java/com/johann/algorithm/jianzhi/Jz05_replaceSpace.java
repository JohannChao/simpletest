package com.johann.algorithm.jianzhi;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @ClassName: Jz05_replaceSpace
 * @Description: TODO
 * @Author: Johann
 * @Date: 2021-05-08 17:30
 **/
public class Jz05_replaceSpace {

    /**
     * 请实现一个函数，把字符串 s 中的每个空格替换成"%20"。
     *
     *  
     *
     * 示例 1：
     *
     * 输入：s = "We are happy."
     * 输出："We%20are%20happy."
     *  
     *
     * 限制：
     *
     * 0 <= s 的长度 <= 10000
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/ti-huan-kong-ge-lcof
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
    */

    public static String solution(String s){

        // 1，替换不是算法思想
//        if(s==null){
//            return null;
//        }
//        String regex = "\\s";
//        String s1 = s.replaceAll(regex,"%20");
//        System.out.println(s1);

        // 2，字符数组。时间复杂度 O(N),空间复杂度 O(N)
        if(s==null){
            return null;
        }
        StringBuilder sb = new StringBuilder(s.length());
        for(char c : s.toCharArray()){
            if(' '==c){
                sb.append("%20");
            }else{
                sb.append(c);
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) {
//        String str = "12345\t1 s ";
//        System.out.println(str);
//        System.out.println(str.length());
//        String pattern = "\\s";
//
//        Pattern r = Pattern.compile(pattern);
//        Matcher m = r.matcher(str);
//        System.out.println(m.matches());
        String s = "we  1 dsdsdR";
        //String s = "";
        System.out.println(solution(s));
    }
}
