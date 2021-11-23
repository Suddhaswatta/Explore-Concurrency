package locking;

import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import static java.lang.String.*;

class Worker {

    private  Lock lock  ;
    private  Condition condition ;

    public Worker() {
        this.lock = new ReentrantLock(true);
        this.condition = lock.newCondition();
    }

    public  void producer() throws InterruptedException{

        while (true) {
            lock.lock();

            Thread.sleep(1000);
            System.out.println(format("Running on %s ", Thread.currentThread().getName()));
            condition.await();
            System.out.println(format("Again Running on %s ", Thread.currentThread().getName()));
            lock.unlock();

        }
    }
    public  void consume() throws InterruptedException{

        while(true) {

            lock.lock();
            Thread.sleep(1000);
            System.out.println(format("Running on %s ", Thread.currentThread().getName()));
            condition.signal();
            System.out.println(format("Signalled sent from %s ", Thread.currentThread().getName()));
            lock.unlock();
        }

    }


}
public class RLock{
    public static void main(String[] args) {

        Worker worker = new Worker();

        new Thread(()->{
            try {
                worker.producer();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"Producer-Thread").start();

        new Thread(()->{
            try {
                worker.consume();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "Consumer-Thread").start();
    }
}