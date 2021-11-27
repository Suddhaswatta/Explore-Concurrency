package async.blocking.callback;

import static java.lang.String.format;

public class MathOperation {

    public static Double syncDivide(Double a, Double b) throws InterruptedException {
        System.out.println(format("Division is running on %s thread",Thread.currentThread().getName()));
        long start = System.currentTimeMillis();

        Thread.sleep(1000);
        Double res = a / b;
        long end = System.currentTimeMillis();
        System.out.println(format("Division took %d seconds ", (end - start)));
        return res;
    }

    public static void asyncDivide(Double a, Double b, Callback<Double> callback) {

        new Thread(() -> {
            try {
                Double res = syncDivide(a, b);
                callback.callback(null, res);
            } catch (InterruptedException e) {
                callback.callback(e, null);
            }


        }).start();

    }

}