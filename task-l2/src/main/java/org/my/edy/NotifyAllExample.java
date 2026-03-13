package org.my.edy;

public class NotifyAllExample {
    private static final Object lock = new Object();

    public static void main(String[] args) throws InterruptedException {

        Runnable task = () -> {
            synchronized (lock) {
                try {
                    System.out.println(Thread.currentThread().getName() + " ждёт");
                    lock.wait();
                    System.out.println(Thread.currentThread().getName() + " проснулся");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        new Thread(task, "Поток-1").start();
        new Thread(task, "Поток-2").start();
        new Thread(task, "Поток-3").start();

        Thread.sleep(500);

        synchronized (lock) {
            System.out.println("Будим ВСЕХ");
            lock.notifyAll(); // разбудит все три потока
        }
    }
}
