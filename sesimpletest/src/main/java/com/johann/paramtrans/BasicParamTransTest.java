package main.java.com.johann.paramtrans;

/**
 * @ClassName BasicParamTransTest
 * @Description TODO
 * @Author Johann
 * @Date 2019-10-8 14:43
 **/
public class BasicParamTransTest {

    /**
     * @Author Johann
     * @Description //基本类型
     * @Date 14:52 2019-10-8
     * @Param [m, n]
     * @return void
     **/
    public static void basicTest(int m,int n){
        int tmp = m;
        m = n;
        n = tmp;

        System.out.println("基本类型处理结果：m = "+m+",n = "+n);
    }

    public static void main(String[] args){
        int m = 10;
        int n = 20;
        System.out.println("方法执行前：m = "+m+",n = "+n);
        basicTest(m,n);
        System.out.println("方法执行后：m = "+m+",n = "+n);
    }
}
