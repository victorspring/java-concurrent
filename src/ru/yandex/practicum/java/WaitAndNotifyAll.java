package ru.yandex.practicum.java;

public class WaitAndNotifyAll {

    public static void main(String[] args) throws InterruptedException {

        Object monitor = new Object();

        Runnable r = () -> {
            synchronized (monitor) {
                System.out.printf("%s: Waiting%n", Thread.currentThread().getName());
                try {
                    monitor.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.printf("%s: Completed%n", Thread.currentThread().getName());
            }
        };

        new Thread(r).start();
        new Thread(r).start();
        new Thread(r).start();

        Thread.sleep(1000);

        synchronized (monitor) {
//            monitor.notify();
            monitor.notifyAll();
        }
    }
}
