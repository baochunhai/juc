package cas;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * @author Administrator
 */
public class VolatileTest {

    public static void main(String[] args) {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(200000);
        for (int i = 0; i < 100000000; i++) {
            new Thread(()->{
                try {
                    cyclicBarrier.await();
                    DCL.getInstance();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }).start();
        }


    }
}
