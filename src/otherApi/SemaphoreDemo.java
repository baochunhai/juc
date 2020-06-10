package otherApi;

import util.ThreadUtil;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * 信号量，可以限制并发访问的线程数
 */
public class SemaphoreDemo {
    public static void main(String[] args) {
        Semaphore s = new Semaphore(2);

        Thread t1 = new Thread(()->{
            try {
                s.acquire();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName()+"执行");
        });
        t1.start();
        Thread t2 = new Thread(()->{
            try {
                s.acquire();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName()+"执行");
        });
        t2.start();
        Thread t3 = new Thread(()->{
            try {
                s.acquire();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName()+"执行");
        });
        t3.start();
        ThreadUtil.sleep(5000);
        s.release();


    }

}
