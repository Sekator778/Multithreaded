package net;


public class FileDownload {
    public static void main(String[] args) throws Exception {
        String url = args[0];
        int speed = Integer.parseInt(args[1]);
        Thread input = new Thread(new FileInput(url, speed));
        input.start();
        while ((input.getState() != Thread.State.TERMINATED)) {
            Thread.sleep(1000);
        }
    }
}