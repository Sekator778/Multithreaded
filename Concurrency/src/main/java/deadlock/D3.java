package deadlock;

import java.util.Collections;
import java.util.HashMap;
import java.util.Random;

class Egg extends Thread {
    @Override
    public void run() {

        for (int i = 0; i < 5; i++) {
            try {
                // Приостанавливаем поток
                sleep(D3.getTimeSleep());
                System.out.println("Яйцо");
            } catch (InterruptedException ignored) {
            }
        }
    }
}

public class D3 {
    public static int getTimeSleep() {
        final Random random = new Random();
        int tm = random.nextInt(1000);
        if (tm < 10) {
            tm *= 100;
        } else if (tm < 100) {
            tm *= 10;
        }
        return tm;
    }

    public static void main(String[] args) {
        Egg egg = new Egg(); // Создание потока
        System.out.println(
                "Начинаем спор : кто появился первым ?");

        egg.start(); // Запуск потока
        for (int i = 0; i < 5; i++) {
            try {
                // Приостанавливаем поток
                Thread.sleep(D3.getTimeSleep());
                System.out.println("Курица");
            } catch (InterruptedException ignored) {
            }
        }
        if (egg.isAlive()) {
            // Cказало ли яйцо последнее слово?
            try {
                // Ждем, пока яйцо закончит высказываться
                egg.join();
            } catch (InterruptedException ignored) {
            }

            System.out.println("Первым появилось яйцо !!!");
        } else {
            //если оппонент уже закончил высказываться
            System.out.println("Первой появилась курица !!!");
        }
        System.out.println("Спор закончен");
    }
}