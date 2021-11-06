package locking;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class Sync {

    int LOWER_LIMIT = 0;
    int UPPER_LIMT = 5;
    Object lock = new Object();
    List<Integer> lst = new ArrayList<>();

    public void producer() throws InterruptedException{
        synchronized (lock) {
            while (true) {
                if (lst.size() == UPPER_LIMT) {
                    System.out.println(String.format("Waiting for the consumer"));
                    lock.wait();
                } else {
                    for (int i = 0; i < UPPER_LIMT; i++) {
                        lst.add(i);
                        Thread.sleep(500);
                        System.out.println("Producing Elements : "+lst.toString());
                    }


                    lock.notify();
                }
            }
        }
    }

    public void consumer() throws InterruptedException {
        synchronized (lock) {
            while (true) {
                if (lst.size() == LOWER_LIMIT) {
                    System.out.println(String.format("Waiting for the producer"));
                    lock.wait();
                } else {
                    for (int i = 0; i < UPPER_LIMT; i++) {
                        lst.remove(lst.size()-1);
                        Thread.sleep(200);
                        System.out.println("Draining producer"+lst.toString());
                    }
                    lock.notify();
                }
            }
        }
    }

    public  void process() {
        Thread t = new Thread(()->{
            try {
                producer();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        Thread t1 = new Thread(()->{
            try {
                consumer();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });


        t1.start();
        t.start();
    }

    public static void main(String[] args) {
        Sync sync = new Sync();
        sync.process();
    }


}
