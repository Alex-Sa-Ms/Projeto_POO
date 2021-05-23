import java.util.List;

public class GuardaRedes extends Jogador{
    private int elasticidade;
    private int jogoDeMaos;
    private int reflexos;
    private int mergulho;


    //Construtores

    public GuardaRedes(String nome){
        super(nome);
        this.elasticidade = 50;
        this.jogoDeMaos   = 50;
        this.reflexos     = 50;
        this.mergulho     = 50;
        super.setPontuacaoGeral();
    }

    public GuardaRedes(String nome, AreaDeJogo areaDeJogo, int nrCamisola, int velocidade, int resistencia, int destreza, int impulsao, int jogoDeCabeca, int remate, int passe, List<String> historialClubes, int elasticidade, int jogoDeMaos, int reflexos, int mergulho){
        super(nome, areaDeJogo, nrCamisola, velocidade, resistencia, destreza, impulsao, jogoDeCabeca, remate, passe, historialClubes);
        this.elasticidade = elasticidade;
        this.jogoDeMaos   = jogoDeMaos;
        this.reflexos     = reflexos;
        this.mergulho     = mergulho;
        super.setPontuacaoGeral();
    }

    public GuardaRedes(GuardaRedes gr){
        super(gr);
        this.elasticidade = gr.getElasticidade();
        this.jogoDeMaos   = gr.getJogoDeMaos();
        this.reflexos     = gr.getReflexos();
        this.mergulho     = gr.getMergulho();
        super.setPontuacaoGeral();
    }


    //Gets

    public int getElasticidade(){
        return this.elasticidade;
    }

    public int getJogoDeMaos(){
        return this.jogoDeMaos;
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

    public void setJogoDeMaos(int jogoDeMaos){
        this.jogoDeMaos = jogoDeMaos;
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
          .append("Jogo de Maos: ").append(this.jogoDeMaos).append("\n")
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

    public int calculaPontuacaoGeral() {

        return  (int) ((this.getVelocidade())*0.05+
                (this.getRemate())*0.04+
                (this.getDestreza())*0.2+
                (this.getImpulsao())*0.1+
                (this.getJogoDeCabeca())*0.01+
                (this.getPasse())*0.05+
                (this.getResistencia())*0.05+

                (this.getElasticidade())*0.1+
                (this.getJogoDeMaos())*0.1+
                (this.getMergulho())*0.1+
                (this.getReflexos())*0.2);
    }
}
