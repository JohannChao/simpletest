package com.johann.operators;

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
//        int m = 50;
//        int n = 15;
//        String mBin = Integer.toBinaryString(m);// 0011 0010
//        String nBin = Integer.toBinaryString(n);// 0000 1111
//        System.out.println(m+"； 二进制："+mBin);
//        System.out.println(n+"； 二进制："+nBin);
//        System.out.println("##############################");
//
//        //按位与
//        int yu = m&n;
//        System.out.println(m+"&"+n+" 按位与："+yu);// 2
//        System.out.println(m+"&"+n+" 按位与二进制："+Integer.toBinaryString(yu));// 10
//        System.out.println("##############################");
//
//        //按位或
//        int huo = m|n;
//        System.out.println(m+"|"+n+" 按位或："+huo);// 63
//        System.out.println(m+"|"+n+" 按位或二进制："+Integer.toBinaryString(huo)); // 111111
//        System.out.println("##############################");
//
//        //按位异或
//        int yihuo = m^n;
//        System.out.println(m+"^"+n+" 按位异或："+yihuo);// 61
//        System.out.println(m+"^"+n+" 按位异或二进制："+Integer.toBinaryString(yihuo));// 111101
//        System.out.println("##############################");
//
//        //按位取反
//        int mfan = ~m;
//        int nfan = ~n;
//        System.out.println(m+" 按位取反："+mfan);// -51
//        System.out.println(m+" 按位取反二进制："+Integer.toBinaryString(mfan));// 11111111111111111111111111001101
//        System.out.println(n+" 按位取反："+nfan);// -16
//        System.out.println(n+" 按位取反二进制："+Integer.toBinaryString(nfan));//11111111111111111111111111110000
//        System.out.println("##############################");
//
//        //按位左移
//        int mleft = m<<2;
//        int nleft = n<<3;
//        System.out.println(m+"<<2"+" 按位左移："+mleft);// 20  0
//        System.out.println(m+"<<2"+" 按位左移二进制："+Integer.toBinaryString(mleft));// 11001000
//        System.out.println(n+"<<3"+" 按位左移："+nleft);// 120
//        System.out.println(n+"<<3"+" 按位左移二进制："+Integer.toBinaryString(nleft));// 1111000
//        System.out.println("##############################");
//
//        //按位右移
//        int mright = m>>3;
//        int nright = n>>2;
//        System.out.println(m+">>3"+" 按位右移："+mright);// 6
//        System.out.println(m+">>3"+" 按位右移二进制："+Integer.toBinaryString(mright));// 110
//        System.out.println(n+">>2"+" 按位右移："+nright);// 3
//        System.out.println(n+">>2"+" 按位右移二进制："+Integer.toBinaryString(nright));// 11

        /*********************** 位运算符 ********************************/

        /*********************** 逻辑运算符 ********************************/
//        //逻辑与
//        int m = 100;
//        boolean yu1 = (false)&&(m++>=101);//第一个条件为false，第二个条件不执行
//        System.out.println("逻辑与："+yu1);// false
//        System.out.println("m = "+m);// 没有执行第二个条件，m仍为 100
//        boolean yu2 = (true)&&(m++>=101);
//        System.out.println("逻辑与："+yu2);// false
//        System.out.println("m = "+m);// 第二个条件执行，由于m++是先用未自增前的m作比较，即100>=101，得出结果为false；然后m再自增，所以最终结果为false， m = 101
//
//        //逻辑或
//        int n = 100;
//        boolean huo1 = (true)||(n++>=101);//第一个条件为true，第二个条件不执行
//        System.out.println("逻辑或："+huo1);// true
//        System.out.println("n = "+n);// 没有执行第二个条件，m仍为 100
//        boolean huo2 = (false)||(n++<=100);
//        System.out.println("逻辑或："+huo2);// true
//        System.out.println("n = "+n);// 第二个条件执行，由于m++是先用未自增前的m作比较，即100<=100，得出结果为ftrue；然后m再自增，所以最终结果为true， m = 101
//
//        //逻辑非
//        boolean fei1 = !(true);// false
//        boolean fei2 = !(false);// true
//        System.out.println("fei1 : "+fei1+" --- fei2 : "+fei2);
        /*********************** 逻辑运算符 ********************************/

        /*********************** 赋值运算符 ********************************/
        int m = 50;
        int n = 15;
        int oldm = 0;

        oldm = m;
        m += n;
        System.out.println(oldm+" += "+n+" -----> "+m);

        oldm = m;
        m -= n;
        System.out.println(oldm+" -= "+n+" -----> "+m);

        oldm = m;
        m *= n;
        System.out.println(oldm+" *= "+n+" -----> "+m);

        oldm = m;
        m /= n;
        System.out.println(oldm+" /= "+n+" -----> "+m);

        oldm = m;
        m %= n;
        System.out.println(oldm+" %= "+n+" -----> "+m);

        oldm = m;
        m <<= n;
        System.out.println(oldm+" <<= "+n+" -----> "+m);

        oldm = m;
        m >>= n;
        System.out.println(oldm+" >>= "+n+" -----> "+m);

        System.out.println(m+"； 二进制："+Integer.toBinaryString(m));
        System.out.println(n+"； 二进制："+Integer.toBinaryString(n));

        System.out.println("///////////////////////////////////////////////");
        oldm = m;
        m &= n;
        System.out.println(oldm+" &= "+n+" -----> "+m);
        System.out.println(m+"； 二进制："+Integer.toBinaryString(m));
        System.out.println(n+"； 二进制："+Integer.toBinaryString(n));

        System.out.println("///////////////////////////////////////////////");
        oldm = m;
        m |= n;
        System.out.println(oldm+" |= "+n+" -----> "+m);
        System.out.println(m+"； 二进制："+Integer.toBinaryString(m));
        System.out.println(n+"； 二进制："+Integer.toBinaryString(n));

        System.out.println("///////////////////////////////////////////////");
        oldm = m;
        m ^= n;
        System.out.println(oldm+" ^= "+n+" -----> "+m);
        System.out.println(m+"； 二进制："+Integer.toBinaryString(m));
        System.out.println(n+"； 二进制："+Integer.toBinaryString(n));

        /*********************** 赋值运算符 ********************************/
    }
}
