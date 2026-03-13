package org.my.edy;

public class JoinExample {
    private static int result = 0;

    public static void main(String[] args) throws InterruptedException {

        Thread worker = new Thread(() -> {
            System.out.println("Считаем...");
            result = 100; // (1) записываем результат
        });

        worker.start();
        worker.join(); // (2) ждём завершения потока
        // join() создаёт happens-before:
        // всё что было в worker ДО его завершения
        // гарантированно видно после join()

        // Гарантированно 100, НЕ 0
        System.out.println("Результат: " + result);
    }
}
