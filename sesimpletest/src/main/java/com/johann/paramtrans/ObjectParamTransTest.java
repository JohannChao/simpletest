package main.java.com.johann.paramtrans;

/**
 * @ClassName ObjectParamTransTest
 * @Description TODO
 * @Author Johann
 * @Date 2019-10-8 15:01
 **/
public class ObjectParamTransTest {

    public static void trans(Obj obj){
        int tmp = obj.m;
        obj.m = obj.n;
        obj.n = tmp;
        System.out.println("对象交换方法中：Obj.m = "+obj.m+",Obj.n = "+obj.n);
    }

//    public static void transArray(String[] param){
//        String tmp = param[0];
//        param[0] = param[1];
//        param[1] = tmp;
//        System.out.println("数组交换方法中：param[0] = "+param[0]+",param[1] = "+param[1]);
//    }

    public static void main(String[] args){
        Obj obj= new Obj(10,20);
        System.out.println("对象交换方法执行前：Obj.m = "+obj.m+",Obj.n = "+obj.n);
        trans(obj);
        System.out.println("对象交换方法执行后：Obj.m = "+obj.m+",Obj.n = "+obj.n);

//        String[] array = new String[]{"hello world","i love China"};
//        System.out.println("数组交换方法执行前：array[0] = "+array[0]+",array[1] = "+array[1]);
//        transArray(array);
//        System.out.println("数组交换方法执行后：array[0] = "+array[0]+",array[1] = "+array[1]);
    }
}

class Obj{
    Obj(){}
    Obj(int a,int b){
        m = a;
        n = b;
    }
    int m;
    int n;
}
