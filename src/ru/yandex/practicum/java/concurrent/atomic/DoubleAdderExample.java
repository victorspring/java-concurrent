package ru.yandex.practicum.java.concurrent.atomic;

import java.util.concurrent.atomic.DoubleAdder;

public class DoubleAdderExample {

    private static final DoubleAdder val = new DoubleAdder();

    public static void main(String[] args) throws InterruptedException {
        Runnable task = () -> {
            for (int i = 0; i < 1000; i++) {
                val.add(0.5);
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
