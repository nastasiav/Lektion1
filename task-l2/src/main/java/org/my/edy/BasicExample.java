package org.my.edy;

public class BasicExample {
    private static final Object lock = new Object();

    public static void main(String[] args) throws InterruptedException {

        Thread waiter = new Thread(() -> {
            synchronized (lock) {
                System.out.println("Поток ждёт...");
                try {
                    lock.wait(); // отпускает lock и засыпает
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Поток проснулся!");
            }
        });

        Thread notifier = new Thread(() -> {
            synchronized (lock) {
                System.out.println("Сейчас разбудим...");
                lock.notify(); // будит один ожидающий поток
            }
        });

        waiter.start();
        Thread.sleep(500); // даём waiter-у успеть заснуть
        notifier.start();
    }
}