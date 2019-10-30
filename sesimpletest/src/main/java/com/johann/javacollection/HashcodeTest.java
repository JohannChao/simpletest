package main.java.com.johann.javacollection;

import java.math.BigDecimal;
import java.math.BigInteger;

/**
 * @ClassName HashcodeTest
 * @Description TODO
 * @Author Johann
 * @Date 2019-10-29 14:46
 **/
public class HashcodeTest {

    public static void main(String[] args) {
        int a = 2147483647;
        int b = a+a;
        System.out.println(b);

        String str1 = new String("11");

        char[] str1char = str1.toCharArray();

        //s[0]*31^(n-1) + s[1]*31^(n-2) + ... + s[n-1]
        int h = 0;
        for (char c : str1char){
            System.out.println("c : "+(int)c);
            h = 31 * h + c;
        }
        System.out.println(h & Integer.MAX_VALUE);
        System.out.println(str1.hashCode()& Integer.MAX_VALUE);
//        System.out.println(simulationHashcode(31,str1char));
        whyIs31(31);

        int hhh = (11>>>16)^11;
        int index = hhh&15;
        System.out.println("hhh : "+hhh);
        System.out.println("index ： "+index);
    }

    /**
     * @Author Johann
     * @Description 模拟String中的hashcode重写方法
     * @Date 15:43 2019-10-29
     * @Param [radix, hash]
     * @return int
     **/
    public static int simulationHashcode(int radix,char [] hash){
        int h = 0;
        int length = hash.length;
        BigInteger radixInteger = new BigInteger(String.valueOf(radix));
        for (int i = 0;i < length;i++){
            BigInteger inx =radixInteger.pow(length-1-i);
            int result = Integer.valueOf(inx.toString())*hash[i];
            h += result;
        }
        return h;
    }

    /**
     * @Author Johann
     * @Description 为什么String重写hashcode方法的时候，使用 31
     * @Date 17:23 2019-10-29
     * @Param [n]
     * @return void
     **/
    public static void whyIs31(int n){
        int result = 31*n;
        System.out.println("result : "+result);
        int result1 = (n<<5)-n;
        System.out.println("result1 : "+result1);
    }
}
