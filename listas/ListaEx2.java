package listas;

/**
 * 
 * @author Julio Neves
 */

public class ListaEx2 {
    public static void main(String[] args) {
        Fila fila = new Fila(5); // Criando uma fila de tamanho 5

        // Demonstrando enfileiramento
        System.out.println("Enfileirando elementos:");
        fila.enfileira(10);
        fila.enfileira(20);
        fila.enfileira(30);
        fila.mostrarFila(); // Exibe: Fila: 10 20 30

        // Verificando a cabeça e cauda
        System.out.println("Cabeça: " + fila.cabeca()); // Exibe: Cabeça: 10
        System.out.println("Cauda: " + fila.cauda()); // Exibe: Cauda: 20 (pois 'fim' está no próximo índice)

        // Desenfileirando um elemento
        System.out.println("Desenfileirando: " + fila.desenfileira()); // Exibe: Desenfileirando: 10
        fila.mostrarFila(); // Exibe: Fila: 20 30

        // Enfileirando mais elementos
        fila.enfileira(40);
        fila.enfileira(50);
        fila.enfileira(60); // Fila cheia, este não deve ser adicionado
        fila.mostrarFila(); // Exibe: Fila: 20 30 40 50

        // Verificando se a fila está vazia ou cheia
        System.out.println("Fila vazia? " + fila.vazia()); // Exibe: Fila vazia? false
        System.out.println("Fila cheia? " + fila.cheia()); // Exibe: Fila cheia? true

        // Pesquisando um elemento
        System.out.println("Pesquisa 30: " + fila.pesquisa(30)); // Exibe: Pesquisa 30: true
        System.out.println("Pesquisa 100: " + fila.pesquisa(100)); // Exibe: Pesquisa 100: false

        // Mostrando o tamanho da fila
        System.out.println("Tamanho da fila: " + fila.tamanho()); // Exibe: Tamanho da fila: 4

        // Desenfileirando todos os elementos
        System.out.println("Desenfileirando todos os elementos:");
        while (!fila.vazia()) {
            System.out.println("Desenfileirando: " + fila.desenfileira());
            fila.mostrarFila();
        }
        
        // Tentando desenfileirar de uma fila vazia
        System.out.println("Desenfileirando de fila vazia: " + fila.desenfileira()); // Exibe: Desenfileirando de fila vazia: -1
   
    }
}

class Fila{

    private int[] v;
    private int frente, fim;
    
    public Fila(int tamanho){
        v = new int[tamanho];
        frente = 0;
        fim = 0;
    }
    
    void enfileira(int n){
        
        if(!cheia()){
            v[fim] = n;
            fim = (fim+1) % v.length;
        } else{
            System.out.println("Fila Cheia!");
        }
    }
    
    int desenfileira(){
        
        if(!vazia()){
            
            int valor = v[frente];
            frente = (frente+1)%v.length;
            return valor;
            
        }else{
            
            return -1;
        }
        
    }
    
    int cabeca(){
        
        return v[frente];
        
    }
    
    int cauda(){
        return v[fim-1];
    }
    
    boolean vazia(){
        return fim==frente;
    }
    
    boolean cheia(){
        return (fim+1) % v.length == frente;
    }
    
    void mostrarFila(){
        
        if (vazia()) {
            System.out.println("A fila está vazia!");
            return;
        }else{

            System.out.print("Fila: ");
            for (int i = frente; i != fim; i = (i + 1) % v.length) {
                System.out.print(v[i] + " ");
            }
            System.out.println();
        }
        
    }
    
    boolean pesquisa(int n){
        if (vazia()) {
            return false; // Fila vazia, não contém o valor
        }

        for (int i = frente; i != fim; i = (i + 1) % v.length) {
            if (v[i] == n) {
                return true; // Valor encontrado
            }
        }
        return false;
    }
    
    int tamanho(){
        if (fim >= frente) {
            return fim - frente;
        } else {
            return v.length - frente + fim;
        }
    }
}