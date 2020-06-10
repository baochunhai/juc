package otherApi;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * ReetrantLock提供公平锁，阻塞的情况添加到等待队列，按队列顺序执行
 * Sychronized只有非公平锁
 *
 * @author Administrator
 */
public class ReetrantLockFair extends Thread {
   private static Lock lock = new ReentrantLock(true);

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            lock.lock();
            try {
                System.out.println(Thread.currentThread().getName() +"获得锁"+ i);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            } finally {
                lock.unlock();
            }
        }
    }

    public static void main(String[] args) {
        Thread t = new ReetrantLockFair();
        Thread t1 = new ReetrantLockFair();
        t.start();
        t1.start();
    }
}
