package ru.yandex.practicum.java.concurrent.collection;

import java.util.Queue;
import java.util.Scanner;
import java.util.concurrent.ConcurrentLinkedQueue;

public class NonBlockingQueueExample {

    public static void main(String[] args) {
        Queue<Integer> queue = new ConcurrentLinkedQueue<>();

        System.out.println("Enter the number:");

        try (Scanner scanner = new Scanner(System.in)) {

            int n = scanner.nextInt();

            new Thread(() -> {
                for (int i = 1; i <= n; i++) {
                    queue.offer(i);
                    System.out.println("Added: " + i);
                }
            }).start();

            new Thread(() -> {
                while (true) {
                    Integer i = queue.poll();
                    if (i != null) {
                        System.out.println("Retrieved: " + i);
                    } else {
                        System.out.println("No elements found");
                        try {
                            Thread.sleep(1000L);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }).start();

        }
    }
}
