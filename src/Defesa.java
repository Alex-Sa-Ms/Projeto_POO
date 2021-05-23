import java.util.List;

public class Defesa extends Jogador{
    private int defesa;
    private int forca;
    private int corte;
    private int marcacao;

    //Construtores

    public Defesa(String nome){
        super(nome);
        this.defesa   = 50;
        this.forca    = 50;
        this.corte    = 50;
        this.marcacao = 50;
        super.setPontuacaoGeral();
    }

    public Defesa(String nome, AreaDeJogo areaDeJogo, int nrCamisola, int velocidade, int resistencia, int destreza, int impulsao, int jogoDeCabeca, int remate, int passe, List<String> historialClubes, int defesa, int forca, int corte, int marcacao){
        super(nome, areaDeJogo, nrCamisola, velocidade, resistencia, destreza, impulsao, jogoDeCabeca, remate, passe, historialClubes);
        this.defesa   = defesa;
        this.forca    = forca;
        this.corte    = corte;
        this.marcacao = marcacao;
        super.setPontuacaoGeral();
    }

    public Defesa(Defesa def){
        super(def);
        this.defesa   = def.getDefesa();
        this.forca    = def.getForca();
        this.corte    = def.getCorte();
        this.marcacao = def.getMarcacao();
        super.setPontuacaoGeral();
    }


    //Gets

    public int getDefesa(){
        return this.defesa;
    }

    public int getForca(){
        return this.forca;
    }

    public int getCorte(){
        return this.corte;
    }

    public int getMarcacao(){
        return this.marcacao;
    }


    //Sets

    public void setDefesa(int defesa){ this.defesa = defesa; }

    public void setForca(int forca){
        this.forca = forca;
    }

    public void setCorte(int corte){
        this.corte = corte;
    }

    public void setMarcacao(int marcacao){
        this.marcacao = marcacao;
    }

    //clone
    public Defesa clone(){
        return new Defesa(this);
    }

    //toString
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append(super.toString())
                .append("Defesa: ").append(this.defesa).append("\n")
                .append("Forca: ").append(this.forca).append("\n")
                .append("Corte: ").append(this.corte).append("\n")
                .append("Marcacao: ").append(this.marcacao).append("\n");
        return sb.toString();
    }

    //equals
    public boolean equals(Object o){
        if(!super.equals(o)) return false;
        Defesa def = (Defesa) o;
        return  this.getDefesa()   == def.getDefesa() &&
                this.getForca()    == def.getForca()  &&
                this.getCorte()    == def.getCorte()  &&
                this.getMarcacao() == def.getMarcacao();
    }

    public int calculaPontuacaoGeral() {

        return (int) ((this.getVelocidade()) * 0.1 +
                (this.getRemate()) * 0.03 +
                (this.getDestreza()) * 0.05 +
                (this.getImpulsao()) * 0.05 +
                (this.getJogoDeCabeca()) * 0.02 +
                (this.getPasse()) * 0.1 +
                (this.getResistencia()) * 0.15 +

                (this.getCorte()) * 0.1 +
                (this.getDefesa()) * 0.15 +
                (this.getForca()) * 0.1 +
                (this.getMarcacao()) * 0.15 );

    }
}
