package create;

public class TwoThreads {

    private static long counter;

    private static int LOOP = 5;

    public static synchronized void inc() {
        System.out.println(String.format("Thread %s has called inc method ... \n", Thread.currentThread().getName()));
        counter++;
    }

    public static void process() {

        Thread t1 = new Thread(() -> {
            System.out.println(String.format("Thread %s has started running ... \n", Thread.currentThread().getName()));
            for (int i = 0; i < LOOP; i++) {
                inc();

            }
            System.out.println(String.format(
                    "Thread %s has stopped ... with value %d\n", Thread.currentThread().getName(),
                    counter
            ));
        }, "Thread-1");

        Thread t2 = new Thread(() -> {
            System.out.println(
                    String.format(
                            "Thread %s has started running ... \n",
                            Thread.currentThread().getName()
                    )
            );
            for (int i = 0; i < LOOP; i++) {
                inc();
            }
            System.out.println(
                    String.format("Thread %s has stopped ... with value %d\n",
                            Thread.currentThread().getName(),
                            counter));
        }, "Thread-2");

        t1.start();
        t2.start();


    }

    public static void main(String[] args) {
        process();
        System.out.println(counter);
    }
}

    

