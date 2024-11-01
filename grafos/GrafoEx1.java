package grafos;

/**
 * 
 * @author João Pedro Cabral
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class GrafoEx1 {
    private int[][] matrizAdj;
    private int numVertices;

    public GrafoEx1(int[][] matrizAdj) {
        this.matrizAdj = matrizAdj;
        this.numVertices = matrizAdj.length;
    }

    public String[] arestasDoGrafo() {
        int numArestas = 0;
        for (int i = 0; i < numVertices; i++) {
            for (int j = i + 1; j < numVertices; j++) {
                if (matrizAdj[i][j] == 1) {
                    numArestas++;
                }
            }
        }
        String[] arestas = new String[numArestas];
        int index = 0;
        for (int i = 0; i < numVertices; i++) {
            for (int j = i + 1; j < numVertices; j++) {
                if (matrizAdj[i][j] == 1) {
                    arestas[index++] = "(" + i + ", " + j + ")";
                }
            }
        }
        return arestas;
    }

    public String tipoGrafo() {
        for (int i = 0; i < numVertices; i++) {
            for (int j = 0; j < numVertices; j++) {
                if (matrizAdj[i][j] != matrizAdj[j][i]) {
                    return "Dígrafo";
                }
            }
        }
        return "Grafo não-direcionado";
    }

    public int[] grauDeCadaVertice() {
        int[] graus = new int[numVertices];
        for (int i = 0; i < numVertices; i++) {
            int grau = 0;
            for (int j = 0; j < numVertices; j++) {
                grau += matrizAdj[i][j];
            }
            graus[i] = grau;
        }
        return graus;
    }

    public boolean grafoConexo() {
        boolean[] visitado = new boolean[numVertices];
        buscaEmProfundidade(0, visitado);
        for (boolean v : visitado) {
            if (!v) {
                return false;
            }
        }
        return true;
    }

    private void buscaEmProfundidade(int vertice, boolean[] visitado) {
        visitado[vertice] = true;
        for (int i = 0; i < numVertices; i++) {
            if (matrizAdj[vertice][i] == 1 && !visitado[i]) {
                buscaEmProfundidade(i, visitado);
            }
        }
    }

    public boolean grafoCiclico() {
        boolean[] visitado = new boolean[numVertices];
        boolean[] pilha = new boolean[numVertices];
        for (int i = 0; i < numVertices; i++) {
            if (explorarGrafo(i, visitado, pilha)) {
                return true;
            }
        }
        return false;
    }

    private boolean explorarGrafo(int vertice, boolean[] visitado, boolean[] pilha) {
        if (pilha[vertice]) {
            return true;
        }
        if (visitado[vertice]) {
            return false;
        }
        visitado[vertice] = true;
        pilha[vertice] = true;
        for (int i = 0; i < numVertices; i++) {
            if (matrizAdj[vertice][i] == 1 && explorarGrafo(i, visitado, pilha)) {
                return true;
            }
        }
        pilha[vertice] = false;
        return false;
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

    public static void main(String[] args) {
        System.out.println("Exemplo 01: ");
        
        int[][] matrizAdjExemplo1 = {
            {0, 1, 0, 1},
            {1, 0, 1, 0},
            {0, 1, 0, 1},
            {1, 0, 1, 0}
        };
        
        GrafoEx1 grafoExemplo = new GrafoEx1(matrizAdjExemplo1);
        
        String[] arestas = grafoExemplo.arestasDoGrafo();
        System.out.print("a) Arestas do grafo: ");
        for (String aresta : arestas) {
            System.out.print(aresta + " ");
        }
        System.out.println();

        System.out.println("b) Tipo de grafo: " + grafoExemplo.tipoGrafo());

        int[] graus = grafoExemplo.grauDeCadaVertice();
        System.out.print("c) Grau de cada vértice: ");
        for (int grau : graus) {
            System.out.print(grau + " ");
        }
        System.out.println();

        System.out.println("d) O grafo é conexo? " + grafoExemplo.grafoConexo());
        System.out.println("e) O grafo é cíclico? " + grafoExemplo.grafoCiclico());

        int[][] listaAdj = grafoExemplo.listaAdjacencias();
        System.out.println("f) Lista de adjacências:");
        for (int i = 0; i < listaAdj.length; i++) {
            System.out.print(i + ": ");
            for (int j = 0; j < listaAdj[i].length; j++) {
                System.out.print(listaAdj[i][j] + " ");
            }
            System.out.println();
        }
        
        System.out.println("\nExemplo 02: ");
        
        int[][] matrizAdjExemplo2 = {
            {0, 1, 1, 0},
            {1, 0, 0, 0},
            {1, 0, 0, 1},
            {0, 0, 1, 0}
        };
        
        GrafoEx1 grafoExemplo2 = new GrafoEx1(matrizAdjExemplo2);
        
        String[] arestas2 = grafoExemplo2.arestasDoGrafo();
        System.out.print("a) Arestas do grafo: ");
        for (String aresta : arestas2) {
            System.out.print(aresta + " ");
        }
        System.out.println();

        System.out.println("b) Tipo de grafo: " + grafoExemplo2.tipoGrafo());

        int[] graus2 = grafoExemplo2.grauDeCadaVertice();
        System.out.print("c) Grau de cada vértice: ");
        for (int grau : graus2) {
            System.out.print(grau + " ");
        }
        System.out.println();

        System.out.println("d) O grafo é conexo? " + grafoExemplo2.grafoConexo());
        System.out.println("e) O grafo é cíclico? " + grafoExemplo2.grafoCiclico());

        int[][] listaAdj2 = grafoExemplo2.listaAdjacencias();
        System.out.println("f) Lista de adjacências:");
        for (int i = 0; i < listaAdj2.length; i++) {
            System.out.print(i + ": ");
            for (int j = 0; j < listaAdj2[i].length; j++) {
                System.out.print(listaAdj2[i][j] + " ");
            }
            System.out.println();
        }
        
        System.out.println("\nExemplo 03: ");
        
        int[][] matrizAdjExemplo3 = {
            {0, 1, 0, 0},
            {0, 0, 1, 0},
            {0, 0, 0, 0},
            {0, 0, 0, 0}
        };
        
        GrafoEx1 grafoExemplo3 = new GrafoEx1(matrizAdjExemplo3);

        String[] arestas3 = grafoExemplo3.arestasDoGrafo();
        System.out.print("a) Arestas do grafo: ");
        for (String aresta : arestas3) {
            System.out.print(aresta + " ");
        }
        System.out.println();

        System.out.println("b) Tipo de grafo: " + grafoExemplo3.tipoGrafo());

        int[] graus3 = grafoExemplo3.grauDeCadaVertice();
        System.out.print("c) Grau de cada vértice: ");
        for (int grau : graus3) {
            System.out.print(grau + " ");
        }
        System.out.println();

        System.out.println("d) O grafo é conexo? " + grafoExemplo3.grafoConexo());
        System.out.println("e) O grafo é cíclico? " + grafoExemplo3.grafoCiclico());

        int[][] listaAdj3 = grafoExemplo3.listaAdjacencias();
        System.out.println("f) Lista de adjacências:");
        for (int i = 0; i < listaAdj3.length; i++) {
            System.out.print(i + ": ");
            for (int j = 0; j < listaAdj3[i].length; j++) {
                System.out.print(listaAdj3[i][j] + " ");
            }
            System.out.println();
        }
        
    }
}
