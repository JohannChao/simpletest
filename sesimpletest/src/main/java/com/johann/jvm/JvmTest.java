package com.johann.jvm;

/**
 * @ClassName: JvmTest
 * @Description:    IDEA 设置虚拟机堆栈步骤
 *                    1）Run ---> Edit Configurations ---> VM options
 *                    -XX:+PrintGCDetails -Xss128k
 *                    这种方法修改的内容是针对的单个main类
 *
 *                    2）Help ---> Edit Custom VM options（首次会初始化文件 idea64.exe.vmoptions 或者 idea.exe.vmoptions）
 *                    根据系统，修改这个文件里面的内容。
 *                    这种方法修改，针对的是ide环境。
 * @Author: Johann
 * @Date: 2020-06-02 14:05
 **/
public class JvmTest {

    private int num = 0;

    /**
    *
     *  无限递归，测试栈溢出
    */
    public void stackOverFlow(){
        num++;
        stackOverFlow();
    }

    private void dontStop() {
        while (true) {
        }
    }

    public void stackOutOfMemory(){
        while (true) {
             Thread thread = new Thread(() -> dontStop());
             thread.start();
        }
    }


    public static void main(String[] args) {
        JvmTest oom = new JvmTest();
        try{
            //oom.stackOverFlow();
            oom.stackOutOfMemory();
        }catch (Throwable e){
            System.out.println("运行次数 ： "+oom.num);
            e.printStackTrace();
        }
    }

}
