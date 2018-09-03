package concurrent.test1;

import java.util.concurrent.RecursiveTask;

public class Fibonacci extends RecursiveTask {

    final int n;

    Fibonacci(int n) {
        this.n = n;
    }

    private int compute(int small) {

        final int[] results = {1, 1, 2, 3, 5, 8, 13, 21, 34, 55, 89};

        return results[small];
    }

    public Integer compute() {
        if (n <= 10) {
            return compute(n);
        }

        Fibonacci f1 = new Fibonacci(n - 1);

        Fibonacci f2 = new Fibonacci(n - 2);

        f1.fork();
        f2.fork();

        return (Integer) f1.join() + (Integer) f2.join();
    }
}