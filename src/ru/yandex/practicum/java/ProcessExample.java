package ru.yandex.practicum.java;

import java.io.IOException;

public class ProcessExample {

    public static void main(String[] args) throws IOException {
        Runtime.getRuntime().exec("explorer.exe");
        System.out.println(Runtime.getRuntime().availableProcessors());
    }

}
