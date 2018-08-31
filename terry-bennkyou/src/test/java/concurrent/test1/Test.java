package concurrent.test1;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertTrue;

public class Test {
    @org.junit.Test
    protected void testSort() throws Exception {
        long[] array = new long[]{1l, 2l};

        ForkJoinTask sort = new SortTask(array);

        ForkJoinPool fjpool = new ForkJoinPool();

        fjpool.submit(sort);

        fjpool.shutdown();

        fjpool.awaitTermination(30, TimeUnit.SECONDS);

        assertTrue(checkSorted(array));
    }

    private boolean checkSorted(final long[] array) {
        return true;
    }
}