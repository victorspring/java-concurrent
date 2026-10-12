package ru.yandex.practicum.java;

public class SimpleDeadlock {

    public static void main(String[] args) throws InterruptedException {
        Thread.currentThread().join();
    }
}
