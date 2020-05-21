package net;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;

/**
 * поток для скачивания
 * принимает адрес и скорость максимально допустимую для скачивания
 * алгоритм следующий:
 * засекаем время, скачиваем в буфер если накачали больше допустимого ждем до времени 1й секунда
 */

public class InputStream implements Runnable {
    private final String url;
    private final int speed;

    public InputStream(String url, int speed) {
        this.url = url;
        this.speed = speed;
    }

    @Override
    public void run() {
        System.out.println("Speed download: " + speed + " kB/sek");
        int sum = 0;
        int buffSize = 0;
        long time = System.currentTimeMillis();
        try (BufferedInputStream in = new BufferedInputStream(new URL(url).openStream());
             FileOutputStream fileOutputStream = new FileOutputStream("pom_tmp.txt")) {
            byte[] dataBuffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = in.read(dataBuffer, 0, 1024)) != -1) {
                if (Thread.currentThread().isInterrupted()) {
                    break;
                }
                sum += bytesRead;
                buffSize += bytesRead;                              /* размер для индикации сколько загрузили */
                fileOutputStream.write(dataBuffer, 0, bytesRead);
                if (sum >= speed) {                                     /* если загрузили больше установленного размера */
                    if ((System.currentTimeMillis() - time) < 1000L) { /* и прошло времени меньше секунды */
                        try {
                            Thread.sleep(1000L - (System.currentTimeMillis() - time)); /* поспи плиз до 1й секунды */
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                        }
                    }
                    System.out.printf(" %d kb loaded \r", buffSize / 1000);
                    sum = 0;                                        /* обнуляем все на след заход */
                    time = System.currentTimeMillis();
                }
            }
            System.out.printf(" %d kb all download", buffSize / 1000);
        } catch (IOException e) {
           e.printStackTrace();
        }
    }
}
