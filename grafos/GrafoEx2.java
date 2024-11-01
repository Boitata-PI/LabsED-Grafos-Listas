package grafos;

/**
 * 
 * @author Maria Eduarda Werlang
 */

import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * Exercício 02 - Lab Grafos
 * Escreva um programa que receba como entrada 
 * um número inteiro n correspondente ao número 
 * de vértices e apresente como saída a matriz 
 * e a lista de adjacências para o grafo completo Kn.
 */
public class GrafoEx2 {
    
    private int[][] matrizAdj;
    private int numVertices;

    public GrafoEx2(int[][] matrizAdj) {
        this.matrizAdj = matrizAdj;
        this.numVertices = matrizAdj.length;
    }

    public int[][] listaAdjacencias() {
        int[][] listaAdj = new int[numVertices][];
        for (int i = 0; i < numVertices; i++) {
            int numVizinhos = 0;
            for (int j = 0; j < numVertices; j++) {
                if (matrizAdj[i][j] == 1) {
                    numVizinhos++;
                }
            }
            listaAdj[i] = new int[numVizinhos];
            int index = 0;
            for (int j = 0; j < numVertices; j++) {
                if (matrizAdj[i][j] == 1) {
                    listaAdj[i][index++] = j;
                }
            }
        }
        return listaAdj;
    }
    
    public static int[][] criarMatrizAdjacenciasCompleta(int n) {
        int[][] matriz = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i == j) {
                    matriz[i][j] = 0; 
                } else {
                    matriz[i][j] = 1; 
                }
            }
        }
        return matriz;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Digite o número de vértices (n): ");
        int n = scanner.nextInt();

        int[][] matrizAdj = criarMatrizAdjacenciasCompleta(n);
        GrafoEx2 grafo = new GrafoEx2(matrizAdj);

        System.out.println("\nMatriz de Adjacências:");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(matrizAdj[i][j] + " ");
            }
            System.out.println();
        }

        System.out.println("\nLista de Adjacências:");
        int[][] listaAdj = grafo.listaAdjacencias();
        for (int i = 0; i < listaAdj.length; i++) {
            System.out.print(i + ": ");
            for (int j = 0; j < listaAdj[i].length; j++) {
                System.out.print(listaAdj[i][j] + " ");
            }
            System.out.println();
        }

        scanner.close();
    }
    
}
