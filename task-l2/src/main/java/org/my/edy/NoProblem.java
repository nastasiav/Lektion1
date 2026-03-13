package org.my.edy;

public class NoProblem {
    private static boolean ready = false;
    private static int value = 0;

    public static void main(String[] args) throws InterruptedException {

        Thread reader = new Thread(() -> {
            while (!ready) {
                // крутимся в ожидании
                System.out.println(value);
            }
            // Может напечатать 0! Хотя writer записал 42
            System.out.println("Значение: " + value);
        });

        Thread writer = new Thread(() -> {
            System.out.println("Writer start");
            ready = true; // НЕТ гарантии что reader увидит это изменение
            value = 42;
            System.out.println("Writer end");
        });

        reader.start();
        Thread.sleep(100);
        writer.start();
    }
}
