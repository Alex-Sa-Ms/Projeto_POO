import java.util.List;

public class Defesa extends Jogador{
    private int defesa;
    private int forca;
    private int corte;
    private int marcacao;

    //Construtores

    public Defesa(){
        super();
        this.defesa   = 0;
        this.forca    = 0;
        this.corte    = 0;
        this.marcacao = 0;
    }

    public Defesa(String nome, int velocidade, int resistencia, int destreza, int impulsao, int jogo_de_cabeca, int remate, int passe, List<String> historial_clubes, int defesa, int forca, int corte, int marcacao){
        super(nome, velocidade, resistencia, destreza, impulsao, jogo_de_cabeca, remate, passe, historial_clubes);
        this.defesa   = defesa;
        this.forca    = forca;
        this.corte    = corte;
        this.marcacao = marcacao;
    }

    public Defesa(Defesa def){
        super(def);
        this.defesa   = def.getDefesa();
        this.forca    = def.getForca();
        this.corte    = def.getCorte();
        this.marcacao = def.getMarcacao();
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
        if(super.equals(o) == false) return false;
        Defesa def = (Defesa) o;
        return  this.getDefesa()   == def.getDefesa() &&
                this.getForca()    == def.getForca()  &&
                this.getCorte()    == def.getCorte()  &&
                this.getMarcacao() == def.getMarcacao();
    }

    public int calculaPontuacaoGeral() {
        return 0;
    }
}
