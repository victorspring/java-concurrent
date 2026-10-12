package ru.yandex.practicum.java;

import java.util.Scanner;

public class ThreadLab1 {

    public static class Counter extends Thread {

        private int n;

        public Counter(int n){
            this.n = n;
        }

        @Override
        public void run() {
            for (int i = 1; i <= n; i++) {
                System.out.printf("%s: %d%n", Thread.currentThread().getName(), i);
            }
        }
    }

    public static void main(String[] args) {

        try (Scanner scanner = new Scanner(System.in)){
            int n1 = Integer.parseInt(scanner.nextLine());
            int n2 = Integer.parseInt(scanner.nextLine());

            new Counter(n1).start();
            new Counter(n2).start();
        }

    }
}
