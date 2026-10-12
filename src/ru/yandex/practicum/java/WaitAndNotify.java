package ru.yandex.practicum.java;

public class WaitAndNotify {

    public static void main(String[] args) {
        Object monitor = new Object();

        Thread t0 = new Thread(() -> {
            synchronized (monitor) {
                System.out.printf("%s: Waiting%n", Thread.currentThread().getName());
                try {
                    monitor.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.printf("%s: Completed%n", Thread.currentThread().getName());
                monitor.notify();
            }
        });

        Thread t1 = new Thread(() -> {
            for (int i = 1; i <= 1000; i++) {
                synchronized (monitor){
                    System.out.printf("%s: %d%n", Thread.currentThread().getName(), i);
                    if (i == 500) {
                        monitor.notify();
                        try {
                            monitor.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        });

        t0.start();
        t1.start();
    }
}
