import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.Random;

public abstract class Jogador {
    private String nome;
    private int nrCamisola;
    private int pontuacaoGeral;
    private int velocidade;
    private int resistencia;
    private int destreza;
    private int impulsao;
    private int jogoDeCabeca;
    private int remate;
    private int passe;
    private List<String> historialClubes;


    //Construtores

    public Jogador() {
        Random random = new Random();
        this.nome             = "";
        this.nrCamisola       = random.nextInt(100);
        this.pontuacaoGeral   = 50;
        this.velocidade       = 50;
        this.resistencia      = 50;
        this.destreza         = 50;
        this.impulsao         = 50;
        this.jogoDeCabeca     = 50;
        this.remate           = 50;
        this.passe            = 50;
        this.historialClubes  = new ArrayList<>();
    }

    public Jogador(String nome, int nrCamisola, int velocidade,int resistencia,int destreza,int impulsao,int jogoDeCabeca,int remate,int passe,List<String> historialClubes) {
        this.nome             = nome;
        this.nrCamisola       = nrCamisola;
        this.velocidade       = velocidade;
        this.resistencia      = resistencia;
        this.destreza         = destreza;
        this.impulsao         = impulsao;
        this.jogoDeCabeca     = jogoDeCabeca;
        this.remate           = remate;
        this.passe            = passe;
        if(historialClubes != null)
            this.historialClubes = historialClubes.stream().collect(Collectors.toList());
        else
            this.historialClubes = new ArrayList<>();
    }

    public Jogador(Jogador jog){
        this.nome             = jog.getNome();
        this.nrCamisola       = jog.getNrCamisola();
        this.pontuacaoGeral   = jog.getPontuacaoGeral();
        this.velocidade       = jog.getVelocidade();
        this.resistencia      = jog.getResistencia();
        this.destreza         = jog.getDestreza();
        this.impulsao         = jog.getImpulsao();
        this.jogoDeCabeca     = jog.getJogoDeCabeca();
        this.remate           = jog.getRemate();
        this.passe            = jog.getPasse();
        this.historialClubes  = jog.getHistorialClubes();
    }

    //Gets

    public String getNome(){
        return this.nome;
    }

    public int getNrCamisola() { return nrCamisola; }

    public int getPontuacaoGeral(){
        return this.pontuacaoGeral;
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
        return this.jogoDeCabeca;
    }

    public int getRemate(){
        return this.remate;
    }

    public int getPasse(){
        return this.passe;
    }

    public List<String> getHistorialClubes(){
        return this.historialClubes.stream().collect(Collectors.toList());
    }

    public String getCurrentClub(){ return this.historialClubes.get(this.historialClubes.size()); }


    //Sets

    public void setNome(String nome){
        this.nome = nome;
    }

    public void setNrCamisola(int nrCamisola) { this.nrCamisola = nrCamisola; }

    public void setPontuacaoGeral(){ this.pontuacaoGeral = this.calculaPontuacaoGeral(); }

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

    public void setJogoDeCabeca(int jogoDeCabeca){
        this.jogoDeCabeca = jogoDeCabeca;
    }

    public void setRemate(int remate){
        this.remate = remate;
    }

    public void setPasse(int passe){
        this.passe = passe;
    }

    public void setHistorialClubes(List<String> historialClubes){
        this.historialClubes = historialClubes.stream().collect(Collectors.toList());
    }

    /* Adiciona o nome do novo clube no fim do historial de clubes.*/
    public void setNewCurrentClub(String clubName){
        this.historialClubes.add(clubName);
    }

    /* Calcula a pontuação geral de um jogador em função da sua subclasse */
    public abstract int calculaPontuacaoGeral();

    //clone
    public abstract Jogador clone();

    //toString
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("Name: ").append(this.nome).append("\n")
          .append("Nº da Camisola: ").append(this.nrCamisola).append("\n")
          .append("Velocidade: ").append(this.velocidade).append("\n")
          .append("Resistência: ").append(this.resistencia).append("\n")
          .append("Destreza: ").append(this.destreza).append("\n")
          .append("Impulsão: ").append(this.impulsao).append("\n")
          .append("Jogo de Cabeça: ").append(this.jogoDeCabeca).append("\n")
          .append("Remate: ").append(this.remate).append("\n")
          .append("Passe: ").append(this.passe).append("\n")
          .append("Historial de Clubes: ").append(this.historialClubes.toString()).append("\n");
        return sb.toString();
    }

    //equals
    public boolean equals(Object o){
        if(this == o) return true;
        if(o == null || this.getClass() != o.getClass()) return false;
        Jogador jog = (Jogador) o;
        return  this.getNome().equals(jog.getNome())                &&
                this.getNrCamisola()     == jog.getNrCamisola()     &&
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
