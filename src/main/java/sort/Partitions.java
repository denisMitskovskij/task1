package sort;

import java.util.concurrent.ForkJoinPool;

public class Partitions {

    public static int[] invoke(ForkJoinPool forkJoin, int[] vals) {
        forkJoin.invoke(new ParallelSortProcess(vals));
        return vals;
    }

    public static int part(int[] vals, int l, int r) {
        int p = vals[l + (r - l) / 2];

        l = --l;
        r = ++r;

        while (true) {
            do {
                l = ++l;
            } while (vals[l] < p);

            do {
                r = --r;
            } while (vals[r] > p);

            if (l < r) {
                int stored = vals[l];
                vals[l] = vals[r];
                vals[r] = stored;
            } else {
                return r;
            }
        }
    }
}