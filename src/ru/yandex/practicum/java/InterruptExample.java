package ru.yandex.practicum.java;

public class InterruptExample {

    public static void main(String[] args) throws InterruptedException {
        Thread t0 = new Thread(() -> {
            for (int i = 1; true; i++) {
                if (Thread.interrupted())
                    return;
                System.out.printf("%s: %d%n", Thread.currentThread().getName(), i);
            }
        });

        Thread t1= new Thread(() -> {
            try {
                Thread.sleep(1000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        t0.start();
        t1.start();

        Thread.sleep(100);
        t0.interrupt();
        t1.interrupt();
    }
}
