package ru.yandex.practicum.java.concurrent.forkjoin.tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ForkJoinPool;

public class App {

    private static int total = 0;

    public static void main(String[] args) {
        Node root = generateRandomTree(20);
//        System.out.println(root);
        long sum = ForkJoinPool.commonPool().invoke(new NodeSummer(root));

        System.out.println(total);
        System.out.println(sum);

    }

    private static Node generateRandomTree(int level){
        Random rnd = new Random();
        long weight = rnd.nextInt(100);
        total += weight;
        Node root = new Node(weight);

        if (level == 0)
            return root;

        int nChildren = rnd.nextInt(3 ) + 1;
        List<Node> children = new ArrayList<>();
        for (int i = 0; i < nChildren; i++) {
            children.add(generateRandomTree(level - 1));
        }

        root.setChildren(children);
        return root;
    }
}
