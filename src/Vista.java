import java.util.*;

public class Vista implements Observer {
    private final Controlador controlador;
    private static final Menu MenuPrincipal = new Menu("Menu Principal", new String[] {"Criar Jogador", "Listar Jogadores", "Criar Equipa", "Listar Equipa", "Novo Jogo", "Histórico de Jogos", "Ler estado a partir de ficheiro(.fma)", "Gravar estado", "Ler estado a partir de ficheiro log"});
    private static final Menu MenuPosicoes = new Menu("Escolha uma Posição", new String[] {"Guarda-Redes","Defesa","Lateral","Medio","Avancado"});
    private Menu MenuJogadores;
    private Menu MenuEquipas;
    private Menu MenuJogos;

    public Vista (Controlador controlador){
        this.controlador = controlador;
    }

    private static void esperaPermissaoParaContinuar(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Escreva qualquer coisa para continuar...");
        sc.nextLine();
    }

    public void run(){
        int opcaoMenuPrincipal, opcao;

        do{
            MenuPrincipal.executa();
            opcaoMenuPrincipal = MenuPrincipal.getOpcao();

            switch (opcaoMenuPrincipal) {
                case 1:
                    this.controlador.criaJogador(this.getParametrosJogador());
                    break;

                case 2:
                    MenuJogadores = new Menu("Jogadores", this.controlador.getArrayNomesJogadores());
                    MenuJogadores.executa();
                    opcao = MenuJogadores.getOpcao();
                    if(opcao != 0) {
                        System.out.println(this.controlador.getJogador(opcao - 1)); //opcao - 1 corresponde ao indice do jogador na lista fornecida

                        Vista.esperaPermissaoParaContinuar();

                        Scanner sc = new Scanner(System.in);
                        String resposta;
                        System.out.print("Pretende mudar este jogador de equipa (S/N)? ");
                        while(!(resposta = sc.next()).equals("S") && !resposta.equals("N"))
                            System.out.print("Resposta Inválida!\nPretende mudar este jogador de equipa (S/N)? ");

                        if (resposta.equals("S")) {
                            System.out.print("\n\nEscolha a equipa para a qual pretende transferir o jogador: ");
                            this.showMenuEquipas();
                            int op = MenuEquipas.getOpcao();
                            if(op != 0)
                                this.controlador.mudaJogadorDeEquipa(opcao - 1, MenuEquipas.getStringOpcao(op));
                        }
                        else break;
                    }
                    break;

                case 3:
                    this.controlador.criaEquipa(this.getParametrosEquipa());
                    break;

                case 4:
                    this.showMenuEquipas();
                    opcao = MenuEquipas.getOpcao();
                    if(opcao != 0)
                        System.out.println(this.controlador.getEquipa(MenuEquipas.getStringOpcao(opcao))); //opcao - 1 corresponde ao indice do nome da equipa na lista fornecida
                    Vista.esperaPermissaoParaContinuar();
                    break;

                case 5:
                    this.NovoJogo();
                    break;

                case 6:
                    MenuJogos = new Menu("Histórico de jogos",this.controlador.getArrayNomesJogos());
                    MenuJogos.executa();
                    opcao = MenuJogos.getOpcao();
                    if(opcao != 0)
                        System.out.println(this.controlador.getInfoJogo(opcao - 1));
                    Vista.esperaPermissaoParaContinuar();
                    break;

                case 7:
                    System.out.print("Introduza o nome do ficheiro do qual pretende ler o estado: ");
                    int code = this.controlador.carregaEstado(new Scanner(System.in).nextLine());
                    if(code == 0)
                        System.out.println("Estado lido com sucesso!");
                    else if(code == 1)
                        System.out.println("Não foi possível ler os dados! Verifique o nome do ficheiro.");
                    else if(code == 2)
                        System.out.println("Não foi possível ler os dados! Ficheiro não existente.");
                    else
                        System.out.println("Devido a algum erro não foi possível ler os dados!");
                    break;

                case 8:
                    System.out.print("Introduza o nome do ficheiro no qual pretende gravar: ");
                    if(this.controlador.guardaEstado(new Scanner(System.in).nextLine()) == 0)
                        System.out.println("Estado gravado com sucesso!");
                    else
                        System.out.println("Devido a algum erro não foi possível gravar os dados!");
                    break;

                case 9:
                    System.out.print("Introduza o nome do ficheiro(log) do qual pretende ler o estado: ");
                    code = this.controlador.carregaEstado(new Scanner(System.in).nextLine());
                    if(code == 0)
                        System.out.println("Estado lido com sucesso!");
                    else if(code == 1)
                        System.out.println("Não foi possível ler os dados! Verifique o nome do ficheiro.");
                    else
                        System.out.println("Não foi possível ler o ficheiro! Ficheiro mal formatado!");
                    break;
                default:
                    break;
            }
        }while(opcaoMenuPrincipal != 0);
    }

