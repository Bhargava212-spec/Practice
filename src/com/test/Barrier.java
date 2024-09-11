package com.test;

import java.util.concurrent.CyclicBarrier;

public class Barrier {


    public static void main(String[] args) {
        CyclicBarrier barrier = new CyclicBarrier(3, new Test10());

        Runnable task = () -> {
            System.out.println(STR."\{Thread.currentThread().getName()} is waiting at barrier");
            try {
                barrier.await();
            } catch (Exception _) {
                Thread.currentThread().interrupt();
            }
            System.out.println(STR."\{Thread.currentThread().getName()} is crossed the barrier");
        };

        new Thread(task, "task1").start();
        new Thread(task, "task2").start();
        new Thread(task, "task3").start();


    }
}


class Test10 implements Runnable {

    @Override
    public void run() {
        System.out.println("Threads reached the barrier");
    }
}