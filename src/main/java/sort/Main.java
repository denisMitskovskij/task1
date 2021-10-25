package sort;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Main {

    static List<UtilsSort> containers;

    public static void main(String[] args) throws Exception {
        List<UtilsSort> dataArr;
        double seqTimeTotal = 0;
        double parTimeTotal = 0;
        for (int i = 0; i < 5; i++) {
            dataArr = new ArrayList<>();
            dataArr.add(new UtilsSort(genRandom(100000000)));
            containers = new ArrayList<>();

            for (UtilsSort data : dataArr) {
                containers.add((UtilsSort) data.clone());
            }
            seqTimeTotal = seqTimeTotal + sortSeq();
            containers = new ArrayList<>();

            for (UtilsSort data : dataArr) {
                containers.add((UtilsSort) data.clone());
            }
            parTimeTotal = parTimeTotal + sortParallel(new ForkJoinThreadConfig(4));
        }
        if (parTimeTotal != 0) {
            System.out.println("Average ratio seq/par is " + (seqTimeTotal / parTimeTotal));
        }
    }

    public static long sortParallel(ForkJoinThreadConfig sortStrategy) {
        long processingTime = 0;
        try (ForkJoinThreadConfig strategy = sortStrategy) {
            for (UtilsSort data : containers) {
                processingTime = System.currentTimeMillis();
                strategy.sort(data.vals);
                processingTime = System.currentTimeMillis() - processingTime;
                System.out.println("Parallel Quick Sort with 4 threads is finished in " + processingTime + "ms");
                if (data.isSorted()) {
                    System.out.println("SUCCESS");
                } else {
                    System.out.println("FAILURE");
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return processingTime;
    }

    private static long sortSeq() {
        long processingTime = 0;
        for (UtilsSort data : containers) {
            processingTime = System.currentTimeMillis();
            QuickSortProcess quickSortProcess = new QuickSortProcess();
            quickSortProcess.makeSort(data.vals);
            processingTime = System.currentTimeMillis() - processingTime;

            System.out.println("Sequential Quick Sort is finished in " + processingTime + "ms");
            if (data.isSorted()) {
                System.out.println("SUCCESS");
            } else {
                System.out.println("FAILURE");
            }

        }
        return processingTime;
    }

    private static int[] genRandom(int size) {
        int[] arr = new int[size];
        Random r = new Random();

        for (int i = 0; i < size; i++) {
            arr[i] = r.nextInt();
        }

        return arr;
    }
}