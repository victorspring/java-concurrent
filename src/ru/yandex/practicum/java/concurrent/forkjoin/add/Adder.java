package ru.yandex.practicum.java.concurrent.forkjoin.add;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.RecursiveTask;

import static ru.yandex.practicum.java.concurrent.forkjoin.add.App.STEP;


public class Adder extends RecursiveTask<Long> {

    private long from;
    private long to;

    public Adder(long from, long to) {
        this.from = from;
        this.to = to;
    }

    @Override
    protected Long compute() {
        long sum = 0;
        long range = to - from + 1;

        if (range <= STEP){
            return sum(from, to);
        }

        List<Adder> subTasks = new LinkedList<>();

        long nTasks = range/STEP;
        long r = range % nTasks;
        for (int i = 0; i < nTasks; i++) {
            long a = i*STEP + 1;
            long b = i*STEP + STEP + (i == nTasks - 1 ? r : 0);
            Adder task = new Adder(a, b);
            task.fork();
            subTasks.add(task);
        }

        for(Adder task : subTasks) {
            sum += task.join();
        }

        return sum;
    }

    private long sum(long from, long to){
        long sum = 0;
        for (long i = from; i <= to ; i++) {
            sum += i;
        }
        return sum;
    }
    
}