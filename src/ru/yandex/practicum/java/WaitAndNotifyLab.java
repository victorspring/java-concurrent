package ru.yandex.practicum.java;

import java.util.function.DoubleUnaryOperator;

public class WaitAndNotifyLab {

    private static double count = 3;

    public static void main(String[] args) throws InterruptedException {
        Object monitor = new Object();

        Thread t0 = new Thread(getRunnable(x -> x*x, monitor));
        Thread t1 = new Thread(getRunnable(Math::sqrt, monitor));

        t0.start();
        t1.start();
    }

    public static Runnable getRunnable(DoubleUnaryOperator f, Object monitor){
        return () -> {
            for (int i = 0; i < 10; i++) {
                synchronized (monitor) {
                    count = f.applyAsDouble(count);
                    System.out.println(count);
                    monitor.notify();
                    try {
                        monitor.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }

            synchronized (monitor) {
                monitor.notify();
            }
        };
    }
}
