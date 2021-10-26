public class Deadlocks {
   public static void main(String[] args) throws Exception{
      final String RECURSO1 = "Recurso 1";
      final String RECURSO2 = "Recurso 2";
      new Thread(){
         public void run() {
            synchronized (RECURSO1){
               System.out.println(RECURSO1);
            };
            try {
               Thread.sleep(100);
            } catch (InterruptedException e) {
               e.printStackTrace();
            }
            synchronized (RECURSO2){
               System.out.println(RECURSO2);
            };
         }
      }.start();
      new Thread(){
         public void run() {
            synchronized (RECURSO2){
               System.out.println(RECURSO2);
            };
            try {
               Thread.sleep(100);
            } catch (InterruptedException e) {
               e.printStackTrace();
            }
            synchronized (RECURSO1){
               System.out.println(RECURSO1);
            };
         }
      }.start();
   }
}
