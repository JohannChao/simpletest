package com.johann.paramtrans;

/**
 * @ClassName WrapperParamTransTest
 *  当对象是 String，或者基本数据类型的包装类时，参数传入方法处理后，原来的变量值不变。这是因为，【String 是不可变的，包装类的值也是不可变的】。
 *  在方法中当参数被修改的时候，是在堆中重新开辟空间，存储新的字符串或包装类对象，并将参数重新指向新开辟空间的地址。参数是什么，原变量的Copy吖，原变量当然不变。
 * @Author Johann
 * @Date 2019-10-8 14:55
 **/
public class WrapperParamTransTest {

    public static void wrapperTest(Integer m,Integer n){
        Integer tmp = m;
        m = n;
        n = tmp;

        System.out.println("包装器类型处理结果：m = "+m+",n = "+n);
    }

    public static void main(String[] args){
        Integer m = 10;
        Integer n = 20;
        System.out.println("方法执行前：m = "+m+",n = "+n);
        wrapperTest(m,n);
        System.out.println("方法执行后：m = "+m+",n = "+n);
    }
}
