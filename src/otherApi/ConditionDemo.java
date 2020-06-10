package otherApi;

import java.util.Date;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 两个数组，数据类型分别为{A,B,C,D,E...Z},{1,2,3,4,5,6...26}
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
        for (int i = 0; i < 26; i++) {
            System.out.println(strings[i]);
        }
    }
}
