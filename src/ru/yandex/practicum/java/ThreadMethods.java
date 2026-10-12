package ru.yandex.practicum.java;

public class ThreadMethods {

    public static void main(String[] args) throws InterruptedException {
        System.out.println(Thread.currentThread().getName());
        System.out.println(Thread.currentThread().getId());
        System.out.println(Thread.currentThread().getState());

        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 1; i <= 10; i++) {
                    System.out.printf("%s: %d%n", Thread.currentThread().getName(), i);
                }

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                for (int i = 11; i <= 20; i++) {
                    System.out.printf("%s: %d%n", Thread.currentThread().getName(), i);
                }

            }
        });

        System.out.println(t.getState());
        t.start();

        Thread.sleep(100);
        System.out.println(t.getState());

        Thread.sleep(1000);
        System.out.println(t.getState());

    }
}
