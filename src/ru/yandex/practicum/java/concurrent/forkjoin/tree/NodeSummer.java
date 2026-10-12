package ru.yandex.practicum.java.concurrent.forkjoin.tree;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.RecursiveTask;

public class NodeSummer extends RecursiveTask<Long> {

    private final Node node;
    
    public NodeSummer(Node node) {
        this.node = node;
    }

    @Override
    protected Long compute() {
        List<NodeSummer> subTasks = new LinkedList<>();
        
        for(Node child : node.getChildren()) {
            NodeSummer task = new NodeSummer(child);
            task.fork();
            subTasks.add(task);
        }

        long sum = node.getWeight();
        for(NodeSummer task : subTasks) {
            sum += task.join();
        }
        
        return sum;
    }
    
}