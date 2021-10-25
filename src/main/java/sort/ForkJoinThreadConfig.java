package sort;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.ForkJoinPool;

public class ForkJoinThreadConfig implements AutoCloseable {

    ExecutorService forkJoin;

    public ForkJoinThreadConfig(int amountOfThreads) {
        forkJoin = new ForkJoinPool(amountOfThreads);
    }

    @Override
    public void close() {
        if (forkJoin != null) {
            forkJoin.shutdown();
        }
    }

    public void sort(int[] vals) {
        Partitions.invoke((ForkJoinPool) forkJoin, vals);
    }
}