package listas;

/**
 * 
 * @author Eliel Godoy
 */

public class ListaEx3 {
    public static void main(String[] args) {
        
         Pilha pilha = new Pilha(10);

        // Adiciona elementos na Pilha A
        System.out.println("Empilhando na Pilha A:");
        pilha.empilhaA(1);
        pilha.empilhaA(2);
        pilha.empilhaA(3);
        pilha.mostrarPilhaA();

        // Adiciona elementos na Pilha B
        System.out.println("\nEmpilhando na Pilha B:");
        pilha.empilhaB(10);
        pilha.empilhaB(20);
        pilha.empilhaB(30);
        pilha.mostrarPilhaB();

        // Desempilha da Pilha A
        System.out.println("\nDesempilhando da Pilha A:");
        while (!pilha.vaziaA()) {
            int valor = pilha.desempilhaA();
            System.out.println("Desempilhado: " + valor);
        }

        // Desempilha da Pilha B
        System.out.println("\nDesempilhando da Pilha B:");
        while (!pilha.vaziaB()) {
            int valor = pilha.desempilhaB();
            System.out.println("Desempilhado: " + valor);
        }

        // Mostra o estado das pilhas após desempilhamento
        System.out.println("\nEstado final da Pilha A:");
        pilha.mostrarPilhaA();
        System.out.println("Estado final da Pilha B:");
        pilha.mostrarPilhaB();
        
    }
    
}

class Pilha{
    
    //Declaração das variáveis
    private int topoA, topoB;
    private int[] v;

    public Pilha(int tamanho) {
        //Define as variáveis de topo
        //e o tamanho do vetor compartilhado
        topoA = -1;
        topoB = tamanho-1;
        v = new int[tamanho];
        
    }
    
    public void empilhaA(int n){
        //verifica se o vetor está cheio
        if(topoA+1<=topoB){
            topoA++;
            v[topoA] = n;
        } else {
            System.out.println("vetor cheio!");
        }
    }
    
    public void empilhaB(int n){
        if(topoB-1>=topoA){
            topoB--;
            v[topoB] = n;
        } else {
            System.out.println("vetor cheio!");
        }
    }
    
    boolean vaziaA(){
        return topoA==-1;
    }
    
    boolean vaziaB(){
        return topoB==v.length-1;
    }
    
    int desempilhaA(){
        int n=0;
        if(!vaziaA()){
            n = v[topoA];
            topoA--;
        }
        return n;
    }
    
    int desempilhaB(){
        int n=0;
        if(!vaziaB()){
            n = v[topoB];
            topoB++;
        }
        return n;
    }
    
    int topoA(){
        return v[topoA];
    }
    
    int topoB(){
        return v[topoB];
    }
    
    void mostrarPilhaA(){
        System.out.println("Pilha A:");
        for(int i=0;i<topoA;i++){
            System.out.println(v[i]);
        }
    }
    
    void mostrarPilhaB(){
        System.out.println("Pilha B:");
        for(int i=v.length-1;i>topoB;i--){
            System.out.println(v[i]);
        }
    }
    
    int tamanhoA(){
        return topoA+1;
    }
    
    int tamanhoB(){
        return v.length-topoB+1;
    }
}