import java.io.*;
import java.util.*;
import java.util.stream.Collectors;


public class Catalogo extends Observable implements Observer, Serializable {
    private List<Jogador> jogadoresSemEquipa;
    private Map<String,List<Jogador>> jogadores; //Jogadores associados ao nome da equipa.
    private int nrJogadores;
    private Map<String,Equipa> equipas; //Equipas associadas ao seu nome
    private List<InfoJogo> historicoDeJogos;
    private Jogo novoJogo;

    public Catalogo(){
        this.nrJogadores        = 0;
        this.jogadoresSemEquipa = new ArrayList<>();
        this.jogadores          = new HashMap<>();
        this.historicoDeJogos   = new ArrayList<>();
        this.equipas            = new HashMap<>();

        Equipa equipaCasa = new Equipa("SLB");
        Equipa equipaFora = new Equipa("FCP");

        GuardaRedes j1 = new GuardaRedes("Jogador1");
        Lateral j2 = new Lateral("Jogador2");
        Lateral j3 = new Lateral("Jogador3");
        Defesa j4 = new Defesa("Jogador4");
        Defesa j5 = new Defesa("Jogador5");
        Medio j6 = new Medio("Jogador6");
        Medio j7 = new Medio("Jogador7");
        Medio j8 = new Medio("Jogador8");
        Medio j9 = new Medio("Jogador9");
        Avancado j10 = new Avancado("Jogador10");
        Avancado j11 = new Avancado("Jogador11");

        Medio j23 = new Medio("Jogador23");
        Medio j24 = new Medio("Jogador24");
        Avancado j25 = new Avancado("Jogador25");
        Avancado j26 = new Avancado("Jogador26");

        GuardaRedes j12 = new GuardaRedes("Jogador12");
        Lateral j13 = new Lateral("Jogador13");
        Lateral j14 = new Lateral("Jogador14");
        Defesa j15 = new Defesa("Jogador15");
        Defesa j16 = new Defesa("Jogador16");
        Medio j17 = new Medio("Jogador17");
        Medio j18 = new Medio("Jogador18");
        Medio j19 = new Medio("Jogador19");
        Medio j20 = new Medio("Jogador20");
        Avancado j21 = new Avancado("Jogador21");
        Avancado j22 = new Avancado("Jogador22");

        Defesa j27 = new Defesa("Jogador27");
        Medio j28 = new Medio("Jogador28");
        Lateral j29 = new Lateral("Jogador29");
        Avancado j30 = new Avancado("Jogador30");

        equipaCasa.addJogadorComoGuardaRedes(j1);
        equipaCasa.addJogadorComoDefesa(j2);
        equipaCasa.addJogadorComoDefesa(j3);
        equipaCasa.addJogadorComoLateral(j4);
        equipaCasa.addJogadorComoLateral(j5);
        equipaCasa.addJogadorComoMedio(j6);
        equipaCasa.addJogadorComoMedio(j7);
        equipaCasa.addJogadorComoMedio(j8);
        equipaCasa.addJogadorComoMedio(j9);
        equipaCasa.addJogadorComoAvancado(j10);
        equipaCasa.addJogadorComoAvancado(j11);

        equipaCasa.addJogadorSuplente(j23);
        equipaCasa.addJogadorSuplente(j24);
        equipaCasa.addJogadorSuplente(j25);
        equipaCasa.addJogadorSuplente(j26);

        equipaFora.addJogadorComoGuardaRedes(j12);
        equipaFora.addJogadorComoDefesa(j13);
        equipaFora.addJogadorComoDefesa(j14);
        equipaFora.addJogadorComoLateral(j15);
        equipaFora.addJogadorComoLateral(j16);
        equipaFora.addJogadorComoMedio(j17);
        equipaFora.addJogadorComoMedio(j18);
        equipaFora.addJogadorComoMedio(j19);
        equipaFora.addJogadorComoMedio(j20);
        equipaFora.addJogadorComoAvancado(j21);
        equipaFora.addJogadorComoAvancado(j22);

        equipaFora.addJogadorSuplente(j27);
        equipaFora.addJogadorSuplente(j28);
        equipaFora.addJogadorSuplente(j29);
        equipaFora.addJogadorSuplente(j30);

        this.addEquipa(equipaCasa);
        this.addEquipa(equipaFora);
    }

