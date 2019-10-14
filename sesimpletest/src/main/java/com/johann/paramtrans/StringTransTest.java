package main.java.com.johann.paramtrans;

/**
 * @ClassName StringTransTest
 * @Description TODO
 * @Author Johann
 * @Date 2019-10-8 15:11
 **/
public class StringTransTest {

    public static void stringTest(String m,String n){
        String tmp = m;
        m = n;
        n = tmp;

        System.out.println("String参数处理结果：m = "+m+",n = "+n);
    }

    public static void main(String[] args){
        String m = "i love China";
        String n = "hello world";
        System.out.println("方法执行前：m = "+m+",n = "+n);
        stringTest(m,n);
        System.out.println("方法执行后：m = "+m+",n = "+n);
    }
}
