package ru.yandex.practicum.java;

import java.util.Scanner;

public class ThreadLab2 {

    public static void main(String[] args) {

        try (Scanner scanner = new Scanner(System.in)){
            int n1 = Integer.parseInt(scanner.nextLine());
            int n2 = Integer.parseInt(scanner.nextLine());

            new Thread(getRunnable(n1)).start();
            new Thread(getRunnable(n2)).start();
        }
    }

    private static Runnable getRunnable(int n){
        return () -> {
            for (int i = 1; i <= n; i++) {
                System.out.printf("%s: %d%n", Thread.currentThread().getName(), i);
            }
        };
    }
}
