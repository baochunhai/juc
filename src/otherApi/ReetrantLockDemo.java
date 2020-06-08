package otherApi;

import sun.misc.Unsafe;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 可重入锁，sychronized是可重入的
 */
public class ReetrantLockDemo {

    public static void main(String[] args) {
        Lock lock = new ReentrantLock();
        try{
            lock.lock();
        }catch (Exception e){

        }finally {
            lock.unlock();
        }


    }
}
