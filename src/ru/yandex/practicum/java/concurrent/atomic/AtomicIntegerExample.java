package ru.yandex.practicum.java.concurrent.atomic;

import java.util.concurrent.atomic.AtomicInteger;

public class AtomicIntegerExample {

    private static final int LIMIT = 1000_000;

    private static AtomicInteger count = new AtomicInteger(0);

    public static void main(String[] args) throws InterruptedException {


        Thread t0 = new Thread(() -> {

            for (int i = 1; i <= LIMIT; i++) {
                count.incrementAndGet();
            }
        });

        Thread t1 = new Thread(() -> {
            for (int i = 1; i <= LIMIT; i++) {
                count.decrementAndGet();
            }
        });

        t0.start();
        t1.start();

        t0.join();
        t1.join();

        System.out.println(count);
    }

}
