import java.util.List;

public class Avancado extends Jogador {
    private int finalizacao;
    private int salto;
    private int efeito;
    private int posicionamento;

    //Construtores

    public Avancado(){
        super();
        this.finalizacao    = 0;
        this.salto          = 0;
        this.efeito         = 0;
        this.posicionamento = 0;
    }

    public Avancado(String nome, int velocidade, int resistencia, int destreza, int impulsao, int jogoDeCabeca, int remate, int passe, List<String> historialClubes, int finalizacao, int salto, int efeito, int posicionamento){
        super(nome, velocidade, resistencia, destreza, impulsao, jogoDeCabeca, remate, passe, historialClubes);
        this.finalizacao    = finalizacao;
        this.salto          = salto;
        this.efeito         = efeito;
        this.posicionamento = posicionamento;
    }

    public Avancado(Avancado gr){
        super(gr);
        this.finalizacao    = gr.getFinalizacao();
        this.salto          = gr.getSalto();
        this.efeito         = gr.getEfeito();
        this.posicionamento = gr.getPosicionamento();
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
        if(super.equals(o) == false) return false;
        Avancado gr = (Avancado) o;
        return  this.getFinalizacao()    == gr.getFinalizacao() &&
                this.getSalto()          == gr.getSalto()       &&
                this.getEfeito()         == gr.getEfeito()      &&
                this.getPosicionamento() == gr.getPosicionamento();
    }

    public int calculaPontuacaoGeral() {
        return 0;
    }
}
