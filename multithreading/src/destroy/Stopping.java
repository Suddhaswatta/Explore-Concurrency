package destroy;

class Worker implements Runnable{

    private volatile boolean terminated;


    public Worker() {
        this.terminated = false;
    }

    @Override
    public void run() {

        while(!terminated){
            try {
                System.out.println(String.format("Thread %s is terminated %s",Thread.currentThread().getName(),terminated));
                Thread.sleep(1000);
                System.out.println("Execute...");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public boolean isTerminated() {
        return terminated;
    }

    public void setTerminated(boolean terminated) {
         this.terminated = terminated;
    }

    public static void main(String[] args) {
        Worker worker = new Worker();
        Thread t1 = new Thread(worker,"Worker-Thread");

        t1.start();

        new Thread(()->{
            try {
                Thread.sleep(2000);
                worker.setTerminated(true);
                System.out.println("Thread terminated");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"Another-Thread").start();

        System.out.println(String.format("Running %s thread",Thread.currentThread().getName()));

    }
}
