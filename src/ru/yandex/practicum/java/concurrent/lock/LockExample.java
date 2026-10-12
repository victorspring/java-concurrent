package ru.yandex.practicum.java.concurrent.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockExample {

    private static final int LIMIT = 100_000_000;

    private static int count = 0;

    public static void main(String[] args) throws InterruptedException {

        Lock lock = new ReentrantLock();

        Thread t0 = new Thread(() -> {

            for (int i = 1; i <= LIMIT; i++) {
                while (!lock.tryLock()){
                    System.out.printf("%s: Waiting for a lock... (%d) %n", Thread.currentThread().getName(), i);
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                try {
                    count++;
                } finally {
                    lock.unlock();
                }
//                inc();
            }
        });

        Thread t1 = new Thread(() -> {
            for (int i = 1; i <= LIMIT; i++) {
                while (!lock.tryLock()){
                    System.out.printf("%s: Waiting for a lock... (%d) %n", Thread.currentThread().getName(), i);
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                try {
                    count--;
                } finally {
                    lock.unlock();
                }
//                dec();
            }
        });

        t0.start();
        t1.start();

        t0.join();
        t1.join();

        System.out.println(count);
    }

    public static synchronized void inc(){
        count++;
    }

    public static synchronized void dec(){
        count--;
    }
}
