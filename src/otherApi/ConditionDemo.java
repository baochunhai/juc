package otherApi;

import java.util.Date;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 两个数组，数据类型分别为{A,B,C,D,E...Z},{1,2,3,4,5,6...26}
 * 要求打印结果A1,B2,C3,D4....Z26
 */
public class ConditionDemo {
   static String [] strings = new String[26];
   static int [] nums = new int[26];
    static {
        for (int i = 0; i < strings.length; i++) {
            strings[i] = Character.toString ((char)('A' + i));
            nums[i] = i +1;
        }
    }

    public static void main(String[] args) {
        Lock lock = new ReentrantLock();
        Condition condition = lock.newCondition();
        Condition condition1 = lock.newCondition();

        new Thread(()->{
            for (int i = 0; i < strings.length; i++) {
                lock.lock();
                try {
                    condition.await();
                    System.out.print(strings[i]);
                    condition1.signal();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    lock.unlock();
                }
            }

        }).start();
        new Thread(()->{
            for (int i = 0; i < nums.length; i++) {
                lock.lock();
                try {
                    condition.signal();
                    condition1.await();
                    System.out.print(nums[i]+",");

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    lock.unlock();
                }
            }
        }).start();

    }
}
