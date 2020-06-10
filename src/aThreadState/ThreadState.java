package aThreadState;

import util.ThreadUtil;

/**
 * @author Administrator
 */
public class ThreadState extends Thread{
    static int count = 0;
   static Object o = new Object();
    @Override
    public void run() {
       synchronized (o){

           System.out.println(this.getState());
           ThreadUtil.sleep(3000);
           try {
               o.wait();
           } catch (InterruptedException e) {
               e.printStackTrace();
           }
           System.out.println(this.getState());
           System.out.println("线程1执行完毕");
       }
    }

    public static void main(String[] args) {
        Thread t1 = new ThreadState();
        System.out.println(t1.getState());
        t1.start();
        ThreadUtil.sleep(1000);
        System.out.println(t1.getState());
        ThreadUtil.sleep(3000);
        System.out.println(t1.getState());
        Thread t2 = new Thread(()->{
            synchronized (o) {
                // wait()执行后 ，当前线程必须拥有此对象的锁，直到另一个线程通知线程在这个对象锁等待醒来
                // 否则会抛出异常
                o.notify();
                System.out.println(t1.getState());
            }
        });

        t2.start();

        ThreadUtil.sleep(1000);
        System.out.println(t1.getState());


    }
}
