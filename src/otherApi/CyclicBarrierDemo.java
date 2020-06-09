package otherApi;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 *循环栅栏,当指定数量线程到达后，执行一次
 * CountDownLatch 是一次性的，CyclicBarrier 是可循环利用的
 * CountDownLatch 参与的线程的职责是不一样的，有的在倒计时，有的在等待倒计时结束。CyclicBarrier 参与的线程职责是一样的。
 * 可以用来多线程运算，并将结果合并
 */
public class CyclicBarrierDemo {

    public static void main(String[] args) {
       CyclicBarrier cyclicBarrier = new CyclicBarrier(5,()->System.out.println("最后完成"));

        for (int i = 0; i < 10; i++) {
            new Thread(()->{
                try {
                    cyclicBarrier.await();
                    System.out.println(Thread.currentThread().getName()+"执行任务");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }

            }).start();
        }
    }

}
