package ru.yandex.practicum.java;

public class RaceConditionSynchronized {

    private static final int LIMIT = 1000_000;

    private static final Object monitor = new Object();

    private static int count = 0;

    public static void main(String[] args) throws InterruptedException {
        Thread t0 = new Thread(() -> {
            for (int i = 1; i <= LIMIT; i++) {
                    count++;
            }
        });
        Thread t1 = new Thread(() -> {
            for (int i = 1; i <= LIMIT; i++) {
                    count--;
            }
        });

        t0.start();
        t1.start();

        t0.join();
        t1.join();

        System.out.println(count);
    }

/*    public static synchronized void inc() {
        count++;
    }

    public static synchronized void dec() {
        count--;
    }*/
}
