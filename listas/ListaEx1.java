package listas;

/**
 *
 * @author Victor Cardoso
 */

public class ListaEx1 
{
    /**
     * @param args the command line arguments
     */
     public static void main(String[] args) {
        ListaNomes ListaNomes = new ListaNomes();
        
        System.out.println("Lista está vazia: " + ListaNomes.estaVazia());
        System.out.println("Numero de Nomes na Lista: " + ListaNomes.quantidadeNomes());
        
        System.out.println("");
        
        ListaNomes.adicionar("Ana");
        ListaNomes.adicionar("Julio");
        ListaNomes.adicionar("Victor");
        ListaNomes.adicionar("Juan");
        ListaNomes.adicionar("Eliel");
        ListaNomes.adicionar("Josiel");
        
        ListaNomes.percorrerLista();
        System.out.println("");
        System.out.println("Lista está vazia: " + ListaNomes.estaVazia());
        System.out.println("Numero de Nomes na Lista: " + ListaNomes.quantidadeNomes());
        
        System.out.println("");
        
        System.out.println("Posição do Nome Julio: " + ListaNomes.indice("Julio"));
        System.out.println("Posição do Nome Felipe: " + ListaNomes.indice("Felipe"));
        
        System.out.println("");
        
        System.out.println("* Excluindo Juan *");
        ListaNomes.excluir("Juan");
        
        System.out.println("* Excluindo Felipe *");
        ListaNomes.excluir("Felipe");
        
        System.out.println("");
        
        System.out.println("* Renomeando Josiel para Juniper *");
        ListaNomes.renomear("Josiel", "Juniper");
        
        System.out.println("");
        
        System.out.println("Numero de Nomes na Lista: " + ListaNomes.quantidadeNomes());
        ListaNomes.percorrerLista();
    }
}

class Nome {
    String nome;
    Nome proximo;
    Nome anterior;

    Nome(String nome) {
        this.nome = nome;
        this.proximo = null;
        this.anterior = null;
    }
}


class ListaLetras {
    Nome head;

    ListaLetras() {
        this.head = null;
    }
}


class ListaNomes {
    private ListaLetras[] listaLetras;

    public ListaNomes() {
        listaLetras = new ListaLetras[26]; 
        
        for (int i = 0; i < 26; i++) {
            listaLetras[i] = new ListaLetras();
        }
    }

    
    private int charToIndex(char c) {
        return Character.toLowerCase(c) - 'a'; 
    }
    
    
    private char indexToChar(int index){
        if (index < 0 || index >= 26) {
            throw new IllegalArgumentException("Índice fora do intervalo válido (0-25)");
        }
        
        return (char) Character.toUpperCase('a' + index);
    }

    
    public void adicionar(String nome) {
        int idx = charToIndex(nome.charAt(0));
        Nome newNome = new Nome(nome);

        if (listaLetras[idx].head == null) {
            listaLetras[idx].head = newNome;
        } else {
            Nome atual = listaLetras[idx].head;
            Nome anteriorious = null;

            while (atual != null && atual.nome.compareTo(nome) < 0) {
                anteriorious = atual;
                atual = atual.proximo; 
            }

            if (anteriorious == null) {
                newNome.proximo = listaLetras[idx].head;
                listaLetras[idx].head.anterior = newNome;
                listaLetras[idx].head = newNome;
            } else {
                anteriorious.proximo = newNome;
                newNome.anterior = anteriorious;
                newNome.proximo = atual;

                if (atual != null) {
                    atual.anterior = newNome;
                }
            }
        }
    }
    
    
    public boolean buscarNome(String nome) {
        int idx = charToIndex(nome.charAt(0));
        Nome atual = listaLetras[idx].head;

        while (atual != null) {
            if (atual.nome.equals(nome)) {
                return true; 
            }
            atual = atual.proximo;
        }
        return false; 
    }

    
    public String indice(String nome) {
        if (nome == null || nome.isEmpty()) {
            return "O nome não pode ser nulo ou vazio";
        }
        
        if (!buscarNome(nome)) {
            return "Nome não encontrado na lista";
        }

        char primeiraLetra = nome.charAt(0);
        int letraIndex = charToIndex(primeiraLetra);
        
        Nome atual = listaLetras[letraIndex].head;
        int posicaoNome = 0;
        
        while (atual != null) {
            if (atual.nome.equals(nome)) {
                break; 
            }
            atual = atual.proximo;
            posicaoNome++;
        }

        return "Letra: " + primeiraLetra + " (índice: " + letraIndex + "), Posição: " + posicaoNome;
    }
    
    
    public void excluir(String nome) {
        int idx = charToIndex(nome.charAt(0));
        Nome atual = listaLetras[idx].head;

        while (atual != null) {
            if (atual.nome.equals(nome)) {
                if (atual.anterior != null) {
                    atual.anterior.proximo = atual.proximo;
                } else {
                    listaLetras[idx].head = atual.proximo; 
                }

                if (atual.proximo != null) {
                    atual.proximo.anterior = atual.anterior;
                }
                return; 
            }
            atual = atual.proximo;
        }
    }


    public void renomear(String nomeAntigo, String nomeNovo) {
        if (!buscarNome(nomeAntigo) || buscarNome(nomeNovo)) {
            return; 
        }

        int idx = charToIndex(nomeAntigo.charAt(0));
        Nome atual = listaLetras[idx].head;

        while (atual != null) {
            if (atual.nome.equals(nomeAntigo)) {
                atual.nome = nomeNovo;

                excluir(nomeNovo); 
                adicionar(nomeNovo); 
                return; 
            }
            atual = atual.proximo;
        }
    }

    
    public boolean estaVazia() {
        for (ListaLetras lista : listaLetras) {
            if (lista.head != null) {
                return false;
            }
        }
        return true; 
    }

    
    public int quantidadeNomes() {
        int contador = 0;
        
        for (ListaLetras lista : listaLetras) {
            Nome atual = lista.head;
            while (atual != null) {
                contador++;
                atual = atual.proximo;
            }
        }
        
        return contador;
    }

    

    public void percorrerLista() {
        for (int i = 0; i < 26; i++) {
            if (listaLetras[i].head != null) {
                System.out.print("[" + this.indexToChar(i) + "] = ");
                
                Nome atual = listaLetras[i].head;
                
                while (atual != null) {
                    System.out.print(atual.nome + " -> ");
                    atual = atual.proximo;
                }
                
                System.out.println();
            }
        }
    }
}