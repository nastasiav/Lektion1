package org.my.edy;

public class ProducerConsumer {
    private static final Object lock = new Object();
    private static String data = null;

    public static void main(String[] args) {

        // Потребитель
        Thread consumer = new Thread(() -> {
            synchronized (lock) {
                while (data == null) { // while, не if!
                    try {
                        System.out.println("Нет данных, жду...");
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println("Получил данные: " + data);
            }
        });

        // Производитель
        Thread producer = new Thread(() -> {
            try { Thread.sleep(1000); } catch (InterruptedException e) {}
            synchronized (lock) {
                data = "Привет!";
                System.out.println("Данные готовы, будим потребителя");
                lock.notify();
            }
        });

        consumer.start();
        producer.start();
    }
}
