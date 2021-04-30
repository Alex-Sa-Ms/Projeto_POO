import java.util.List;

public class Medio extends Jogador{
    private int intercecao;
    private int visao;

    //Construtores

    public Medio(){
        super();
        this.intercecao = 0;
        this.visao      = 0;
    }

    public Medio(String nome, int velocidade, int resistencia, int destreza, int impulsao, int jogo_de_cabeca, int remate, int passe, List<String> historial_clubes, int intercecao, int visao){
        super(nome, velocidade, resistencia, destreza, impulsao, jogo_de_cabeca, remate, passe, historial_clubes);
        this.intercecao = intercecao;
        this.visao      = visao;
    }

    public Medio(Medio md){
        super(md);
        this.intercecao = md.getIntercecao();
        this.visao      = md.getVisao();
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
        if(super.equals(o) == false) return false;
        Medio md = (Medio) o;
        return  this.getIntercecao() == md.getIntercecao() &&
                this.getVisao()      == md.getVisao();
    }


    public int calculaPontuacaoGeral() {
        return 0;
    }
}
