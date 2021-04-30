import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public abstract class Jogador {
    private String nome;
    private int pontuacao_geral;
    private int velocidade;
    private int resistencia;
    private int destreza;
    private int impulsao;
    private int jogo_de_cabeca;
    private int remate;
    private int passe;
    private List<String> historial_clubes;


    //Construtores

    public Jogador() {
        this.nome             = "";
        this.pontuacao_geral  = 0;
        this.velocidade       = 0;
        this.resistencia      = 0;
        this.destreza         = 0;
        this.impulsao         = 0;
        this.jogo_de_cabeca   = 0;
        this.remate           = 0;
        this.passe            = 0;
        this.historial_clubes = new ArrayList<>();
    }

    public Jogador(String nome, int velocidade,int resistencia,int destreza,int impulsao,int jogo_de_cabeca,int remate,int passe,List<String> historial_clubes) {
        this.nome             = nome;
        this.velocidade       = velocidade;
        this.resistencia      = resistencia;
        this.destreza         = destreza;
        this.impulsao         = impulsao;
        this.jogo_de_cabeca   = jogo_de_cabeca;
        this.remate           = remate;
        this.passe            = passe;
        this.historial_clubes = historial_clubes.stream().collect(Collectors.toList());
    }

    public Jogador(Jogador jog){
        this.nome             = jog.getNome();
        this.pontuacao_geral  = jog.getPontuacaoGeral();
        this.velocidade       = jog.getVelocidade();
        this.resistencia      = jog.getResistencia();
        this.destreza         = jog.getDestreza();
        this.impulsao         = jog.getImpulsao();
        this.jogo_de_cabeca   = jog.getJogoDeCabeca();
        this.remate           = jog.getRemate();
        this.passe            = jog.getPasse();
        this.historial_clubes = jog.getHistorialClubes();
    }

    //Gets

    public String getNome(){
        return this.nome;
    }

    public int getPontuacaoGeral(){
        return this.pontuacao_geral;
    }

    public int getVelocidade(){
        return this.velocidade;
    }

    public int getResistencia(){
        return this.resistencia;
    }

    public int getDestreza(){
        return this.destreza;
    }

    public int getImpulsao(){
        return this.impulsao;
    }

    public int getJogoDeCabeca(){
        return this.jogo_de_cabeca;
    }

    public int getRemate(){
        return this.remate;
    }

    public int getPasse(){
        return this.passe;
    }

    public List<String> getHistorialClubes(){
        return this.historial_clubes.stream().collect(Collectors.toList());
    }

    public String getCurrentClub(){ return this.historial_clubes.get(this.historial_clubes.size()); }


    //Sets

    public void setNome(String nome){
        this.nome = nome;
    }

    public void setPontuacaoGeral(){ this.pontuacao_geral = this.calculaPontuacaoGeral(); }

    public void setVelocidade(int velocidade){
        this.velocidade = velocidade;
    }

    public void setResistencia(int resistencia){
        this.resistencia = resistencia;
    }

    public void setDestreza(int destreza){
        this.destreza = destreza;
    }

    public void setImpulsao(int impulsao){
        this.impulsao = impulsao;
    }

    public void setJogoDeCabeca(int jogo_de_cabeca){
        this.jogo_de_cabeca = jogo_de_cabeca;
    }

    public void setRemate(int remate){
        this.remate = remate;
    }

    public void setPasse(int passe){
        this.passe = passe;
    }

    public void setHistorialClubes(List<String> historial_clubes){
        this.historial_clubes = historial_clubes.stream().collect(Collectors.toList());
    }

    /* Adiciona o nome do novo clube no fim do historial de clubes.*/
    public void setNewCurrentClub(String clubName){
        this.historial_clubes.add(clubName);
    }

    /* Calcula a pontuação geral de um jogador em função da sua classe */
    public abstract int calculaPontuacaoGeral();

    //clone
    public abstract Jogador clone();

    //toString
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("Name: ").append(this.nome).append("\n")
          .append("Velocidade: ").append(this.velocidade).append("\n")
          .append("Resistência: ").append(this.resistencia).append("\n")
          .append("Destreza: ").append(this.destreza).append("\n")
          .append("Impulsão: ").append(this.impulsao).append("\n")
          .append("Jogo de Cabeça: ").append(this.jogo_de_cabeca).append("\n")
          .append("Remate: ").append(this.remate).append("\n")
          .append("Passe: ").append(this.passe).append("\n")
          .append("Historial de Clubes: ").append(this.historial_clubes.toString()).append("\n");
        return sb.toString();
    }

    //equals
    public boolean equals(Object o){
        if(this == o) return true;
        if(o == null || this.getClass() != o.getClass()) return false;
        Jogador jog = (Jogador) o;
        return  this.getNome().equals(jog.getNome())                &&
                this.getPontuacaoGeral() == jog.getPontuacaoGeral() &&
                this.getVelocidade()     == jog.getVelocidade()     &&
                this.getResistencia()    == jog.getResistencia()    &&
                this.getDestreza()       == jog.getDestreza()       &&
                this.getImpulsao()       == jog.getImpulsao()       &&
                this.getJogoDeCabeca()   == jog.getJogoDeCabeca()   &&
                this.getRemate()         == jog.getRemate()         &&
                this.getPasse()          == jog.getPasse()          &&
                this.getHistorialClubes().equals(jog.getHistorialClubes());
    }

}
