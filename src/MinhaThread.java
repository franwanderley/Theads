import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class MinhaThread extends Thread {
   private Integer id;
   private File arquivo;
   private Integer tempo;
   private Integer qtdVogais;

   public MinhaThread(Integer id, File arquivo, Integer tempo) {
      this.id = id;
      this.arquivo = arquivo;
      this.tempo = tempo;
      this.qtdVogais = 0;
   }
   public MinhaThread(){
      this.qtdVogais = 0;
      this.tempo = 0;
   }

   public void setId(Integer id){
      this.id = id;
   }
   public void setArquivo(File arquivo){
      this.arquivo = arquivo;
   }
   public void setTempo(Integer tempo){
      this.tempo = tempo;
   }

   public String getFilename() {
      return arquivo.getName();
   }

   public Integer getQtdVogais(){
      return qtdVogais;
   }

   @Override
   public synchronized void run(){
      try {
         Thread.sleep(tempo);
         BufferedReader lerArq = new BufferedReader( new FileReader(arquivo) );
         String linha = lerArq.readLine();
         while (linha != null) {
            String vogais = linha.toLowerCase().replaceAll("[^a|e|i|o|u]","");
            qtdVogais += vogais.length();
            linha = lerArq.readLine();
         }
         System.out.println("Thread "+ id +" Arquivo:  "+ arquivo +" vogais "+ qtdVogais);
         lerArq.close();
      } catch (Exception e) {
         System.out.println("Error: " + e.getMessage());
      }

   }

}