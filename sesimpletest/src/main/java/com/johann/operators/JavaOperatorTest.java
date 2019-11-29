package main.java.com.johann.operators;

/**
 * @ClassName JavaOperatorTest
 * @Description TODO
 * @Author Johann
 * @Date 2019-11-29 14:00
 **/
public class JavaOperatorTest {
    public static void main(String[] args) {
        /*********************** 算术运算符 ********************************/
//        //除法和取余运算
//        int a = -7;
//        int b = 4;
//        int c = a/b; // c1 = -1
//        int r = a%b; // r1 = -3
//        System.out.println(c);
//        System.out.println(r);
//
//        //取模运算的商和结果
//        int c1 = Math.floorDiv(a,b); // c2 = -2
//        int r1 = Math.floorMod(a,b); // r2 = 1
//        System.out.println(c1);
//        System.out.println(r1);
//
//        // ++ --
//        int m1 = 10;
//        int m2 = 10;
//        int n = m1++; //  m1 = 11 ; n = 10 先赋值，再自增
//        //int n = ++m1; // m1 = 11 ; n = 11 先自增，再赋值
//        System.out.println(m1);
//        System.out.println(n);
//        int l = m2--; // m2 = 9 ; l = 10 先赋值，再自减
//        //int l = --m2; // m2 = 9 ; l = 9 先自减，再赋值
//        System.out.println(m2);
//        System.out.println(l);
        /*********************** 算术运算符 ********************************/

        /*********************** 位运算符 ********************************/
        int m = 50;
        int n = 15;
        String mBin = Integer.toBinaryString(m);// 0011 0010
        String nBin = Integer.toBinaryString(n);// 0000 1111
        System.out.println(mBin);
        System.out.println(nBin);


        /*********************** 位运算符 ********************************/

    }
}
