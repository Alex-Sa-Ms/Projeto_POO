import java.util.List;

public class Lateral extends Jogador{
    private int cruzamento;
    private int drible;

    //Construtores

    public Lateral(){
        super();
        this.cruzamento = 50;
        this.drible     = 50;
        super.setPontuacaoGeral();
    }

    public Lateral(String nome, int nrCamisola, int velocidade, int resistencia, int destreza, int impulsao, int jogoDeCabeca, int remate, int passe, List<String> historialClubes, int cruzamento, int drible){
        super(nome, nrCamisola, velocidade, resistencia, destreza, impulsao, jogoDeCabeca, remate, passe, historialClubes);
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
        if(super.equals(o) == false) return false;
        Lateral ll = (Lateral) o;
        return  this.getCruzamento() == ll.getCruzamento() &&
                this.getDrible()     == ll.getDrible();
    }


    public int calculaPontuacaoGeral() {
        return 0;
    }
}
