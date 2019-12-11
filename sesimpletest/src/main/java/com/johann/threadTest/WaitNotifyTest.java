package main.java.com.johann.threadTest;

public class WaitNotifyTest {
    public static void main(String[] args) {
        MoonCake moonCake = new MoonCake();
        CakeShop cakeShop = new CakeShop(moonCake);
        Customer customer = new Customer(moonCake);
        cakeShop.start();
        customer.start();
    }
}
class MoonCake{
    String stuffing;
    boolean cakeFlag = false;
}

class CakeShop extends Thread{
    private MoonCake moonCake;
    public CakeShop(MoonCake moonCake){
        this.moonCake = moonCake;
    }

    @Override
    public void run(){
        int count = 0;
        while(true){
            synchronized (moonCake){
                if(moonCake.cakeFlag){
                    try{
                        System.out.println("当前有月饼库存，蛋糕店无需生产月饼，顾客可直接进行购买。。。。。。");
                        moonCake.wait();//如果有月饼，让蛋糕店线程进入月饼的线程监狱等待
                    } catch (InterruptedException e){
                        e.printStackTrace();
                    }
                }
                if(count % 3 == 0){
                    moonCake.stuffing = "伍仁";
                }else if(count % 3 == 1){
                    moonCake.stuffing = "莲蓉";
                }else{
                    moonCake.stuffing = "蛋黄";
                }
                count ++;
                System.out.println("当前正在生产 ["+moonCake.stuffing+"] 月饼中。。。。。。");
                try{
                    Thread.sleep(2000);
                } catch (InterruptedException e){
                    e.printStackTrace();
                }
                moonCake.cakeFlag = true;
                System.out.println("["+moonCake.stuffing+"] 月饼已经生产完成，可以进行售卖了。。。。。。");
                moonCake.notify();
            }
        }
    }
}

class Customer extends Thread{
    private MoonCake moonCake;
    public Customer(MoonCake moonCake){
        this.moonCake = moonCake;
    }

    @Override
    public void run(){
        while(true){
            synchronized (moonCake){
                if(!moonCake.cakeFlag){
                    try{
                        System.out.println("当前无月饼库存，顾客无法购买，请等待蛋糕店生产月饼。。。。。。");
                        moonCake.wait();
                    } catch (InterruptedException e){
                        e.printStackTrace();
                    }
                }
                System.out.println("顾客正在购买 ["+moonCake.stuffing+"] 月饼中。。。。。。");
                try{
                    Thread.sleep(2000);
                } catch (InterruptedException e){
                    e.printStackTrace();
                }
                moonCake.cakeFlag = false;
                System.out.println("顾客付完款，已购买到 ["+moonCake.stuffing+"] 月饼。。。。。。");
                moonCake.notify();
                System.out.println("-----------------------------------------------------------------------\r\n");
            }
        }
    }
}