    //getters

    public String getJogadorString(int index){
        //Embora os jogadores estejam distribuidos em diversas listas, das quais apenas uma não se encontra no HashMap, consideramos como se fosse uma lista contínua

        if(index < this.jogadoresSemEquipa.size()){
            return this.jogadoresSemEquipa.get(index).toString();
        }
        else{
            index -= this.jogadoresSemEquipa.size();

            for(List<Jogador> ls : this.jogadores.values()){
                if(index < ls.size())
                    return ls.get(index).toString();
                else
                    index -= ls.size();
            }
        }

        return null;
    }

    public String[] getNomesJogadores(){
        String[] nomesJogadores = new String[this.nrJogadores];
        int index = 0;

        //Cópia dos nomes dos jogadores sem equipa
        for(; index < this.jogadoresSemEquipa.size(); index++)
            nomesJogadores[index] = this.jogadoresSemEquipa.get(index).getName();

        //Cópia dos nomes dos jogadores de cada equipa
        for(List<Jogador> ls : this.jogadores.values())
            for(int i = 0; i < ls.size(); i++, index++)
                nomesJogadores[index] = ls.get(i).getName();

        return nomesJogadores;
    }

    public String getEquipaString(String nome) {
        return this.equipas.get(nome).toString();
    }

    public String[] getNomesEquipas(){
        String[] nomesEquipas = new String[this.equipas.size()];
        int index = 0;

        for(String s : this.equipas.keySet())
            nomesEquipas[index++] = s;

        return nomesEquipas;
    }

    /*
    public Equipa getEquipa(String name){
        if(this.equipas.containsKey(name)) return this.equipas.get(name).clone();
        else return null;
    }*/

    public String getInfoJogoString (int index) { return this.historicoDeJogos.get(index).toString(); }

    public InfoJogo getJogo(int index){
        return this.historicoDeJogos.get(index).clone();
    }

    public String[] getTitulosJogos(){
        String[] NomesJogos = new String[this.historicoDeJogos.size()];
        int index = 0;

        for(InfoJogo ij : this.historicoDeJogos)
            NomesJogos[index++] = ij.getTituloJogo();

        return NomesJogos;
    }

    //setters

    //Para ser criado o jogador é necessário que sejam fornecidos todos os dados
    public void criaJogador(String[] parametros){
        if(parametros == null || (parametros.length != 13 && parametros.length != 15)) return;

        //Area de Jogo
        AreaDeJogo adj;
        if(parametros[2].equals("Esquerda")) adj = AreaDeJogo.ESQUERDO;
        else if (parametros[2].equals("Centro")) adj = AreaDeJogo.CENTRO;
        else adj = AreaDeJogo.DIREITO;

        //Criacao do jogador
        Jogador jog;
        switch (parametros[0]){
            case "Guarda-Redes":
                jog = new GuardaRedes(parametros[1],adj,Integer.parseInt(parametros[3]),Integer.parseInt(parametros[4]), Integer.parseInt(parametros[5]), Integer.parseInt(parametros[6]), Integer.parseInt(parametros[7]), Integer.parseInt(parametros[8]), Integer.parseInt(parametros[9]), Integer.parseInt(parametros[10]), null, Integer.parseInt(parametros[11]), Integer.parseInt(parametros[12]), Integer.parseInt(parametros[13]), Integer.parseInt(parametros[14]));
                break;
            case "Defesa":
                jog = new Defesa(parametros[1],adj,Integer.parseInt(parametros[3]),Integer.parseInt(parametros[4]), Integer.parseInt(parametros[5]), Integer.parseInt(parametros[6]), Integer.parseInt(parametros[7]), Integer.parseInt(parametros[8]), Integer.parseInt(parametros[9]), Integer.parseInt(parametros[10]), null, Integer.parseInt(parametros[11]), Integer.parseInt(parametros[12]), Integer.parseInt(parametros[13]), Integer.parseInt(parametros[14]));
                break;
            case "Lateral":
                jog = new Lateral(parametros[1],adj,Integer.parseInt(parametros[3]),Integer.parseInt(parametros[4]), Integer.parseInt(parametros[5]), Integer.parseInt(parametros[6]), Integer.parseInt(parametros[7]), Integer.parseInt(parametros[8]), Integer.parseInt(parametros[9]), Integer.parseInt(parametros[10]), null, Integer.parseInt(parametros[11]), Integer.parseInt(parametros[12]));
                break;
            case "Medio":
                jog = new Medio(parametros[1],adj,Integer.parseInt(parametros[3]),Integer.parseInt(parametros[4]), Integer.parseInt(parametros[5]), Integer.parseInt(parametros[6]), Integer.parseInt(parametros[7]), Integer.parseInt(parametros[8]), Integer.parseInt(parametros[9]), Integer.parseInt(parametros[10]), null, Integer.parseInt(parametros[11]), Integer.parseInt(parametros[12]));
                break;
            case "Avancado":
                jog = new Avancado(parametros[1],adj,Integer.parseInt(parametros[3]),Integer.parseInt(parametros[4]), Integer.parseInt(parametros[5]), Integer.parseInt(parametros[6]), Integer.parseInt(parametros[7]), Integer.parseInt(parametros[8]), Integer.parseInt(parametros[9]), Integer.parseInt(parametros[10]), null, Integer.parseInt(parametros[11]), Integer.parseInt(parametros[12]), Integer.parseInt(parametros[13]), Integer.parseInt(parametros[14]));
                break;
            default:
                return;
        }

        this.jogadoresSemEquipa.add(jog);
        this.nrJogadores++;
    }


