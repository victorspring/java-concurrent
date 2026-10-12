package ru.yandex.practicum.java;

public class StringBuilderBuffer {

    public static void main(String[] args) throws InterruptedException {

//        StringBuilder builder = new StringBuilder();
        StringBuffer builder = new StringBuffer();

        Thread t0 = new Thread(() -> {
            for (int i = 1; i <= 1000; i++) {
                builder.append(i + ", ");
            }
        });

        Thread t1 = new Thread(() -> {
            for (int i = 1; i <= 1000; i++) {
                builder.append(i + ", ");
            }
        });

        t0.setDaemon(true);
        t0.start();
        t1.start();

        t0.join();
        t1.join();

        System.out.println(builder);


    }
}
