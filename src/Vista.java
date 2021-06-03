import java.util.List;
import java.util.Scanner;

public class Vista {
    private Controlador controlador;
    private static final String[] OpcoesMenuPrincipal = {"Criar Jogador", "Listar Jogadores", "Criar Equipa", "Listar Equipa", "Novo Jogo", "Histórico de Jogos"};
    private static final Menu MenuPrincipal = new Menu(OpcoesMenuPrincipal);
    private Menu MenuJogadores;
    private Menu MenuEquipas;
    private Menu MenuJogos;

    public Vista (Controlador controlador){
        this.controlador = controlador;
    }

    public void run(){
        int opcao;

        do{
            MenuPrincipal.executa();
            opcao = MenuPrincipal.getOpcao();

            switch (opcao) {
                case 1:
                    this.controlador.criaJogador(Vista.getParametrosJogador());
                    break;

                case 2:
                    MenuJogadores = new Menu(this.controlador.getArrayNomesJogadores());
                    MenuJogadores.executa();
                    opcao = MenuJogadores.getOpcao();
                    if(opcao != 0) {
                        System.out.println(this.controlador.getJogador(opcao - 1)); //opcao - 1 corresponde ao indice do jogador na lista fornecida

                        Scanner sc = new Scanner(System.in);
                        System.out.println("Pressione qualquer tecla para continuar...");
                        sc.next();

                        System.out.print("Pretende mudar este jogador de equipa (S/N)? ");
                        String resposta;
                        while(!(resposta = sc.next()).equals("S") && !resposta.equals("N"))
                            System.out.println("Resposta Inválida!\nPretende mudar este jogador de equipa (S/N)? ");

                        if (resposta.equals("S")) {
                            this.showMenuEquipas();
                            int op = MenuEquipas.getOpcao();
                            this.controlador.mudaJogadorDeEquipa(opcao - 1, MenuEquipas.getStringOpcao(op));
                        }
                        else break;
                    }
                    break;

                case 3:
                    this.controlador.criaEquipa(Vista.getParametrosEquipa());
                    break;

                case 4:
                    this.showMenuEquipas();
                    opcao = MenuEquipas.getOpcao();
                    if(opcao != 0)
                        System.out.println(this.controlador.getEquipa(MenuEquipas.getStringOpcao(opcao))); //opcao - 1 corresponde ao indice do nome da equipa na lista fornecida
                    break;

                case 5:
                    this.controlador.CorreNovoJogo(Vista.getParametrosJogo());
                    break;

                case 6:
                    MenuJogos = new Menu(this.controlador.getArrayNomesJogos());
                    MenuJogos.executa();
                    opcao = MenuJogos.getOpcao();
                    if(opcao != 0)
                        System.out.println(this.controlador.getInfoJogo(opcao - 1));
                    break;

                default:
                    break;
            }
        }while(opcao != 0);
    }

    /** Criar Jogador */

    private static String[] getParametrosJogador(){
        String[] parametros;
        Scanner sc = new Scanner(System.in);
        int opcao;

        //Escolha da posicao
        String[] posicoes = {"Guarda-Redes","Defesa","Lateral","Medio","Avancado"};
        Menu MenuPosicao = new Menu(posicoes);
        System.out.println("Selecione a posicao do Jogador:");
        MenuPosicao.executa();
        opcao = MenuPosicao.getOpcao();

        //Caso seja escolhida opcao de Sair
        if(opcao == 0) return null;

        //Sabendo a posicao passamos a saber o número exato de argumentos a fornecer ao controlador (Nº de argumentos comuns é 10  + 1 da posicao)
        if(opcao == 3 || opcao == 4)
            parametros = new String[13];
        else
            parametros = new String[15];

        //Insercao da String que identifica a posicao no array de parametros
        parametros[0] = posicoes[opcao - 1];

        // **** Parametros base ****

        // Nome
        System.out.print("Nome: ");
        parametros[1] = sc.next();

        // Area de Jogo
        System.out.print("Area de Jogo:\n");
        String[] areasDeJogo = {"Esquerda", "Centro", "Direita"};
        Menu MenuAreaDeJogo = new Menu(areasDeJogo);
        MenuAreaDeJogo.executa();
        opcao = MenuAreaDeJogo.getOpcao();

        if(opcao == 0) return null;
        else parametros[2] = MenuAreaDeJogo.getStringOpcao(opcao);

        // Numero Camisola
        System.out.print("Número da camisola: ");
        parametros[3] = String.valueOf(sc.nextInt());

        // Velocidade, Resistencia, etc ...
        System.out.print("Velocidade: ");
        parametros[4] = String.valueOf(sc.nextInt());

        System.out.print("Resistencia: ");
        parametros[5] = String.valueOf(sc.nextInt());

        System.out.print("Destreza: ");
        parametros[6] = String.valueOf(sc.nextInt());

        System.out.print("Impulsao: ");
        parametros[7] = String.valueOf(sc.nextInt());

        System.out.print("Jogo de Cabeca: ");
        parametros[8] = String.valueOf(sc.nextInt());

        System.out.print("Remate: ");
        parametros[9] = String.valueOf(sc.nextInt());

        System.out.print("Passe: ");
        parametros[10] = String.valueOf(sc.nextInt());


        // **** Parametros Especificos De Cada Posicao ****

        //Guarda-Redes
        if(opcao == 1) {
            System.out.print("Elasticidade: ");
            parametros[11] = String.valueOf(sc.nextInt());

            System.out.print("Jogo de Maos: ");
            parametros[12] = String.valueOf(sc.nextInt());

            System.out.print("Reflexos: ");
            parametros[13] = String.valueOf(sc.nextInt());

            System.out.print("Mergulho: ");
            parametros[14] = String.valueOf(sc.nextInt());
        }
        //Defesa
        else if(opcao == 2) {
            System.out.print("Defesa: ");
            parametros[11] = String.valueOf(sc.nextInt());

            System.out.print("Forca: ");
            parametros[12] = String.valueOf(sc.nextInt());

            System.out.print("Corte: ");
            parametros[13] = String.valueOf(sc.nextInt());

            System.out.print("Marcacao: ");
            parametros[14] = String.valueOf(sc.nextInt());
        }
        //Lateral
        else if(opcao == 3) {
            System.out.print("Cruzamento: ");
            parametros[11] = String.valueOf(sc.nextInt());

            System.out.print("Drible: ");
            parametros[12] = String.valueOf(sc.nextInt());
        }
        //Medio
        else if(opcao == 4) {
            System.out.print("Intercecao: ");
            parametros[11] = String.valueOf(sc.nextInt());

            System.out.print("Visao: ");
            parametros[12] = String.valueOf(sc.nextInt());
        }
        //Avancado
        else {
            System.out.print("Finalizacao: ");
            parametros[11] = String.valueOf(sc.nextInt());

            System.out.print("Salto: ");
            parametros[12] = String.valueOf(sc.nextInt());

            System.out.print("Efeito: ");
            parametros[13] = String.valueOf(sc.nextInt());

            System.out.print("Posicionamento: ");
            parametros[14] = String.valueOf(sc.nextInt());
        }

        return parametros;
    }

    /** Show Menu Equipas **/

    private void showMenuEquipas(){
        String[] nomesEquipas = this.controlador.getArrayNomesEquipas();
        MenuEquipas = new Menu(nomesEquipas);
        MenuEquipas.executa();
    }
}