    /** Criar Jogador */

    //Lê um inteiro e transforma numa String
    private String getStat(Scanner sc, String pedido){
        int r;

        do{
            System.out.print(pedido);
            try {
                r = sc.nextInt();
            }
            catch (InputMismatchException e) { // Não foi inscrito um int
                r = -1;
                sc.nextLine();
            }
        } while(r < 0 || r > 100);

        return String.valueOf(r);
    }

    private String[] getParametrosJogador(){
        String[] parametros;
        Scanner sc = new Scanner(System.in);
        int opcao;

        // **** Parametros base ****

        //Escolha da posicao
        MenuPosicoes.executa();
        opcao = MenuPosicoes.getOpcao();

        //Caso seja escolhida opcao de Sair
        if(opcao == 0) return null;

        //Sabendo a posicao passamos a saber o número exato de argumentos a fornecer ao controlador (Nº de argumentos comuns é 10  + 1 da posicao)
        if(opcao == 3 || opcao == 4)
            parametros = new String[13];
        else
            parametros = new String[15];

        //Insercao da String que identifica a posicao no array de parametros
        parametros[0] = MenuPosicoes.getStringOpcao(opcao);

        // Nome
        System.out.print("Nome: ");
        parametros[1] = sc.nextLine();

        // Numero Camisola
        parametros[3] = this.getStat(sc,"Número da camisola: ");

        // Velocidade, Resistencia, etc ...
        parametros[4] = this.getStat(sc,"Velocidade: ");

        parametros[5] = this.getStat(sc,"Resistencia: ");

        parametros[6] = this.getStat(sc,"Destreza: ");

        parametros[7] = this.getStat(sc,"Impulsao: ");

        parametros[8] = this.getStat(sc,"Jogo de Cabeca: ");

        parametros[9] = this.getStat(sc,"Remate: ");

        parametros[10] = this.getStat(sc,"Passe: ");


        // **** Parametros Especificos De Cada Posicao ****

        String[] areasDeJogo = null;

        if(opcao == 1) /* Guarda-Redes */{
            parametros[11] = this.getStat(sc,"Elasticidade: ");

            parametros[12] = this.getStat(sc,"Jogo de Maos: ");

            parametros[13] = this.getStat(sc,"Reflexos: ");

            parametros[14] = this.getStat(sc,"Mergulho: ");
        }
        else if(opcao == 2) /* Defesa */ {
            parametros[11] = this.getStat(sc,"Defesa: ");

            parametros[12] = this.getStat(sc,"Forca: ");

            parametros[13] = this.getStat(sc,"Corte: ");

            parametros[14] = this.getStat(sc,"Marcacao: ");
        }
        else if(opcao == 3) /* Lateral */ {
            parametros[11] = this.getStat(sc,"Cruzamento: ");

            parametros[12] = this.getStat(sc,"Drible: ");

            areasDeJogo = new String[]{"Esquerda", "Direita"};
        }
        else if(opcao == 4) /* Medio */ {
            parametros[11] = this.getStat(sc,"Intercecao: ");

            parametros[12] = this.getStat(sc,"Visao: ");

            areasDeJogo = new String[]{"Esquerda", "Centro", "Direita"};
        }
        else /* Avancado */ {
            parametros[11] = this.getStat(sc,"Finalizacao: ");

            parametros[12] = this.getStat(sc,"Salto: ");

            parametros[13] = this.getStat(sc,"Efeito: ");

            parametros[14] = this.getStat(sc,"Posicionamento: ");

            areasDeJogo = new String[]{"Esquerda", "Centro", "Direita"};
        }

        // Area de Jogo

        //Se for GR ou Defesa a posicao é "Centro"
        if(opcao > 2) {
            Menu MenuAreaDeJogo = new Menu("Area de Jogo", areasDeJogo);
            MenuAreaDeJogo.executa();
            opcao = MenuAreaDeJogo.getOpcao();

            if (opcao == 0) return null;
            else parametros[2] = MenuAreaDeJogo.getStringOpcao(opcao);
        }
        else
            parametros[2] = "Centro";

        return parametros;
    }

