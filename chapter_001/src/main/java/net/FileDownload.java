package net;

/**
 * консольная программа.
 *
 * Программа скачивает файл из сети с ограничением по скорости скачки.
 *
 * Например,
 * java -jar wget.jar url 200
 * wget (ссылка) (скорость в килобайтах в секунту)
 */

public class FileDownload {
    public static void main(String[] args) {
        String url = args[0];
        int speed = Integer.parseInt(args[1]);
        try {
            Thread download = new Thread(new InputStream(url, speed));
            download.start();
            download.join();
        } catch (Exception e) {
            System.out.println("set correct args: wget (ссылка) (скорость в килобайтах в секунду)");
        }
    }
}