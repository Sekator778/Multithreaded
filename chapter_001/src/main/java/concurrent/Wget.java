package concurrent;

/**
 * System.out.print("\rLoading : " + i  + "%");
 * Метод print печатает символы в строку без перевода каретки.
 * Символ \r указывает, что каретку каждый раз нужно вернуть в начало строки.
 * Это позволяет через промежуток времени обновить строчку.
 */

public class Wget {
    public static void main(String[] args) {
        Thread first = new Thread(
                () -> {
                    try {
                        for (int i = 0; i < 100; i++) {
                            System.out.print("\rLoading : " + i + "%");
                            Thread.sleep(200);
                        }
                        System.out.print("\rLoaded.");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
        );
        first.start();
    }
}
