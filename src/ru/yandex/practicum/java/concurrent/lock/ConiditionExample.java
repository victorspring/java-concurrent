package ru.yandex.practicum.java.concurrent.lock;

import java.nio.file.Path;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ConiditionExample {

    private static final Lock lock = new ReentrantLock();

    private static final Condition cond1 = lock.newCondition();
    private static final Condition cond2 = lock.newCondition();

    private static int n;

    public static void main(String[] args) throws InterruptedException {

        Thread t0 = new Thread(() -> {
            for (int i = 1; i <= 500; i++) {
                lock.lock();
                try {
                    n++;
                    System.out.printf("%s: %d (i = %d)%n", Thread.currentThread().getName(), n, i);
                    if (i == 400)
                        cond1.signal();
                    if (i == 100)
                        cond2.signal();
                } finally {
                    lock.unlock();
                }
            }
        });

        Thread t1 = new Thread(() -> {
            lock.lock();
            try {
                cond1.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }

            for (int i = 1; i <= 400; i++) {
                lock.lock();
                try {
                    n--;
                    System.out.printf("%s: %d (i = %d)%n", Thread.currentThread().getName(), n, i);
                } finally {
                    lock.unlock();
                }
            }
        });

        Thread t2 = new Thread(() -> {
            lock.lock();
            try {
                cond2.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }

            for (int i = 1; i <= 100; i++) {
                lock.lock();
                try {
                    n--;
                    System.out.printf("%s: %d (i = %d)%n", Thread.currentThread().getName(), n, i);
                } finally {
                    lock.unlock();
                }
            }
        });

        t0.start();
        t1.start();
        t2.start();

        t0.join();
        t1.join();
        t2.join();

        System.out.println(n);



    }
}
