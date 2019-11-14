package main.java.com.johann.stringTest;

/**
 * @ClassName StringInternTest
 * @Description TODO
 * @Author Johann
 * @Date 2019-11-13 15:36
 **/
public class StringInternTest {

    public static void main(String[] args) {
        String a = "a";
        String param = new String("param" + a);
        String paramSame = param.intern();
        System.out.println(param == paramSame);

        System.out.println("==================================");

        //String a = "a";
        String param1 = new String("param" + "a");
        String paramSame1 = param.intern();
        System.out.println(param1 == paramSame1);

        System.out.println("==================================");

        String s1 = "hello"+"world";
        String s0 = "helloworld";
        System.out.println(s1==s0);

        String s2 = "nihao";
        String s3 = "java";
        String s4 = s2+s3;
        String s5 = "nihaojava";
        System.out.println(s4==s5);
    }
}