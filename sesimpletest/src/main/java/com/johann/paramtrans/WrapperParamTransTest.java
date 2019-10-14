package main.java.com.johann.paramtrans;

/**
 * @ClassName WrapperParamTransTest
 * @Description TODO
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
