package io;

import java.io.*;

/**
 *
 */

public class ParseFile {
    private File file;

    public synchronized void setFile(File f) {
        file = f;
    }

    public synchronized File getFile() {
        return file;
    }

    public String getContent() {
        StringBuilder output = new StringBuilder();
        int data;
        synchronized (this) {
            try (BufferedInputStream i = new BufferedInputStream(new FileInputStream(file))) {
                while ((data = i.read()) != -1) {
                    output.append((char) data);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return output.toString();
    }

    public String getContentWithoutUnicode() throws IOException {
        StringBuilder output = new StringBuilder();
        int data;
        synchronized (this) {
            try (BufferedInputStream i = new BufferedInputStream(new FileInputStream(file))) {
                while ((data = i.read()) != -1) {
                    if (data < 0x80) {
                        output.append((char) data);
                    }
                }
            }
            return output.toString();
        }
    }

    public synchronized void saveContent(String content) {
        try (BufferedOutputStream bw = new BufferedOutputStream(new FileOutputStream(file))) {
            byte[] bytes = content.getBytes();
            bw.write(bytes, 0, bytes.length);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}