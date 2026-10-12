package ru.yandex.practicum.java;

public class Deadlock {

    private static final Object lock1 = new Object();
    private static final Object lock2 = new Object();

    public static void main(String[] args) {

        Thread t1 = new Thread(() -> {
            synchronized (lock1) {
                System.out.printf("%s: lock1%n", Thread.currentThread().getName());

                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                synchronized (lock2) {
                    System.out.printf("%s: lock2%n", Thread.currentThread().getName());
                }
            }
        });

        Thread t2 = new Thread(() -> {
            synchronized (lock2) {
                System.out.printf("%s: lock2%n", Thread.currentThread().getName());

                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                synchronized (lock1) {
                    System.out.printf("%s: lock1%n", Thread.currentThread().getName());
                }
            }
        });

        t1.start();
        t2.start();
    }

}