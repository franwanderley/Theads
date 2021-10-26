public class MinhaThread extends Thread {
   private Integer id;
   private String linha;
   private Integer tempo;
   private Integer qtdVogais;
   private String filename;

   public MinhaThread(Integer id, String linha, Integer tempo, String filename) {
      this.id = id;
      this.linha = linha;
      this.tempo = tempo;
      this.qtdVogais = 0;
      this.filename = filename;
   }

   public String getFilename() {
      return filename;
   }

   public Integer getQtdVogais(){
      return qtdVogais;
   }

   @Override
   public void run(){
      try {
         Thread.sleep(tempo);
      } catch (InterruptedException e) {
         e.printStackTrace();
      }
      String vogais = linha.toLowerCase().replaceAll("[^aeiou]", "");
      qtdVogais += vogais.length();
      System.out.printf("Thead "+ id +" linha = "+ linha +" %s\n", vogais.length());
   }
}