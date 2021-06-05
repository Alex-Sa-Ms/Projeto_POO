import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.Random;

public abstract class Jogador implements Player, Serializable {
    private String nome;
    private AreaDeJogo areaDeJogo;
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

    public Jogador(String nome) {
        Random random = new Random();
        this.nome             = nome;
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

    public Jogador(String nome, AreaDeJogo areaDeJogo, int nrCamisola, int velocidade,int resistencia,int destreza,int impulsao,int jogoDeCabeca,int remate,int passe,List<String> historialClubes) {
        this.nome             = nome;
        this.areaDeJogo       = areaDeJogo;
        this.nrCamisola       = nrCamisola;
        this.velocidade       = velocidade;
        this.resistencia      = resistencia;
        this.destreza         = destreza;
        this.impulsao         = impulsao;
        this.jogoDeCabeca     = jogoDeCabeca;
        this.remate           = remate;
        this.passe            = passe;
        if(historialClubes != null)
            this.historialClubes = new ArrayList<>(historialClubes);
        else
            this.historialClubes = new ArrayList<>();
    }

    public Jogador(Jogador jog){
        this.nome             = jog.getName();
        this.areaDeJogo       = jog.getAreaDeJogo();
        this.nrCamisola       = jog.getShirtNumber();
        this.pontuacaoGeral   = jog.getOverall();
        this.velocidade       = jog.getVelocidade();
        this.resistencia      = jog.getResistencia();
        this.destreza         = jog.getDestreza();
        this.impulsao         = jog.getImpulsao();
        this.jogoDeCabeca     = jog.getHeadGame();
        this.remate           = jog.getStrike();
        this.passe            = jog.getPasse();
        this.historialClubes  = jog.getHistorialClubes();
    }

    //Gets

    public String getName(){
        return this.nome;
    }

    public AreaDeJogo getAreaDeJogo() { return areaDeJogo; }

    public int getShirtNumber() { return nrCamisola; }

    public int getOverall(){ return this.pontuacaoGeral; }

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

    public int getHeadGame(){
        return this.jogoDeCabeca;
    }

    public int getStrike(){ return this.remate; }

    public int getPasse(){
        return this.passe;
    }

    public List<String> getHistorialClubes(){
        return new ArrayList<>(this.historialClubes);
    }

    public String getCurrentClub(){ return this.historialClubes.get(this.historialClubes.size()); }


    //Sets

    public void setNome(String nome){
        this.nome = nome;
    }

    public void setAreaDeJogo(AreaDeJogo areaDeJogo) { this.areaDeJogo = areaDeJogo; }

    public void setShirtNumber(int nrCamisola) { this.nrCamisola = nrCamisola; }

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
        this.historialClubes = new ArrayList<>(historialClubes);
    }

    /* Adiciona o nome do novo clube no fim do historial de clubes.*/
    public void setNewCurrentClub(String clubName){
        this.historialClubes.add(clubName);
    }

    /* Retorna uma area de jogo aleatória (Para medios e avancados usar como argumento 'false' e para lateral 'true')*/
    public static AreaDeJogo randomAreaDeJogo(boolean isLateral){
        int nrAreasJogo = 3;
        if(isLateral) nrAreasJogo = 2;
        int random = new Random().nextInt(nrAreasJogo);
        if(random == 0)
            return AreaDeJogo.ESQUERDO;
        else if (random == 1)
            return AreaDeJogo.DIREITO;
        else
            return AreaDeJogo.CENTRO;
    }


    /* Calcula a pontuação geral de um jogador em função da sua subclasse */
    public abstract int calculaPontuacaoGeral();

    //clone
    public abstract Jogador clone();

    //toString
    public String areaDeJogoToString(){
        if(this.areaDeJogo == AreaDeJogo.CENTRO) return "Centro";
        else if(this.areaDeJogo == AreaDeJogo.ESQUERDO) return "Esquerda";
        else return "Direita";
    }

    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("Name: ").append(this.nome).append("\n")
          .append("Posicao: ").append(this.getClass().getSimpleName()).append("\n")
          .append("Area de Jogo: ").append(this.areaDeJogoToString()).append("\n")
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
        return  this.getName().equals(jog.getName())                &&
                this.getShirtNumber()    == jog.getShirtNumber()    &&
                this.getOverall()        == jog.getOverall()        &&
                this.getVelocidade()     == jog.getVelocidade()     &&
                this.getResistencia()    == jog.getResistencia()    &&
                this.getDestreza()       == jog.getDestreza()       &&
                this.getImpulsao()       == jog.getImpulsao()       &&
                this.getHeadGame()       == jog.getHeadGame()       &&
                this.getStrike()         == jog.getStrike()         &&
                this.getPasse()          == jog.getPasse()          &&
                this.getHistorialClubes().equals(jog.getHistorialClubes());
    }

    public String getGenericInfo(){
        return "Name: " + this.nome + " Shirt Number: " + this.nrCamisola + " Overall: " + this.pontuacaoGeral;
    }
}
