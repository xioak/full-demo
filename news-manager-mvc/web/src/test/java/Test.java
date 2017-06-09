/**
 * Created by xioa on 16/11/23.
 */
public class Test {

    private static int maxValue;

    public static void main(String[] args) {

        int i = 0;
        long ti = System.currentTimeMillis();
        long tt = ti;
        for (; ; ) {


            System.out.println(i++);
            long ct = System.currentTimeMillis();
            System.out.println(ct - ti);
            ti = ct;

            if (i > 10000) {
                System.out.println(Integer.MAX_VALUE);
                return;
            }
        }

//        long t = System.currentTimeMillis();

//        System.out.println(System.currentTimeMillis() - tt);

//        class MyThread implements Runnable {
//
//            private int i = 0;
//
//            @Override
//            public void run() {
//                System.out.println(i);
//                i++;
//
//            }
//        }
//
//
//        for (int i = 0; i < 100; i++) {
//            MyThread t = new MyThread();
//            new Thread(t).start();
//            new Thread(t).start();
//        }


    }


}
