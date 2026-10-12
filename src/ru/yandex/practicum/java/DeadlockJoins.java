package ru.yandex.practicum.java;

public class DeadlockJoins {

    private static class Counter extends Thread {

        private Thread otherThread;

        public void setOtherThread(Thread otherThread) {
            this.otherThread = otherThread;
        }

        @Override
        public void run() {
            try {
                otherThread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            for (int i = 1; i <= 1000; i++) {
                System.out.printf("%s: %d%n", Thread.currentThread().getName(), i);
            }
        }
    }

    public static void main(String[] args) {
        Counter c1 = new Counter();
        Counter c2 = new Counter();

        c1.setOtherThread(c2);
        c2.setOtherThread(c1);

        c1.start();
        c2.start();
    }
}