    //Auxiliar de addJogador
    private void insertJogador(Jogador jog, String nomeEquipa){
        if(jog == null) return;

        //Caso 'nomeEquipa' seja 'null',ou se nao existir qualquer equipa com esse nome, o jogador é inserido na lista 'jogadoresSemEquipa'
        if(nomeEquipa == null || !this.equipas.containsKey(nomeEquipa))
            this.jogadoresSemEquipa.add(jog);
            //Se o nomeEquipa for válido
        else {
            this.jogadores.get(nomeEquipa).add(jog.clone());
            this.equipas.get(nomeEquipa).addJogadorSuplente(jog);
        }
    }

    //Caso nao tenha equipa nomeEquipa deve ser 'null'
    public void addJogador(Jogador jog, String nomeEquipa) {
        this.insertJogador(jog, nomeEquipa);
        this.nrJogadores++;
    }

    public void addEquipa(Equipa equipa) {
        if(equipa != null) {
            this.equipas.put(equipa.getName(), equipa.clone());
            this.jogadores.put(equipa.getName(),new ArrayList<>());
        }
    }

    public void iniciaNovoJogo(String nomeEquipaCasa, String nomeEquipaFora) {
        Equipa equipaCasa, equipaFora;
        if(nomeEquipaCasa != null && (equipaCasa = this.equipas.get(nomeEquipaCasa)) != null && nomeEquipaFora != null && (equipaFora = this.equipas.get(nomeEquipaFora)) != null) {
            this.novoJogo = new Jogo(equipaCasa, equipaFora);
            this.novoJogo.addObserver(this);
        }
    }

    public void correJogo(){
        if(novoJogo != null)
            if(novoJogo.correJogo() == 2)
                this.historicoDeJogos.add(this.novoJogo.getResumoJogo());
    }

    public void criaEquipa(String[] parametros){
        Equipa equipa = new Equipa(parametros[0], Integer.parseInt(parametros[1]), Integer.parseInt(parametros[2]), Integer.parseInt(parametros[3]), Integer.parseInt(parametros[4]));
        this.addEquipa(equipa);
    }

    /** Equipa **/

