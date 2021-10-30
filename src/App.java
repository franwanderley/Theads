import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        Scanner ler = new Scanner(System.in);
        System.out.println("Diretorio");
        String diretorio = ler.nextLine();
        System.out.println("Numeros de processos: ");
        Integer n = ler.nextInt();
        ler.close();
        if(n == 0)
            throw new Exception("Numero de processos não pode ser 0");

        List<MinhaThread> threads = new ArrayList<MinhaThread>();
        File folder = new File(diretorio);
        if(!folder.exists() || !folder.isDirectory())
            throw new Exception("diretorio invalido!");

        int i,j;
        i = j = 0;
        for (File file : folder.listFiles()) {
            if(i < n){
                MinhaThread thread = new MinhaThread(i+1,file, 0);
                thread.start();
                threads.add( thread );

                j = i;
            }
            else{
                if(j >= n)
                    j = j - n;
                threads.get(i- n).join();
                MinhaThread thread = new MinhaThread(j+1, file, 0);
                thread.start();
                threads.add(thread);
                j++;
            }
            i++;
                
        }
        print(threads);

    }
    public static void print(List<MinhaThread> threads) throws IOException, InterruptedException {
        File newFile = new File("src/resultado.txt");
        if(!newFile.exists())
            newFile.createNewFile();
        BufferedWriter write = new BufferedWriter( new FileWriter(newFile) );
        Integer vogais = 0;
        for (MinhaThread minhaThread : threads) {
            minhaThread.join(); // esperar terminar a thread;           
            write.write("Arquivo: "+ minhaThread.getFilename() +" Total: "+ minhaThread.getQtdVogais() +"\n");
            vogais += minhaThread.getQtdVogais();
        }
        write.write("Total de Vogais "+ vogais+ "\n");
        System.out.println("Total de Vogais "+ vogais);
        write.close();
    }
}
