package ru.yandex.practicum.java;

public class PriorityExample {

    public static int n0 = 0;
    public static int n1 = 0;
    public static final int LIMIT = 1000_000;

    public static void main(String[] args) throws InterruptedException {
        Thread t0 = new Thread(() -> {
            for (int i = 1; i <= LIMIT; i++) {
                n0++;
            }
        });

        Thread t1 = new Thread(() -> {
            for (int i = 1; i <= LIMIT; i++) {
                n1++;
            }
        });

        t0.setPriority(Thread.MAX_PRIORITY);
        t1.setPriority(Thread.MIN_PRIORITY);

        t0.start();
        t1.start();

        while (n0 < LIMIT && n1 < LIMIT){
            Thread.yield();
        }

        System.out.println("n0= " + n0);
        System.out.println("n1= " + n1);
    }
}
