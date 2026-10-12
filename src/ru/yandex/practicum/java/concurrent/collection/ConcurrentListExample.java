package ru.yandex.practicum.java.concurrent.collection;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

public class ConcurrentListExample {

    public static void main(String[] args) throws InterruptedException {
        List<Integer> list =
//                new ArrayList<>();
//                Collections.synchronizedList(new ArrayList<>());
//                new Vector<>();
                new CopyOnWriteArrayList<>();

        Runnable add = () -> {
            for (int i = 0; i < 100; i++) {
                list.add(i);
            }
        };

        Thread t0 = new Thread(add);
        Thread t1 = new Thread(add);


        t0.start();
        t1.start();

        t0.join();
        t1.join();

        System.out.println(list.size());
    }
}
