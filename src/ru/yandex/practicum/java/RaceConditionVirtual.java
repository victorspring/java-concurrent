package ru.yandex.practicum.java;

public class RaceConditionVirtual {

    private static final int LIMIT = 1_000_000;

    private static int count = 0;

    public static void main(String[] args) throws Exception {

        Thread t0 = Thread.ofVirtual().start(() -> {
            for (int i = 0; i < LIMIT; i++) {
//                count++;
                inc();
            }
        });

        Thread t1 = Thread.ofVirtual().start(() -> {
            for (int i = 0; i < LIMIT; i++) {
//                count--;
                dec();
            }
        });

        t0.join();
        t1.join();

        System.out.println(count);
    }

    public static synchronized void inc() {
        count++;
    }

    public static synchronized void dec() {
        count--;
    }
}