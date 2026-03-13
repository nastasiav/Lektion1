package org.my.edy;

public class SynchronizedExample {
    private static int counter = 0;

    public static synchronized void increment() {
        counter++;
    }

    public static synchronized int getCounter() {
        return counter;
    }

    public static void main(String[] args) throws InterruptedException {

        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 1000; i++) increment();
        });

        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 1000; i++) increment();
        });

        t1.start();
        t2.start();
        t1.join();
        t2.join();

        // Без synchronized мог быть НЕ 2000 — гонка данных
        // С synchronized — гарантированно 2000
        System.out.println("Итог: " + getCounter()); // всегда 2000
    }
}
