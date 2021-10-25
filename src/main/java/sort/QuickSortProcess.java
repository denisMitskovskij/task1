package sort;

import java.util.Arrays;

public class QuickSortProcess {

    public static void sort(int[] vals, int min, int max) {
        if (vals.length == 0)
            return;

        if (min >= max)
            return;

        int mVal = vals[min + (max - min) / 2];

        int x = min;
        int y = max;
        while (x <= y) {
            while (vals[x] < mVal) {
                x++;
            }

            while (vals[y] > mVal) {
                y--;
            }

            if (x <= y) {
                int stored = vals[x];
                vals[x] = vals[y];
                vals[y] = stored;
                x++;
                y--;
            }
        }

        if (min < y)
            sort(vals, min, y);

        if (max > x)
            sort(vals, x, max);
    }

    public void makeSort(int[] x) {
        int min = 0;
        int max = x.length - 1;

        sort(x, min, max);
    }

    public void makeSort(int[] x, int min, int max){
        int[] part = Arrays.copyOfRange(x, min, max);
        makeSort(part);
        for (int k = 0; k < part.length; k++){
            x[min+k] = part[k];
        }
    }
}