package ru.yandex.practicum.java.concurrent.forkjoin.tree;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class Node {

    private long weight;
    private List<Node> children = Collections.emptyList();

    public Node (long weight){
        this.weight = weight;
    }

    public Collection<Node> getChildren(){
        return children;
    }

    public long getWeight(){
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public void setChildren(List<Node> children) {
        this.children = children;
    }

    @Override
    public String toString() {
        return toString(0);
    }

    private String toString(int level){
        StringBuilder sb = new StringBuilder();

        sb = sb.append("\t".repeat(Math.max(0, level))).append(weight).append("\n");

        for (Node n : children) {
            sb.append(n.toString(level + 1));
        }

        return sb.toString();
    }
}