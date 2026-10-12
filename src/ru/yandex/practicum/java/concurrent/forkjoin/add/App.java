package ru.yandex.practicum.java.concurrent.forkjoin.add;

import java.util.concurrent.ForkJoinPool;

public class App {

    public static final long STEP = 200_000_000;

    public static void main(String[] args) {
        long t1 = System.currentTimeMillis();
        long sum = ForkJoinPool.commonPool().invoke(new Adder(1, 1000_000_000L));
        long t2 = System.currentTimeMillis();
        System.out.println(sum);
        System.out.printf("Completed in %d ms", t2 - t1);
    }


}