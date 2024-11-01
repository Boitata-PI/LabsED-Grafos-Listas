package grafos;

/**
 * 
 * @author Victor Rafael de Roma
 */

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class GrafoEx4 {
 
    public static void main(String[] args) {
        String packagePath = GrafoEx4.class.getPackageName().replace(".", File.separator);
        String currentDirectory = System.getProperty("user.dir");
        String inputFilePath = currentDirectory + File.separator + "src" + File.separator + packagePath + File.separator + "entrada.txt";

        try {
            BufferedReader reader = new BufferedReader(new FileReader(inputFilePath));

            int dimensao = Integer.parseInt(reader.readLine());
            int[][] matriz = new int[dimensao][dimensao];

            for (int i = 0; i < dimensao; i++) {
                String linha = reader.readLine();
                String[] elementos = linha.split(" ");
                for (int j = 0; j < dimensao; j++) {
                    if (j < elementos.length) {
                        matriz[i][j] = Integer.parseInt(elementos[j]);
                        matriz[j][i] = matriz[i][j]; 
                    } else {
                        matriz[i][j] = 0;
                        matriz[j][i] = 0;
                    }
                }
            }

            System.out.println(dimensao);
            for (int i = 0; i < dimensao; i++) {
                for (int j = 0; j < dimensao; j++) {
                    System.out.print(matriz[i][j]);
                    if (j < dimensao - 1) {
                        System.out.print(" ");
                    }
                }
                System.out.println();
            }

            reader.close();
        } catch (NumberFormatException | IOException e) {
            System.err.println("Erro ao ler o arquivo de entrada: " + e.getMessage());
        }
    }
}
