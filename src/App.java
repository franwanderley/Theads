import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        Scanner ler = new Scanner(System.in);
        Integer n = ler.nextInt();
        ler.close();
        
        List<ContArquivos> vogaisPorArquivo = new ArrayList<ContArquivos>();
        List<MinhaThread> threads = new ArrayList<MinhaThread>();
        File folder = new File("src/files");
        for (File file : folder.listFiles()) {

            ContArquivos contArquivos = new ContArquivos();
            contArquivos.setFilename(file.getName());
            vogaisPorArquivo.add(contArquivos);

            BufferedReader lerArq = new BufferedReader(new FileReader(file));
            String linha = lerArq.readLine();
            while (linha != null) {
                for (int i = 0; i < n; i++) {
                    MinhaThread thread = new MinhaThread(i+1, linha, 0, file.getName());
                    thread.start();
                    threads.add( thread );
                    if (i + 1 != n)
                        linha = lerArq.readLine();
                }
                
                linha = lerArq.readLine();
            }
            lerArq.close();

            
        }
        Integer vogais =  0;
        for (MinhaThread minhaThread : threads) {
            minhaThread.join(); // esperar terminar a thread;
            vogais += minhaThread.getQtdVogais();
            int i = 0;
            for(ContArquivos count : vogaisPorArquivo){
                if(count.getFilename().equals(minhaThread.getFilename())){
                    ContArquivos newCont = count;
                    newCont.setQtd(count.getQtd() + minhaThread.getQtdVogais());
                    vogaisPorArquivo.set(i, newCont);
                }
                i++;
            }
        }
        print(vogais, vogaisPorArquivo);
        System.out.println("Total "+ vogais);

    }
    public static void print(Integer vogais, List<ContArquivos> vogaisPorArquivo) throws IOException {
        File newFile = new File("src/resultado.txt");
        if(!newFile.exists())
        newFile.createNewFile();
        BufferedWriter write = new BufferedWriter( new FileWriter(newFile) );
        for (ContArquivos cont : vogaisPorArquivo) {
            write.write("Arquivo: "+ cont.getFilename() +" Total = "+ cont.getQtd()+ "\n");            
        }
        write.write("Total de Vogais "+ vogais);
        write.close();
    }
}
