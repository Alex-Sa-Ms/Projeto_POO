import java.util.List;
import java.util.Random;

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

        if(pos != 2) {
            if(pos == 0) deduction += 0.4;
            if(pos == 1) deduction += 0.2;
            if(pos == 3) deduction += 0.1;

        }

        return (int) (getOverall()*(1-deduction));
    }

    //Sets

    public void setIntercecao(int intercecao){
        this.intercecao = intercecao;
    }

    public void setVisao(int visao){
        this.visao = visao;
    }


    //clone
    public Medio clone()  { return new Medio(this); }
    public Player Clone() { return this.clone(); }

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

        return (int) ((this.getVelocidade()) * 0.1 +
                (this.getStrike()) * 0.05 +
                (this.getDestreza()) * 0.1 +
                (this.getImpulsao()) * 0.1 +
                (this.getHeadGame()) * 0.05 +
                (this.getPasse()) * 0.2 +
                (this.getResistencia()) * 0.1 +

                (this.getVisao()) * 0.15 +
                (this.getIntercecao()) * 0.15 );

    }

    //Parse

    public static Medio parse(String input){
        String[] campos = input.split(",");
        Random rand = new Random();
        return new Medio(campos[0],
                Jogador.randomAreaDeJogo(false),
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
