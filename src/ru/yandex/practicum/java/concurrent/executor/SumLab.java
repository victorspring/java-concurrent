package ru.yandex.practicum.java.concurrent.executor;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class SumLab {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        long t1 = System.currentTimeMillis();
        long sum = sum(1, 1000_000_000);
        long t2 = System.currentTimeMillis();
        System.out.printf("Completed with single thread in %d ms, result = %d%n", t2 - t1, sum);

        t1 = System.currentTimeMillis();
        sum = sumMultiThread(1000_000_000, 4);
        t2 = System.currentTimeMillis();
        System.out.printf("Completed with multi threads in %d ms, result = %d%n", t2 - t1, sum);
    }

    private static long sum(long from, long to){
        long sum = 0;
        for (long i = from; i <= to; i++) {
            sum += i;
        }
        return sum;
    }

    private static long sumMultiThread(long to, int nTasks) throws ExecutionException, InterruptedException {
        ExecutorService pool = Executors.newCachedThreadPool();

        List<Future<Long>> tasks = new ArrayList<>();

        long step = to/nTasks;
        long r = to % nTasks;

        for (int i = 0; i < nTasks; i++) {
            long a = i*step + 1;
            long b = (i + 1)*step + (i == nTasks - 1 ? r : 0);
            tasks.add(pool.submit(() -> sum(a, b)));
        }
        pool.shutdown();

        long sum = 0;
        for (Future<Long> task : tasks){
            sum += task.get();
        }

        return sum;

    }
}
