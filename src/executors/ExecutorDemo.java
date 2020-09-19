package executors;

import util.ExecutorConfig;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Administrator
 */
public class ExecutorDemo {
    static int count = 1;
    static final ThreadPoolExecutor executor = ExecutorConfig.getExecutor();

    public static void main(String[] args) {



        System.out.println("开始执行任务");
        List<Future> list = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            executor.execute(() -> {
                System.out.println("假装执行异步任务");
            });

            Future<Object> submit = executor.submit(new Callable<Object>() {
                @Override
                public Object call() throws Exception {
                    return count++;
                }
            });
            list.add(submit);
        }
        AtomicInteger result = new AtomicInteger();
        // Future 需要调用get方法，阻塞当前任务，直到线程返回结果
        list.forEach((future) -> {
            try {
                Object o = future.get();
                if (o instanceof Integer) {
                    result.addAndGet((int) o);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        });
        System.out.println(result);
        System.out.println("结束执行任务");
        executor.shutdown();

    }

    public static void m2() {

    }
}
