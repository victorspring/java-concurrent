package ru.yandex.practicum.java.concurrent.atomic;

import java.util.concurrent.atomic.AtomicReference;

public class AtomicReferenceExample {

    private static final int LIMIT = 1000;

    public static void main(String[] args) throws InterruptedException {

        AtomicReference<Double> value = new AtomicReference<>(1.0);

        Thread t0 = new Thread(() -> {
            for (int i = 1; i <= LIMIT; i++) {
                System.out.println(value);
                value.updateAndGet(x -> x * 2);
            }
        });

        Thread t1 = new Thread(() -> {
            for (int i = 1; i <= LIMIT; i++) {
                System.out.println(value);
                value.updateAndGet(x -> x / 2);
            }
        });

        t0.start();
        t1.start();

        t0.join();
        t1.join();

        System.out.println(value);
    }

}