    /** Criar Equipas **/

    public String[] getParametrosEquipa(){
        String[] parametros = new String[5]; //Espaço para o nome, e o número de jogadores de cada posicao, ou seja, a formacao
        Scanner sc = new Scanner(System.in);

        // Nome
        System.out.print("Nome: ");
        parametros[0] = sc.nextLine();

        while (this.controlador.existeEquipa(parametros[0])){
            System.out.print("Já existe uma equipa com este nome! Insira um novo nome: ");
            parametros[0] = sc.nextLine();
        }

        // Menu de Formacoes
        String[] formacoes = {"2-2-4-2", "2-2-5-1", "2-2-3-3" , "3-0-5-2"};
        Menu MenuFormacoes = new Menu("Escolha a formação (Defesas-Laterais-Medios-Avancados)", formacoes);
        MenuFormacoes.executa();
        int opcao = MenuFormacoes.getOpcao();

        if(opcao == 1) {
            parametros[1] = "2";
            parametros[2] = "2";
            parametros[3] = "4";
            parametros[4] = "2";
        }
        else if(opcao == 2) {
            parametros[1] = "2";
            parametros[2] = "2";
            parametros[3] = "5";
            parametros[4] = "1";
        }
        else if(opcao == 3) {
            parametros[1] = "2";
            parametros[2] = "2";
            parametros[3] = "3";
            parametros[4] = "3";
        }
        else if(opcao == 4){
            parametros[1] = "3";
            parametros[2] = "0";
            parametros[3] = "5";
            parametros[4] = "2";
        }
        else return null;

        return parametros;
    }

    /** Show Menu Equipas **/

    private void atualizaMenuEquipas(){
        String[] nomesEquipas = this.controlador.getArrayNomesEquipas();
        MenuEquipas = new Menu("Equipas", nomesEquipas);
    }

    private void showMenuEquipas(){
        this.atualizaMenuEquipas();
        MenuEquipas.executa();
    }

    /** Substituicao Equipa **/

    //Retorna:
    //      -1 se o jogador pretender sair
    //      0 se for feita uma troca entre titulares, ou se apenas tiver sido uma insercao numa posicao
    //      1 se for feita uma substituicao entre titular e suplente
    //Se inGame for true, significa que é uma troca no meio de um jogo
    private int substituicao(String nomeEquipa, boolean inGame){

        //Escolhe jogador que pretende substituir
        String[] jogadoresTitulares = this.controlador.getArrayInfoGenericaTitulares(nomeEquipa, inGame);
        Menu MenuTitulares = new Menu("Escolha o jogador a substituir (" + nomeEquipa + ")", jogadoresTitulares);
        MenuTitulares.executa();

        //As substituicoes da classe Equipa sao feitas pelo numero, logo é necessário obtê-lo para o jogador que se pretende substituir
        int opcao = MenuTitulares.getOpcao();

        //Sai se for escolhida a opcao "Sair"
        if(opcao == 0) return -1;

        int numeroCamisolaASubstituir = this.controlador.getNumeroCamisolaJogador(opcao - 1, nomeEquipa, inGame);

        //Apresentacao dos jogadores da equipa
        System.out.print("\n\nJogadores de " + nomeEquipa + ":\n");
        String[] suplentes = this.controlador.getArrayInfoGenericaSuplentes(nomeEquipa, inGame);
        for(String s : suplentes)
            if(!s.equals(""))
                System.out.println(s);

        //Numero de camisola do jogador que irá substituir o escolhido anteriormente
        Scanner sc = new Scanner(System.in);
        int numeroCamisolaSubstituto;

        do{
            System.out.println("Introduza o número de camisola do jogador substituto (Pode ser um titular), ou -1 se pretender sair): ");
            numeroCamisolaSubstituto = sc.nextInt();
        }while(!(numeroCamisolaSubstituto == -1 || (this.controlador.numeroCamisolaJogadorValido(numeroCamisolaSubstituto, nomeEquipa) && numeroCamisolaASubstituir != numeroCamisolaSubstituto)));

        //Retorna 0 pq o utilizador pode simplesmente querer cancelar a substituicao
        if(numeroCamisolaSubstituto == -1) return 0;

        //Indica que nao existe jogador nessa posicao, logo nao se trata de uma substituicao mas de uma insercao numa dada posicao (index = opcao - 1)
        //Esta situacao só acontece na selecao de jogadores para iniciar um jogo. Nunca no decorrer de uma partida!
        if(numeroCamisolaASubstituir == -1){
            this.controlador.insereJogador(opcao - 1, numeroCamisolaSubstituto, nomeEquipa);
            return 0;
        }
        else
            return this.controlador.substituicao(numeroCamisolaASubstituir, numeroCamisolaSubstituto, nomeEquipa, inGame);
    }

