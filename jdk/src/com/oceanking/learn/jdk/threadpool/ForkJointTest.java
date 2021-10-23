package com.oceanking.learn.jdk.threadpool;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

/**
 * @author whaiy
 * @version 1.0 2019/01/24
 */
public class ForkJointTest {

    private static double[] d;

    public static void main(String[] args) {
        d = createArrayOfRandomDoubles();
        int n = new ForkJoinPool().invoke(new ForkJoinTask(0, 4, d));
        System.out.println("Found " + n + " values");
    }

    private static double[] createArrayOfRandomDoubles() {
        return new double[]{1d, 0.6d, 0.1d, 0.1d, 0.1d};
    }

}

class ForkJoinTask extends RecursiveTask<Integer> {

    private int first;

    private int last;

    private double[] d;

    public ForkJoinTask(int first, int last, double[] d) {
        this.first = first;
        this.last = last;
        this.d = d;
    }

    protected Integer compute() {
        int subCount;
        if (last - first < 10) {
            subCount = 0;
            for (int i = first; i <= last; i++) {
                if (d[i] < 0.5) subCount++;
            }
        } else {
            int mid = (first + last) >>> 1;
            ForkJoinTask left = new ForkJoinTask(first, mid, d);
            left.fork();
            ForkJoinTask right = new ForkJoinTask(mid + 1, last, d);
            right.fork();
            subCount = left.join();
            subCount += right.join();
        }
        return subCount;
    }

}
