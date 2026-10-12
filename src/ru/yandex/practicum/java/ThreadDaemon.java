package ru.yandex.practicum.java;


public class ThreadDaemon {
    public static void main(String[] args) throws InterruptedException {
        Thread t0 = new Thread(() -> {
            for (int i = 1; i <= 1000; i++) {
                System.out.printf("%s: %d%n", Thread.currentThread().getName(), i);
            }
        });

        t0.setDaemon(true);
        t0.start();

        Thread.sleep(100);

    }
}