    public boolean mudaJogadorDeEquipa(int index, String nomeEquipa){
        //Verifica se a equipa existe
        if(nomeEquipa == null || !this.equipas.containsKey(nomeEquipa))
            return false;

        //Obtem o apontador para o jogador pretendido e elimina a referencia da lista da equipa atual
        Jogador jog = null;
        String nomeEquipaAtual = null;

        if(index < this.jogadoresSemEquipa.size()){
            jog = this.jogadoresSemEquipa.get(index);
            this.jogadoresSemEquipa.remove(index);
        }
        else{
            index -= this.jogadoresSemEquipa.size();

            for(Map.Entry<String,List<Jogador>> entry : this.jogadores.entrySet()){
                if(index < entry.getValue().size()) {
                    jog = entry.getValue().get(index);
                    entry.getValue().remove(index);
                    nomeEquipaAtual = entry.getKey();
                }
                else
                    index -= entry.getValue().size();
            }
        }

        //Caso nao exista nenhum jogador com o dado index
        if(jog == null) return false;

        //Se 'nomeEquipaAtual' == null entao nao é preciso retirar o jogador de uma equipa
        if(nomeEquipaAtual != null)
            this.equipas.get(nomeEquipaAtual).removeJogador(jog.getName());

        //Adiciona o jogador na nova equipa
        this.insertJogador(jog,nomeEquipa);

        return true;
    }

    public boolean equipaValidaParaJogo(String nomeEquipa){
        Equipa equipa = this.equipas.get(nomeEquipa);
        return equipa != null && equipa.jogadoresSuficientesParaJogar();
    }

    public boolean equipaProntaParaJogo(String nomeEquipa){
        Equipa equipa = this.equipas.get(nomeEquipa);
        return equipa != null && equipa.prontaParaJogar();
    }

    public String[] getArrayInfoGenericaTitulares(String nomeEquipa, boolean inGame){
        Equipa equipa;

        if(!inGame)
            equipa = this.equipas.get(nomeEquipa);
        else
            equipa = (Equipa) this.novoJogo.getEquipa(nomeEquipa);

        String[] infosGenericas = new String[11];
        String aux;
        int index = 0;

        for(Player player : equipa.getTitulares()) {
            if(player == null) aux = "";
            else aux = player.getGenericInfo();

            infosGenericas[index++] = aux;
        }

        return infosGenericas;
    }

    public String[] getArrayInfoGenericaJogadores(String nomeEquipa){
        Equipa equipa = this.equipas.get(nomeEquipa);
        String[] infosGenericas = new String[equipa.numberOfPlayers()];
        String aux;
        int index = 0;

        for(Player player : equipa.getTitulares()) {
            if(player == null) aux = "";
            else aux = player.getGenericInfo();

            infosGenericas[index++] = aux;
        }

        for(Player player : equipa.getSuplentes().values())
            infosGenericas[index++] = player.getGenericInfo();

        return infosGenericas;
    }

    public int getNumeroCamisolaJogador(int index, String nomeEquipa){
        Equipa equipa = this.equipas.get(nomeEquipa);
        return equipa.getPlayer(index).getShirtNumber();
    }

    public boolean numeroCamisolaJogadorValido(int numeroCamisola, String nomeEquipa){
        Equipa equipa = this.equipas.get(nomeEquipa);
        return equipa.existeNaEquipa(numeroCamisola);
    }

    public int substituicao(int numeroCamisolaASubstituir, int numeroCamisolaSubstituto, String nomeEquipa, boolean inGame){
        int r;
        if(!inGame){
            Equipa equipa = this.equipas.get(nomeEquipa);
            r = equipa.substituicao(numeroCamisolaASubstituir, numeroCamisolaSubstituto);
        }
        else {
            r = this.novoJogo.substituicao(numeroCamisolaASubstituir, numeroCamisolaSubstituto, nomeEquipa);
        }

        return r;
    }

    /** Ler e guardar estado **/

    public static Catalogo carregaEstado(String nomeFicheiro) throws FileNotFoundException, IOException, ClassNotFoundException {
        FileInputStream fis = new FileInputStream(nomeFicheiro);
        ObjectInputStream ois = new ObjectInputStream(fis);
        return (Catalogo) ois.readObject();
    }

    public void guardaEstado(String nomeFicheiro) throws IOException {
        if(nomeFicheiro == null) nomeFicheiro = "save.fma";
        else nomeFicheiro = nomeFicheiro + ".fma";
        FileOutputStream fos = new FileOutputStream(nomeFicheiro);
        ObjectOutputStream oos = new ObjectOutputStream(fos);

        oos.writeObject(this);  // deve implementar Serializable

        oos.flush();
        oos.close();
    }

    /** Informa o controlador da mudança nos eventos do jogo **/

    public void update(Observable o, Object arg) {
        this.setChanged();
        this.notifyObservers(arg);
    }
}
