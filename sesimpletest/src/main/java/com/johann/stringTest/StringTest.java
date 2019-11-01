package main.java.com.johann.stringTest;

/**
 * @ClassName StringTest
 * @Description TODO
 * @Author Johann
 * @Date 2019-10-31 18:09
 **/
public class StringTest {

    public static void main(String[] args) {

        String em = new String();
        String em1 = "";
        em = em.intern();

        String s1 = new String("cat");
        String s4 = s1.intern();
        String s5 = new String("cat");


        String s2 = "cat";
        String s10 = new String(s2);

        String s3 = "cat";
        System.out.println(s3==s4);


        System.out.println(s1==s2);
        System.out.println(s2==s3);
        System.out.println(s1==s5);
        System.out.println(s4==s5);

        String s6 = s1;
        System.out.println(s6==s1);
        System.out.println(s6==s2);

        s1 = s1.intern();
        System.out.println(s1==s2);
        System.out.println(s6==s1);

        String s7 = s1;
        System.out.println(s7==s1);
        System.out.println(s7==s2);
        System.out.println(s6==s7);

        //new StringTest().foo("");
    }

    public void foo(String s) {
        System.out.println("String");
    }

    public void foo(StringBuffer sb){
        System.out.println("StringBuffer");
    }
    public void foo(Object o) {
        System.out.println("Object");
    }
}
