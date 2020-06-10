package util;

/**
 * @author Administrator
 */
public class ThreadUtil {
    private ThreadUtil(){
        throw new RuntimeException("Can not create this Class!");
    }
    public static void sleep(long time){
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
