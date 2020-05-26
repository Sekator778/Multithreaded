package deadlock;

public class DeadLockDemo { /* * This method request two locks, first String and then Integer */
    public void method1() throws InterruptedException {
        synchronized (String.class) {
            System.out.println("Aquired lock on String.class object");
            Thread.sleep(10);
            synchronized (Integer.class) {
                System.out.println("Aquired lock on Integer.class object");
            }
        }
    } /* * This method also requests same two lock but in exactly * Opposite order i.e. first Integer and then String. * This creates potential deadlock, if one thread holds String lock * and other holds Integer lock and they wait for each other, forever. */

    public void method2() {
        synchronized (Integer.class) {
            System.out.println("Aquired lock on Integer.class object");
            synchronized (String.class) {
                System.out.println("Aquired lock on String.class object");
            }
        }
    }

    public static void main(String[] args) {
        DeadLockDemo demo = new DeadLockDemo();
        Thread first = new Thread(
                () -> {
                    try {
                        demo.method1();
                    } catch (Exception ignored) {

                    }
                }
        );
        Thread second = new Thread(
                demo::method2
        );
        first.start();
        second.start();
    }
}