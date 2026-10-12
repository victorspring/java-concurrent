package ru.yandex.practicum.java.concurrent.executor;

import java.util.concurrent.*;

public class ExecutorExample {

    private static class Power implements Callable<Double> {

        private double input;

        private Power(double input) {
            this.input = input;
        }

        @Override
        public Double call() throws Exception {
            System.out.printf("%s: calculation%n", Thread.currentThread().getName());
            Thread.sleep(1000);
            return Math.pow(input, 2);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ExecutorService pool = Executors.newVirtualThreadPerTaskExecutor();
        Future<Double> res1 = pool.submit(new Power(2));
        Future<Double> res2 = pool.submit(new Power(3));
        pool.shutdown();

        try {
            while (!res1.isDone() || !res2.isDone()) {
                System.out.println("Waiting for results...");
                Thread.sleep(100);
            }
            System.out.println("Result 1: " + res1.get());
            System.out.println("Result 2: " + res2.get());


        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

    }
}
