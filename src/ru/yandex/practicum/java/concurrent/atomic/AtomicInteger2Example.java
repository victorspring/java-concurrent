package ru.yandex.practicum.java.concurrent.atomic;

import java.util.concurrent.atomic.AtomicInteger;

public class AtomicInteger2Example {

    private static final int LIMIT = 1000_000;

    private static AtomicInteger count = new AtomicInteger(0);

    public static void main(String[] args) throws InterruptedException {

        Thread t0 = new Thread(() -> {
            for (int i = 1; i <= LIMIT; i++) {
                count.updateAndGet(x -> x++);
            }
        });

        Thread t1 = new Thread(() -> {
            for (int i = 1; i <= LIMIT; i++) {
                count.updateAndGet(x -> x--);
            }
        });

        t0.start();
        t1.start();

        t0.join();
        t1.join();

        System.out.println(count);
    }

}
