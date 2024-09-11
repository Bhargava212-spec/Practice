package com.test;


import java.util.concurrent.Exchanger;

public class ExchangeTest {

    public static void main(String[] args) {


        Exchanger<String> exchanger = new Exchanger<>();

        Runnable producer1 = () -> {

            try {
                var producer = "Data from producer";
                var consumer = exchanger.exchange(producer);
                System.out.println(STR."Producer received: \{consumer}");
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        };

        Runnable consumer = () -> {
            try {
                String consumedData = "Data from Consumer";
                String receivedData = exchanger.exchange(consumedData);
                System.out.println(STR."Consumer received: \{receivedData}");
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        };

        new Thread(producer1).start();
        new Thread(consumer).start();
    }


}
