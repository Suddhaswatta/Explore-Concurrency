package async.blocking.callback;
import static java.lang.String.format;

public class Main {


    public static void main(String[] args) {

        System.out.println("=========================== SYNC ========================================");
        System.out.println(format("Execution started on %s Thread", Thread.currentThread().getName()));

        Double a = 2.0,b=0.0;

        try {
            Double res = MathOperation.syncDivide(a,b);
            System.out.println("Output "+ res);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(format("Execution ended on %s Thread", Thread.currentThread().getName()));

        System.out.println("========================= ASYNC ========================================");
        System.out.println(format("Execution started on %s Thread", Thread.currentThread().getName()));

        MathOperation.asyncDivide(a,b, (e, res) -> {
            if (e != null) {
                System.out.println(e.getMessage());
            } else {
                System.out.println("Result " + res);
            }
        });
        System.out.println(format("Execution ended on %s Thread", Thread.currentThread().getName()));



    }
}