import java.util.List;
import java.util.Random;

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

    public int getOverall(int pos, int area) {
        float deduction = 0;
        AreaDeJogo areaNow;

        if(area == 0)
            areaNow = AreaDeJogo.ESQUERDO;
        else if (area == 1)
            areaNow = AreaDeJogo.CENTRO;
        else
            areaNow = AreaDeJogo.DIREITO;

        if(areaNow != this.getAreaDeJogo()) deduction += 0.05;

        if(pos != 1) {
            if(pos == 2) deduction += 0.2;
            else deduction += 0.3;
        }

        return (int) (getOverall()*(1-deduction));
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
    public Player Clone()  { return this.clone(); }

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

        return (int) ((this.getVelocidade()) * 0.1 +
                (this.getStrike()) * 0.1 +
                (this.getDestreza()) * 0.1 +
                (this.getImpulsao()) * 0.1 +
                (this.getHeadGame()) * 0.05 +
                (this.getPasse()) * 0.1 +
                (this.getResistencia()) * 0.15 +

                (this.getCruzamento()) * 0.15 +
                (this.getDrible()) * 0.15 );

    }

    //Parse

    public static Lateral parse(String input){
        String[] campos = input.split(",");
        Random rand = new Random();
        return new Lateral(campos[0],
                Jogador.randomAreaDeJogo(true),
                Integer.parseInt(campos[1]),
                Integer.parseInt(campos[2]),
                Integer.parseInt(campos[3]),
                Integer.parseInt(campos[4]),
                Integer.parseInt(campos[5]),
                Integer.parseInt(campos[6]),
                Integer.parseInt(campos[7]),
                Integer.parseInt(campos[8]),
                null,
                Integer.parseInt(campos[9]),
                rand.nextInt(100));
    }
}
