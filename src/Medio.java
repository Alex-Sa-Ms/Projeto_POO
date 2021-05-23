import java.util.List;

public class Medio extends Jogador{
    private int intercecao;
    private int visao;

    //Construtores

    public Medio(String nome){
        super(nome);
        this.intercecao = 50;
        this.visao      = 50;
        super.setPontuacaoGeral();
    }

    public Medio(String nome, AreaDeJogo areaDeJogo, int nrCamisola, int velocidade, int resistencia, int destreza, int impulsao, int jogoDeCabeca, int remate, int passe, List<String> historialClubes, int intercecao, int visao){
        super(nome, areaDeJogo, nrCamisola, velocidade, resistencia, destreza, impulsao, jogoDeCabeca, remate, passe, historialClubes);
        this.intercecao = intercecao;
        this.visao      = visao;
        super.setPontuacaoGeral();
    }

    public Medio(Medio md){
        super(md);
        this.intercecao = md.getIntercecao();
        this.visao      = md.getVisao();
        super.setPontuacaoGeral();
    }


    //Gets

    public int getIntercecao(){
        return this.intercecao;
    }

    public int getVisao(){
        return this.visao;
    }

    //Sets

    public void setIntercecao(int intercecao){
        this.intercecao = intercecao;
    }

    public void setVisao(int visao){
        this.visao = visao;
    }


    //clone
    public Medio clone(){
        return new Medio(this);
    }

    //toString
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append(super.toString())
                .append("Intercecao: ").append(this.intercecao).append("\n")
                .append("Visao: ").append(this.visao).append("\n");
        return sb.toString();
    }

    //equals
    public boolean equals(Object o){
        if(!super.equals(o)) return false;
        Medio md = (Medio) o;
        return  this.getIntercecao() == md.getIntercecao() &&
                this.getVisao()      == md.getVisao();
    }


    public int calculaPontuacaoGeral() {
        return 0;
    }
}
