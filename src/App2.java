
// import java.io.BufferedReader;
// import java.io.FileReader;
// import java.util.Scanner;

// public class App2 {
//     public static void main(String[] args) throws Exception {
//         Scanner ler = new Scanner(System.in);
//         Integer n = ler.nextInt();
//         ler.close();
//         FileReader arq = new FileReader("com.projeto/src/texto.txt");
//         BufferedReader lerArq = new BufferedReader(arq);
         
//         String linha = lerArq.readLine();
//         while (linha != null) {
//             for (int i = 0; i < n; i++) {
//                 new MinhaThread(i,linha, 100).start();
//                 if(i+1 != n && n > 1)
//                     linha = lerArq.readLine();

//             }
//             linha = lerArq.readLine();
//         }
//         lerArq.close();

//     }
// }
