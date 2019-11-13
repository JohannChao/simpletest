package main.java.com.johann.stringTest;

/**
 * @ClassName StringInternTest
 * @Description TODO
 * @Author Johann
 * @Date 2019-11-13 15:36
 **/
public class StringInternTest {

    public static void main(String[] args) {
        String s15 = new String("lo"+"ve");//这一句我们只知道在堆中创建了"love"对象,并不知道在 StringTable中是否放入一个"love"
        //s15.intern();//如果上一句执行完，如果在StringTable中放入了"love"，那么下面的执行结果就是false；如果在StringTable中没有放入了"love"，执行该句，会把堆中“love”对象的引用放入到StringTable中，即下面的结果是 true
        //String s16 = "love";
        String s16 = s15.intern();;
        System.out.println("s15 == s16 : "+(s15==s16));// fasle 说明执行 new String("lo"+"ve"); 时，在StringTable中已经放入了“love”

        System.out.println("=================================");

        String s17 = new String("mi"+"ne");
        //String s17 = new String("mi");

        String s18 = new String("m")+new String("i");//只会在堆中创建"mi",在StringTable不放入“mi”
        //String s18 = new StringBuilder().append("m").append("i").toString();
        System.out.println("s17 == s18 : "+(s18.intern()==s18));

    }
}
