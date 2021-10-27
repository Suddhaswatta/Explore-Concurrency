public class Create {
    public static void main(String[] args) throws Exception {
        int max = 2;
        for (int i = 0; i < max; i++) {
            new Thread(()->{
                int n = 7;
                while(n>0){
                    System.out.println("N : "+n--);
                }
            },"My New Thread "+i)
            .start();
        }
    }
}
