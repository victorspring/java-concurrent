package ru.yandex.practicum.java.concurrent.collection;

import java.util.Scanner;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class BlockingQueueExample {

    public static void main(String[] args) {
        BlockingQueue<Integer> queue = new ArrayBlockingQueue<>(5);

        System.out.println("Enter the number:");

        try (Scanner scanner = new Scanner(System.in)) {

            int n = scanner.nextInt();

            new Thread(() -> {
                for (int i = 1; i <= n; i++) {
                    try {
                        queue.put(i);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("Added: " + i);
                }
            }).start();

            new Thread(() -> {
                while (true) {
                    try {
                        Integer i = queue.take();
                        System.out.println("Retrieved: " + i);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }).start();

/*            new Thread(() -> {
                try {
                    for (int i = 0; i < 10; i++) {
                        Thread.sleep(100);
                        System.out.println("Queue size = " + queue.size());
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();*/
        }
    }
}
