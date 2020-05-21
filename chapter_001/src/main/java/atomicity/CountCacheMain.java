//package atomicity;
//
///**
// *
// */
//
//public class CountCacheMain {
//    public static void main(String[] args) throws InterruptedException {
//        Cache cache = new Cache();
//        Thread first = null;
//        Thread second = null;
//
//        for (int i = 0; i < 10000; i++) {
//            first = new Thread(
//                    cache::instOf
//            );
//            second = new Thread(
//                    cache::instOf
//            );
//
//            first.start();
//            second.start();
//            first.join();
//            second.join();
//        }
//    }
//}
