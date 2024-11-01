package grafos;

/**
 * 
 * @author Juan Farias
 */

public class GrafoEx3 
{
    public static void imprimirArestas(int[][] matrizAdj) {
        int numVertices = matrizAdj.length;

        System.out.println("Arestas do Grafo Ponderado:");
        for (int i = 0; i < numVertices; i++) {
            for (int j = i + 1; j < numVertices; j++) {
                if (matrizAdj[i][j] > 0) {
                    System.out.println("(" + i + ", " + j + ") - Peso: " + matrizAdj[i][j]);
                }
            }
        }
    }

    
    public static void main(String[] args) {
        int[][] matrizAdj = {
            {0, 2, 0, 3},
            {2, 0, 4, 0},
            {0, 4, 0, 5},
            {3, 0, 5, 0}
        };

        imprimirArestas(matrizAdj);
    }
}