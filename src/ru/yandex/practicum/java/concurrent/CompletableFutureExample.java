package ru.yandex.practicum.java.concurrent;

import java.util.concurrent.CompletableFuture;

public class CompletableFutureExample {

    private static class Power {

        private final double input;

        private Power(double input) {
            this.input = input;
        }

        public Double calculate() {
            System.out.printf("%s: calculation%n", Thread.currentThread().getName());

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            return Math.pow(input, 2);
        }
    }

    public static void main(String[] args) {

        CompletableFuture<Void> f1 =
                CompletableFuture
                        .supplyAsync(() -> new Power(2).calculate())
                        .thenAccept(result ->
                                System.out.println("Result: " + result));

        CompletableFuture<Void> f2 =
                CompletableFuture
                        .supplyAsync(() -> new Power(3).calculate())
                        .thenAccept(result ->
                                System.out.println("Result: " + result));

        CompletableFuture.allOf(f1, f2).join();
    }
}