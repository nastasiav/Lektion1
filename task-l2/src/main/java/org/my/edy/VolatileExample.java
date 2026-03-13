package org.my.edy;

public class VolatileExample {
    private static volatile boolean ready = false; // volatile!
    private static volatile int value = 0;

    public static void main(String[] args) throws InterruptedException {

        Thread reader = new Thread(() -> {
            while (!ready) {}
            // Гарантированно напечатает 42
            // Запись value=42 случилась ДО записи ready=true
            // Чтение ready=true случилось ДО чтения value
            System.out.println("Значение: " + value);
        });

        Thread writer = new Thread(() -> {
            value = 42;       // (1)
            ready = true;     // (2) volatile-запись
            // happens-before любого чтения ready
        });

        reader.start();
        Thread.sleep(100);
        writer.start();
    }
}
