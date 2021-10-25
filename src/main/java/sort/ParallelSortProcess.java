package sort;

import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveAction;

public class ParallelSortProcess extends RecursiveAction {
    private final int[] vals;
    private final int l;
    private final int r;

    private ParallelSortProcess(int[] vals, int l, int r) {
        this.vals = vals;
        this.l = l;
        this.r = r;
    }

    public ParallelSortProcess(int[] vals) {
        this(vals, 0, vals.length - 1);
    }

    @Override
    protected void compute() {
        QuickSortProcess quickSortProcess = new QuickSortProcess();
        if (r - l < 0x1000) {
            quickSortProcess.makeSort(vals, l, r + 1);
        } else {
            int p = Partitions.part(vals, l, r);
            ForkJoinTask task = null;

            if (l < p) {
                task = new ParallelSortProcess(vals, l, p).fork();
            }

            if (p + 1 < r) {
                new ParallelSortProcess(vals, p + 1, r).invoke();
            }

            if (task != null) {
                task.join();
            }
        }
    }
}