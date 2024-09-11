package com.test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

public class SemaphoreExample {

    Semaphore semaphore = new Semaphore(3);

    public void performTask() {
        try {
            semaphore.acquire();
            System.out.println(STR."\{Thread.currentThread().getName()} acquired a permit.");
            Thread.sleep(10); // Simulate some work
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } finally {
            semaphore.release();
        }
    }

    public static void main(String[] args) {
        SemaphoreExample example = new SemaphoreExample();

        Runnable r = example::performTask;

        ExecutorService executorService = Executors.newFixedThreadPool(8);
        for (var i = 0; i < 50; i++) {
            executorService.submit(r);
        }
        executorService.shutdown();
    }
}
