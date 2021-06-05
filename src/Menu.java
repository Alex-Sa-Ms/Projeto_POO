/**
 * Esta classe implementa um menu em modo texto.
 * 
 * @author José Creissac Campos 
 * @version v2.1 (20170504)
 */
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.InputMismatchException;

public class Menu {
    // variáveis de instância
    private String titulo;
    private List<String> opcoes;
    private List<Boolean> valid; //Usada para bloquear a escolha de certas opcoes
    private int op;
    
    /**
     * Constructor for objects of class Menu
     */
    public Menu(String[] opcoes) {
        this.titulo = "Menu";
        this.opcoes = Arrays.asList(opcoes);
        this.op     = 0;
        this.valid  = new ArrayList<>();
        for(int i = 0; i < this.opcoes.size() ; i++)
            this.valid.add(true);
    }

    public Menu(String titulo, String[] opcoes){
        this.titulo = titulo;
        this.opcoes = Arrays.asList(opcoes);
        this.op     = 0;
        this.valid  = new ArrayList<>();
        for(int i = 0; i < this.opcoes.size() ; i++)
            this.valid.add(true);
    }

    /**
     * Método para apresentar o menu e ler uma opção.
     * 
     */
    public void executa() {
        do {
            showMenu();
            this.op = lerOpcao();
        } while (this.op == -1);
    }
    
    /** Apresentar o menu */
    private void showMenu() {
        System.out.println("\n *** " + this.titulo + " *** ");
        for (int i=0; i<this.opcoes.size(); i++) {
            if(this.valid.get(i))
                System.out.print(i+1);
            else
                System.out.print("*");
            System.out.print(" - ");
            System.out.println(this.opcoes.get(i));
        }
        System.out.println("0 - Sair");
    }
    
    /** Ler uma opção válida */
    private int lerOpcao() {
        int op; 
        Scanner is = new Scanner(System.in);
        
        System.out.print("Opção: ");
        try {
            op = is.nextInt();
        }
        catch (InputMismatchException e) { // Não foi inscrito um int
            op = -1;
        }
        if (op<0 || op>this.opcoes.size() || (op > 0 && !this.valid.get(op - 1))) {
            System.out.println("Opção Inválida!!!");
            op = -1;
        }
        return op;
    }
    
    /**
     * Método para obter a última opção lida
     */
    public int getOpcao() {
        return this.op;
    }

    /**
     * Método que retorna a string correspondente a uma certa opcao
     */
    public String getStringOpcao(int opcao){
        return this.opcoes.get(opcao - 1);
    }
}
