package com.test;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class ProducerConsumer {
    private static final int BUFFER_SIZE = 10;


    public static void main() {
        final BlockingQueue<Integer> queue = new ArrayBlockingQueue<>(BUFFER_SIZE);
        new Thread(new Producer(queue)).start();
        new Thread(new Consumer(queue)).start();
    }


    static class Producer implements Runnable {

        private final BlockingQueue<Integer> queue;

        Producer(BlockingQueue<Integer> queue) {
            this.queue = queue;
        }

        @Override
        public void run() {
            int val = 0;
            while (true) {
                synchronized (queue) {
                    while (BUFFER_SIZE == queue.size()) {
                        try {
                            queue.wait();
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                        }
                    }
                    queue.add(val++);
                    System.out.println(STR."Produced: \{val}");
                    queue.notify();
                }

            }

        }
    }

    static class Consumer implements Runnable {

        private final BlockingQueue<Integer> queue;

        Consumer(BlockingQueue<Integer> queue) {
            this.queue = queue;
        }

        @Override
        public void run() {

            while (true) {
                synchronized (queue) {
                    while (queue.isEmpty()) {
                        try {
                            queue.wait();
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                        }
                    }

                    try {
                        var val = queue.take();
                        System.out.println(STR."Consumed:  \{val}");
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                    queue.notify();
                }
            }

        }
    }
}
