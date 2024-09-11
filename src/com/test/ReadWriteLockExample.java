package com.test;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReadWriteLockExample {

    private final ReadWriteLock lock = new ReentrantReadWriteLock();
    private int val;

    public void read() {
        lock.readLock().lock();
        System.out.println(STR."\{Thread.currentThread().getName()} read Value \{val}");
        lock.readLock().unlock();
    }

    public void write(int val) {
        this.val = val;
        lock.writeLock().lock();
        System.out.println(STR."\{Thread.currentThread().getName()} write Value \{val}");
        lock.writeLock().unlock();
    }

    public static void main(String[] args) {
        ReadWriteLockExample example = new ReadWriteLockExample();

        Runnable readTask = example::read;
        Runnable writeTask = () -> example.write(42);

        new Thread(readTask, "Reader 1").start();
        new Thread(writeTask, "Writer").start();
        new Thread(readTask, "Reader 2").start();

    }

}
