import java.util.List;
import java.util.Random;

public class Avancado extends Jogador {
    private int finalizacao;
    private int salto;
    private int efeito;
    private int posicionamento;

    //Construtores

    public Avancado(String nome){
        super(nome);
        this.setAreaDeJogo(Jogador.randomAreaDeJogo(false));
        this.finalizacao    = 50;
        this.salto          = 50;
        this.efeito         = 50;
        this.posicionamento = 50;
        super.setPontuacaoGeral();
    }

    public Avancado(String nome, AreaDeJogo areaDeJogo, int nrCamisola, int velocidade, int resistencia, int destreza, int impulsao, int jogoDeCabeca, int remate, int passe, List<String> historialClubes, int finalizacao, int salto, int efeito, int posicionamento){
        super(nome, areaDeJogo, nrCamisola, velocidade, resistencia, destreza, impulsao, jogoDeCabeca, remate, passe, historialClubes);
        this.finalizacao    = finalizacao;
        this.salto          = salto;
        this.efeito         = efeito;
        this.posicionamento = posicionamento;
        super.setPontuacaoGeral();
    }

    public Avancado(Avancado gr){
        super(gr);
        this.finalizacao    = gr.getFinalizacao();
        this.salto          = gr.getSalto();
        this.efeito         = gr.getEfeito();
        this.posicionamento = gr.getPosicionamento();
        super.setPontuacaoGeral();
    }


    //Gets

    public int getFinalizacao(){
        return this.finalizacao;
    }

    public int getSalto(){
        return this.salto;
    }

    public int getEfeito(){
        return this.efeito;
    }

    public int getPosicionamento(){
        return this.posicionamento;
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

        if(pos != 3) {
            if(pos == 0) deduction += 0.4;
            else deduction += 0.2;
        }

        return (int) (getOverall()*(1-deduction));
    }

    //Sets

    public void setFinalizacao(int finalizacao){
        this.finalizacao = finalizacao;
    }

    public void setSalto(int salto){
        this.salto = salto;
    }

    public void setEfeito(int efeito){
        this.efeito = efeito;
    }

    public void setPosicionamento(int posicionamento){
        this.posicionamento = posicionamento;
    }

    //clone
    public Avancado clone(){
        return new Avancado(this);
    }
    public Player Clone()   { return this.clone(); }

    //toString
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append(super.toString())
                .append("Finalizacao: ").append(this.finalizacao).append("\n")
                .append("Salto: ").append(this.salto).append("\n")
                .append("Efeito: ").append(this.efeito).append("\n")
                .append("Posicionamento: ").append(this.posicionamento).append("\n");
        return sb.toString();
    }

    //equals
    public boolean equals(Object o){
        if(!super.equals(o)) return false;
        Avancado gr = (Avancado) o;
        return  this.getFinalizacao()    == gr.getFinalizacao() &&
                this.getSalto()          == gr.getSalto()       &&
                this.getEfeito()         == gr.getEfeito()      &&
                this.getPosicionamento() == gr.getPosicionamento();
    }

    public int calculaPontuacaoGeral() {

        return (int) ((this.getVelocidade()) * 0.1 +
                (this.getStrike()) * 0.1 +
                (this.getDestreza()) * 0.05 +
                (this.getImpulsao()) * 0.1 +
                (this.getHeadGame()) * 0.05 +
                (this.getPasse()) * 0.05 +
                (this.getResistencia()) * 0.1 +

                (this.getEfeito()) * 0.1 +
                (this.getPosicionamento()) * 0.1 +
                (this.getSalto()) * 0.1 +
                (this.getFinalizacao()) * 0.15) ;

    }

    //Parse

    public static Avancado parse(String input){
        String[] campos = input.split(",");
        Random rand = new Random();
        return new Avancado(campos[0],
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
                rand.nextInt(100),
                rand.nextInt(100),
                rand.nextInt(100),
                rand.nextInt(100));
    }
}