    //Retorna:
    // true se a equipa estiver pronta para jogar
    // false se o utilizador pretender sair
    private void substituicoes(String nomeEquipa, boolean inGame){
        while(substituicao(nomeEquipa,inGame) != -1);
    }

    /** Novo Jogo **/

    private void NovoJogo(){
        int opcao;
        boolean validaParaJogo;

        //Atualizacao do menu de equipas
        this.atualizaMenuEquipas();

        String nomeEquipaCasa;

        //Escolha da equipa da casa

        do {
            System.out.print("Escolha a equipa da casa:");
            MenuEquipas.executa();
            opcao = MenuEquipas.getOpcao();

            //Se for selecionado "Sair" então não será criado um novo jogo
            if (opcao == 0) return;

            //Guardado o nome da equipa da casa
            nomeEquipaCasa = MenuEquipas.getStringOpcao(opcao);

            validaParaJogo = this.controlador.equipaValidaParaJogo(nomeEquipaCasa);

            if(!validaParaJogo)
                System.out.println("Esta equipa não tem jogadores suficientes para participar num jogo.");
        }while (!validaParaJogo);


        //Escolha da equipa de fora
        String nomeEquipaFora;

        do {
            System.out.print("Escolha a equipa de fora:");
            MenuEquipas.executa();
            opcao = MenuEquipas.getOpcao();

            //Se for selecionado "Sair" então não será criado um novo jogo
            if (opcao == 0) return;

            //Guardado o nome da equipa de fora
            nomeEquipaFora = MenuEquipas.getStringOpcao(opcao);

            validaParaJogo = this.controlador.equipaValidaParaJogo(nomeEquipaFora);

            if(!validaParaJogo)
                System.out.println("Esta equipa não tem jogadores suficientes para participar num jogo.");

        } while (nomeEquipaCasa.equals(nomeEquipaFora) || !this.controlador.equipaValidaParaJogo(nomeEquipaFora));


        //Escolha dos jogadores iniciais titulares que jogam em casa
        substituicoes(nomeEquipaCasa, false);

        if(!this.controlador.equipaProntaParaJogar(nomeEquipaCasa)) return;

        //Escolha dos jogadores iniciais titulares que jogam fora
        substituicoes(nomeEquipaFora, false);

        if(!this.controlador.equipaProntaParaJogar(nomeEquipaFora)) return;

        //Cria Novo Jogo com estas duas equipas e inicia primeira parte
        System.out.println("\n");
        this.controlador.novoJogo(nomeEquipaCasa,nomeEquipaFora);
        this.controlador.correJogo();

        Vista.esperaPermissaoParaContinuar();

        //Substituicoes antes da segunda parte
        this.substituicoes(nomeEquipaCasa, true);
        this.substituicoes(nomeEquipaFora, true);

        //Inicia segunda parte
        System.out.println("\n");
        this.controlador.correJogo();

        System.out.println("\n");
        Vista.esperaPermissaoParaContinuar();
    }

    public void update(Observable o, Object arg) {
        System.out.println((String) arg);
    }
}
