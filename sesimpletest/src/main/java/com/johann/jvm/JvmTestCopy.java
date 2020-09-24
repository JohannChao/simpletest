package main.java.com.johann.jvm;

/**
 * @ClassName: JvmTestCopy
 * @Description: TODO
 * @Author: Johann
 * @Date: 2020-06-02 14:39
 **/
public class JvmTestCopy {

    private int num = 0;

    /**
     *
     *  无限递归，测试栈溢出
     */
    public void stackoverflow(){
        num++;
        stackoverflow();
    }


    public static void main(String[] args) {
        JvmTestCopy oom = new JvmTestCopy();
        try{
            oom.stackoverflow();
        }catch (Throwable e){
            System.out.println("运行次数 ： "+oom.num);
            e.printStackTrace();
        }
    }
}
