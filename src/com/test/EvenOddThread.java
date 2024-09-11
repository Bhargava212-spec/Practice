package com.test;


public class EvenOddThread {

    private static final int max = 500;
    private static int number = 1;
    private static final Object lock = new Object();


    public static void main(String[] args) {
        Thread oddThread = new Thread(new OddPrinter());
        Thread evenThread = new Thread(new EvenThread());

        oddThread.start();
        evenThread.start();
    }

    static class EvenThread implements Runnable {
        @Override
        public void run() {
            while (true) {
                synchronized (lock) {
                    while (number % 2 != 0) {
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                        }
                    }
                    if (number > max) {
                        lock.notify();
                        break;
                    }
                    System.out.println(STR."Even: \{number}");
                    number++;
                    lock.notify();
                }
            }
        }
    }

    static class OddPrinter implements Runnable {

        @Override
        public void run() {
            while (true) {
                synchronized (lock) {
                    while (number % 2 == 0) {
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                        }
                    }
                    if (number > max) {
                        lock.notify();
                        break;
                    }
                    System.out.println(STR."Odd: \{number}");
                    number++;
                    lock.notify();
                }
            }
        }
    }
}
