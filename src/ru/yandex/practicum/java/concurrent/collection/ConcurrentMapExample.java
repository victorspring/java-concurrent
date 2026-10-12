package ru.yandex.practicum.java.concurrent.collection;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class ConcurrentMapExample {

    public static void main(String[] args) throws InterruptedException {
        Map<Integer, Integer> map =
//                new HashMap<>();
//                Collections.synchronizedMap(new HashMap<>());
//                new Hashtable<>();
                new ConcurrentHashMap<>();


        Runnable mapPut = () -> {
            for (int i = 0; i < 1000_000; i++) {
                map.put(i, 1);
            }
        };

        Thread t0 = new Thread(mapPut);
        Thread t1 = new Thread(mapPut);

        t0.start();
        t1.start();

        t0.join();
        t1.join();

        System.out.println(map.size());
    }
}
