package ru.yandex.practicum.java;

public class JoinExample {

    public static void main(String[] args) {
        Thread t0 = new Thread(() -> {
            for (int i = 1; i <= 1000; i++) {
                System.out.printf("%s: %d%n", Thread.currentThread().getName(), i);
            }
        });

        Thread t1 = new Thread(() -> {
            for (int i = 1001; i <= 2000; i++) {
                System.out.printf("%s: %d%n", Thread.currentThread().getName(), i);
            }
        });

        t0.start();

        try {
            t0.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        t1.start();
    }
}
