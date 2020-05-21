package net;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;

public class FileInput implements Runnable {
    private final String url;
    private final int delay;

    public FileInput(String url, int delay) {
        this.url = url;
        this.delay = delay;
    }

    @Override
    public void run() {
        int x;
        try (BufferedInputStream in = new BufferedInputStream(new URL(url).openStream());
             FileOutputStream fileOutputStream = new FileOutputStream("pom_tmp.xml")) {
            byte[] dataBuffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = in.read(dataBuffer, 0, 1024)) != -1) {
                System.out.println("size bytesRead: " + bytesRead);
                x = bytesRead / delay;
                System.out.println("size delay: " + x);
                Thread.sleep(x * 1000);
                fileOutputStream.write(dataBuffer, 0, bytesRead);
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }

    }
}
