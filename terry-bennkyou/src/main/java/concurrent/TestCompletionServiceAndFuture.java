package concurrent;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

class HandleFuture<Integer> implements Callable<Integer> {

    private Integer num;

    public HandleFuture(Integer num) {
        this.num = num;
    }

    @Override
    public Integer call() throws Exception {
        Thread.sleep(3 * 100);
        System.out.println(Thread.currentThread().getName());
        return num;
    }
}

public class TestCompletionServiceAndFuture {
    public void futureTest() throws InterruptedException, ExecutionException {
        System.out.println("main Thread begin:");
        ExecutorService executor = Executors.newCachedThreadPool();
        List<Future<Integer>> result = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Future<Integer> submit = executor.submit(new HandleFuture(i));
            result.add(submit);
        }
        executor.shutdown();
        for (int i = 0; i < 10; i++) {//一个一个等待返回结果
            System.out.println("Result: " + result.get(i).get());
        }
        System.out.println("main Thread end:");
    }

    public void compleTest() throws InterruptedException, ExecutionException {
        System.out.println("main Thread begin:");
        ExecutorService executor = Executors.newCachedThreadPool();
        // 构建完成服务
        CompletionService<Integer> completionService = new ExecutorCompletionService<>(executor);
        for (int i = 0; i < 10; i++) {
            completionService.submit(new HandleFuture(i));
        }
        for (int i = 0; i < 10; i++) {//一个一个等待返回结果
            System.out.println("Result: " + completionService.take().get());
        }
        System.out.println("main Thread end:");
    }

    public static void main(String[] args) {
        TestCompletionServiceAndFuture test = new TestCompletionServiceAndFuture();
        try {
//            test.futureTest();
            test.compleTest();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}



