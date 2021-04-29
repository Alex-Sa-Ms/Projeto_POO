import java.util.List;

public class GuardaRedes extends Jogador{
    private int elasticidade;
    private int jogo_de_maos;
    private int reflexos;
    private int mergulho;


    //Construtores

    public GuardaRedes(){
        super();
        this.elasticidade = 0;
        this.jogo_de_maos = 0;
        this.reflexos     = 0;
        this.mergulho     = 0;
    }

    public GuardaRedes(String nome, int velocidade, int resistencia, int destreza, int impulsao, int jogo_de_cabeca, int remate, int passe, List<String> historial_clubes, int elasticidade, int jogo_de_maos, int reflexos, int mergulho){
        super(nome, velocidade, resistencia, destreza, impulsao, jogo_de_cabeca, remate, passe, historial_clubes);
        this.elasticidade = elasticidade;
        this.jogo_de_maos = jogo_de_maos;
        this.reflexos     = reflexos;
        this.mergulho     = mergulho;
    }

    public GuardaRedes(GuardaRedes gr){
        super(gr);
        this.elasticidade = gr.getElasticidade();
        this.jogo_de_maos = gr.getJogoDeMaos();
        this.reflexos     = gr.getReflexos();
        this.mergulho     = gr.getMergulho();
    }


    //Gets

    public int getElasticidade(){
        return this.elasticidade;
    }

    public int getJogoDeMaos(){
        return this.jogo_de_maos;
    }

    public int getReflexos(){
        return this.reflexos;
    }

    public int getMergulho(){
        return this.mergulho;
    }


    //Sets

    public void setElasticidade(int elasticidade){
        this.elasticidade = elasticidade;
    }

    public void setJogoDeMaos(int jogo_de_maos){
        this.jogo_de_maos = jogo_de_maos;
    }

    public void setReflexos(int reflexos){
        this.reflexos = reflexos;
    }

    public void setMergulho(int mergulho){
        this.mergulho = mergulho;
    }

    //clone
    public GuardaRedes clone(){
        return new GuardaRedes(this);
    }

    //toString
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append(super.toString())
          .append("Elasticidade: ").append(this.elasticidade).append("\n")
          .append("Jogo de Maos: ").append(this.jogo_de_maos).append("\n")
          .append("Reflexos: ").append(this.reflexos).append("\n")
          .append("Mergulho: ").append(this.mergulho).append("\n");
        return sb.toString();
    }

    //equals
    public boolean equals(Object o){
        if(super.equals(o) == false) return false;
        GuardaRedes gr = (GuardaRedes) o;
        return  this.getElasticidade() == gr.getElasticidade() &&
                this.getJogoDeMaos()   == gr.getJogoDeMaos()   &&
                this.getReflexos()     == gr.getReflexos()     &&
                this.getMergulho()     == gr.getMergulho();
    }
}
