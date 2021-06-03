import java.util.*;
import java.util.stream.Collectors;


public class Catalogo {
    private List<Jogador> jogadoresSemEquipa;
    private Map<String,List<Jogador>> jogadores; //Jogadores associados ao nome da equipa.
    private int nrJogadores;
    private List<InfoJogo> historicoDeJogos;
    private Map<String,Equipa> equipas; //Equipas associadas ao seu nome

    public Catalogo(){
        this.nrJogadores        = 0;
        this.jogadoresSemEquipa = new ArrayList<>();
        this.jogadores          = new HashMap<>();
        this.historicoDeJogos   = new ArrayList<>();
        this.equipas            = new HashMap<>();
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
        for(List<Jogador> ls : this.jogadores.values()){
            for(int i = 0; i < ls.size(); i++, index++)
                nomesJogadores[index] = this.jogadoresSemEquipa.get(i).getName();
        }

        return nomesJogadores;
    }

    public String getEquipaString(String nome) {
        return this.equipas.get(nome).toString();
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

    //setters

    //Para ser criado o jogador é necessário que sejam fornecidos todos os dados
    public void criaJogador(String[] parametros){
        if(parametros.length != 13 && parametros.length != 15) return;

        //Area de Jogo
        AreaDeJogo adj;
        if(parametros[2].equals("Esquerda")) adj = AreaDeJogo.ESQUERDO;
        else if (parametros[2].equals("Centro")) adj = AreaDeJogo.CENTRO;
        else adj = AreaDeJogo.DIREITO;

        //Criacao do jogador
        Jogador jog;
        switch (parametros[0]){
            case "Guarda-Redes":
                jog = new GuardaRedes(parametros[1],adj,Integer.getInteger(parametros[3]),Integer.getInteger(parametros[4]), Integer.getInteger(parametros[5]), Integer.getInteger(parametros[6]), Integer.getInteger(parametros[7]), Integer.getInteger(parametros[8]), Integer.getInteger(parametros[9]), Integer.getInteger(parametros[10]), null, Integer.getInteger(parametros[11]), Integer.getInteger(parametros[12]), Integer.getInteger(parametros[13]), Integer.getInteger(parametros[14]));
                break;
            case "Defesa":
                jog = new Defesa(parametros[1],adj,Integer.getInteger(parametros[3]),Integer.getInteger(parametros[4]), Integer.getInteger(parametros[5]), Integer.getInteger(parametros[6]), Integer.getInteger(parametros[7]), Integer.getInteger(parametros[8]), Integer.getInteger(parametros[9]), Integer.getInteger(parametros[10]), null, Integer.getInteger(parametros[11]), Integer.getInteger(parametros[12]), Integer.getInteger(parametros[13]), Integer.getInteger(parametros[14]));
                break;
            case "Lateral":
                jog = new Lateral(parametros[1],adj,Integer.getInteger(parametros[3]),Integer.getInteger(parametros[4]), Integer.getInteger(parametros[5]), Integer.getInteger(parametros[6]), Integer.getInteger(parametros[7]), Integer.getInteger(parametros[8]), Integer.getInteger(parametros[9]), Integer.getInteger(parametros[10]), null, Integer.getInteger(parametros[11]), Integer.getInteger(parametros[12]));
                break;
            case "Medio":
                jog = new Medio(parametros[1],adj,Integer.getInteger(parametros[3]),Integer.getInteger(parametros[4]), Integer.getInteger(parametros[5]), Integer.getInteger(parametros[6]), Integer.getInteger(parametros[7]), Integer.getInteger(parametros[8]), Integer.getInteger(parametros[9]), Integer.getInteger(parametros[10]), null, Integer.getInteger(parametros[11]), Integer.getInteger(parametros[12]));
                break;
            case "Avancado":
                jog = new Avancado(parametros[1],adj,Integer.getInteger(parametros[3]),Integer.getInteger(parametros[4]), Integer.getInteger(parametros[5]), Integer.getInteger(parametros[6]), Integer.getInteger(parametros[7]), Integer.getInteger(parametros[8]), Integer.getInteger(parametros[9]), Integer.getInteger(parametros[10]), null, Integer.getInteger(parametros[11]), Integer.getInteger(parametros[12]), Integer.getInteger(parametros[13]), Integer.getInteger(parametros[14]));
                break;
            default:
                return;
        }

        this.jogadoresSemEquipa.add(jog);
        this.nrJogadores++;
    }

    //Caso nao tenha equipa nomeEquipa deve ser 'null'
    public void addJogador(Jogador jog, String nomeEquipa) {
        if(jog == null) return;

        //Caso 'nomeEquipa' seja 'null',ou se nao existir qualquer equipa com esse nome, o jogador é inserido na lista 'jogadoresSemEquipa'
        if(nomeEquipa == null || !this.equipas.containsKey(nomeEquipa))
            this.jogadoresSemEquipa.add(jog);
        //Se o nomeEquipa for válido
        else {
            this.jogadores.get(nomeEquipa).add(jog.clone());
            this.equipas.get(nomeEquipa).addJogador(jog);
        }
        this.nrJogadores++;
    }

    public void addEquipa(Equipa equipa) {
        if(equipa != null) {
            this.equipas.put(equipa.getName(), equipa.clone());
            this.jogadores.put(equipa.getName(),new ArrayList<>());
        }
    }

    public void addJogo(InfoJogo jogo) {
        if(jogo != null)
            this.historicoDeJogos.add(jogo.clone());
    }

    // Muda Jogador de Equipa

    public boolean mudaJogadorDeEquipa(int index, String nomeEquipa){
        //Verifica se a equipa, para a qual se pretende mudar o jogador, existe
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

        //Caso 'nomeEquipaAtual' == null entao quer dizer que o jogador nao estava em qualquer equipa
        if(nomeEquipaAtual != null)
            this.equipas.get(nomeEquipaAtual).removeJogador(jog.getName());

        //Adiciona o jogador na nova equipa
        this.addJogador(jog,nomeEquipa);



        return true;
    }
    
}
