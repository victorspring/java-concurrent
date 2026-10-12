package ru.yandex.practicum.java;

public class ThreadExample {

    public static final int LIMIT = 1000;

    public static class Counter extends Thread {

        @Override
        public void run() {
            for (int i = 1; i <= LIMIT; i++) {
                System.out.printf("%s: %d%n", Thread.currentThread().getName(), i);
            }
        }
    }

    public static void main(String[] args){
        Thread t1 = new Counter();
        t1.start();

        Runnable r = () -> {
            for (int i = 1; i <= LIMIT; i++) {
                System.out.printf("%s: %d%n", Thread.currentThread().getName(), i);
            }
        };
        Thread t2 = new Thread(r);
        t2.start();
    }
}
