package ru.yandex.practicum.java.lab;

import java.util.concurrent.CompletableFuture;

public class UserInfoService {

    public String getUserInfo() {
        String name = getName();
        int ordersCount = getOrdersCount();
        int bonusBalance = getBonusBalance();

        return "User: " + name
                + ", orders: " + ordersCount
                + ", bonus balance: " + bonusBalance;
    }

    private String getName() {
        sleep(2000);
        return "Alice";
    }

    private int getOrdersCount() {
        sleep(2000);
        return 5;
    }

    private int getBonusBalance() {
        sleep(2000);
        return 100;
    }

    private void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        UserInfoService service = new UserInfoService();

        long start = System.nanoTime();

        System.out.println(service.getUserInfo());

        long elapsed = (System.nanoTime() - start) / 1_000_000;

        System.out.println("Execution time: " + elapsed + " ms");
    }
}