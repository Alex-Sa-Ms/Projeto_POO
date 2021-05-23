import java.util.List;

public class Lateral extends Jogador{
    private int cruzamento;
    private int drible;

    //Construtores

    public Lateral(String nome){
        super(nome);
        this.cruzamento = 50;
        this.drible     = 50;
        super.setPontuacaoGeral();
    }

    public Lateral(String nome, AreaDeJogo areaDeJogo, int nrCamisola, int velocidade, int resistencia, int destreza, int impulsao, int jogoDeCabeca, int remate, int passe, List<String> historialClubes, int cruzamento, int drible){
        super(nome, areaDeJogo, nrCamisola, velocidade, resistencia, destreza, impulsao, jogoDeCabeca, remate, passe, historialClubes);
        this.cruzamento = cruzamento;
        this.drible     = drible;
        super.setPontuacaoGeral();
    }

    public Lateral(Lateral ll){
        super(ll);
        this.cruzamento = ll.getCruzamento();
        this.drible     = ll.getDrible();
        super.setPontuacaoGeral();
    }


    //Gets

    public int getCruzamento(){
        return this.cruzamento;
    }

    public int getDrible(){
        return this.drible;
    }

    //Sets

    public void setCruzamento(int cruzamento){
        this.cruzamento = cruzamento;
    }

    public void setDrible(int drible){
        this.drible = drible;
    }


    //clone
    public Lateral clone(){
        return new Lateral(this);
    }

    //toString
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append(super.toString())
                .append("Cruzamento: ").append(this.cruzamento).append("\n")
                .append("Drible: ").append(this.drible).append("\n");
        return sb.toString();
    }

    //equals
    public boolean equals(Object o){
        if(!super.equals(o)) return false;
        Lateral ll = (Lateral) o;
        return  this.getCruzamento() == ll.getCruzamento() &&
                this.getDrible()     == ll.getDrible();
    }


    public int calculaPontuacaoGeral() {

        return (this.getVelocidade()) * 0.1 +
                (this.getRemate()) * 0.1 +
                (this.getDestreza()) * 0.1 +
                (this.getImpulsao()) * 0.1 +
                (this.getJogoDeCabeca()) * 0.05 +
                (this.getPasse()) * 0.1 +
                (this.getResistencia()) * 0.15 +

                (this.getCruzamento()) * 0.15 +
                (this.getDrible()) * 0.15 +;

    }
}
