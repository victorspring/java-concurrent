package ru.yandex.practicum.java.concurrent.atomic;

import java.util.concurrent.atomic.DoubleAccumulator;
import java.util.function.DoubleBinaryOperator;

public class DoubleAccumulatorExample {

    private static final DoubleAccumulator val =
            new DoubleAccumulator( (multi, n) -> multi * n, 1);

    public static void main(String[] args) throws InterruptedException {
        Runnable task = () -> {
            for (int i = 0; i < 1000; i++) {
                val.accumulate(0.9);
            }
        };

        Thread t0 = new Thread(task);
        Thread t1 = new Thread(task);
        Thread t2 = new Thread(task);

        t0.start();
        t1.start();
        t2.start();

        t0.join();
        t1.join();
        t2.join();

        System.out.println(val);

    }
}
