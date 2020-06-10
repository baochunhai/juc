package otherApi;


import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 可重入锁，sychronized是可重入的
 * ReetrantLock获得锁后必须手动释放锁
 * @author Administrator
 */
public class ReetrantLockDemo {
    Lock lock = new ReentrantLock();

    /**
     *  锁可重入，在m1中未释放锁的情况，m2可以执行
     */
    void m1() {
        lock.lock(); //synchronized(this)
        try {
            for (int i = 0; i < 10; i++) {
                System.out.println("m1:"+i);
                if(i==2){
                    m2();
                }
                // 验证tryLock()，打开下面注释
//                TimeUnit.SECONDS.sleep(1);
            }
            // 验证lockInterruptibly，打开下面注释
//            TimeUnit.SECONDS.sleep(Integer.MAX_VALUE);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    /**
     * 开启另外一个线程，线程间有争用，会在m1释放锁后执行m2
     */
    void m2() {
        lock.lock();
        try {
            System.out.println("m2 ...");
        } finally {
            lock.unlock();
        }

    }

    /**
     * tryLock();尝试加锁，5秒后，无论是否加锁成功，都继续执行下面代码
     */
    void m3() {
        boolean locked = false;
        try {
            locked = lock.tryLock(5, TimeUnit.SECONDS);
            System.out.println("m3:"+locked);
            for (int i = 0; i < 10; i++) {
                System.out.println("m3:"+i);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if(locked){
                lock.unlock();
            }
        }
    }

    void m4(){
        try{
            lock.lockInterruptibly();
            System.out.println("m4 start");
            TimeUnit.SECONDS.sleep(5);
            System.out.println("m4 end");
        }catch (InterruptedException e){
            System.out.println("interrupted!");
        }finally {
            lock.unlock();
        }

    }
    public static void main(String[] args) {
        ReetrantLockDemo rl = new ReetrantLockDemo();

        Thread t1 = new Thread(rl::m1);
        t1.start();
        new Thread(rl::m2).start();

        new Thread(rl::m3).start();
        Thread t = new Thread(rl::m4);
        t.start();
        t.interrupt();
    }
}
