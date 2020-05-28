package main.java.com.johann.algorithm;

/**
 * @ClassName: Recursion
 * @Description: 递归
 * @Author: Johann
 * @Date: 2020-04-08 11:35
 **/
public class Recursion {


    /**
    * @Description:  阶乘 n!
    * @Param: [x, ans]
    * @return: java.lang.Integer
    * @Author: Johann
    * @Date: 2020/4/8
    */
    public static Integer factorial(Integer x ,Integer ans){
        if(x==1){
            return ans;
        }
        return factorial(x-1,ans*x);
    }

    /** 
    * @Description: 阶乘 
    * @Param: [x] 
    * @return: java.lang.Integer
    * @Author: Johann 
    * @Date: 2020/4/8 
    */ 
    public static Integer factorial2(Integer x){
        if(x==1){
            return 1;
        }
        return factorial2(x-1)*x;
    }

    /** 
    * @Description: 汉诺塔问题
    * @Param: [] 
    * @return: void 
    * @Author: Johann 
    * @Date: 2020/4/28 
    */ 
    public static void towerOfHanoi(Integer num,String from,String temp,String to){
        if(num==1){
            System.out.println(num+" From "+from+" To "+to );//只有一个，直接把1号从 A 拿到 C 就可以了
        }else{
            towerOfHanoi(num-1,from,to,temp);//把 n-1个，从A 拿到 B
            System.out.println(num+" From "+from+" To "+to);//把第n号，从A 拿到 C
            towerOfHanoi(num-1,temp,from,to);//把n-1个，从B 拿到 C
        }
    }

    public static void main(String[] args) {
        //System.out.println(factorial(4,1));
        // (3,4) (2,4*3) (1,4*3*2) 24
        //System.out.println(factorial2(4));
        towerOfHanoi(4,"A","B","C");

    }
}
