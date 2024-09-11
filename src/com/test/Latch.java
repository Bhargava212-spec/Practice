package com.test;

import java.util.concurrent.CountDownLatch;

public class Latch {

    public static void main() {

        CountDownLatch latch = new CountDownLatch(3);


        Runnable r = () -> {
            System.out.println(STR."\{Thread.currentThread().getName()} is working");
            latch.countDown();
        };

        new Thread(r, "test1").start();
        new Thread(r, "test2").start();
        new Thread(r, "test3").start();

        try {
            latch.await();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        System.out.println("completed");
    }


}
