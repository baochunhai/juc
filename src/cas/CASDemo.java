package cas;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class CASDemo {
    AtomicInteger count = new AtomicInteger();
//    Integer count = 0;
    /*synchronized*/ void m() {
        for (int i = 0; i < 10000; i++) {
            //if count1.get() < 1000
            count.incrementAndGet();
//            count++;
        }
    }

    public static void main(String[] args) {
        CASDemo demo = new CASDemo();
        List<Thread> threads = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            threads.add(new Thread(() -> {
                demo.m();
            }));

        }
        threads.forEach((o)->{
            o.start();
        });

        threads.forEach((o) -> {
            try {
                o.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        System.out.println(demo.count);
    }
}
