package otherApi;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * 门闩，控制指定数量线程到达后，才继续执行
 */
public class CountDownLanchDemo {
    static void lanch(){
        CountDownLatch count = new CountDownLatch(3);
        for (int i = 0; i < 3; i++) {
            new Thread(() -> {
                try {
                    TimeUnit.SECONDS.sleep((long) (Math.random() * 5));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                count.countDown();
                System.out.println(count);
            }).start();

        }

        try {
            // 调用await的时候，只有count值为0才会继续执行
            count.await();
            System.out.println(count);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("end latch");

    }

    public static void main(String[] args) {
        lanch();

    }
}
