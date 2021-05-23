public class Formacao {
    private int nrDefesas;
    private int nrLaterais;
    private int nrMedios;
    private int nrAvancados;

    //Construtor

    public Formacao() {
        this.nrDefesas   = 2;
        this.nrLaterais  = 2;
        this.nrMedios    = 3;
        this.nrAvancados = 3;
    }

    public Formacao(int nrDefesas, int nrLaterais, int nrMedios, int nrAvancados) {
        if(nrDefesas + nrLaterais + nrMedios + nrAvancados == 10) {
            this.nrDefesas   = nrDefesas;
            this.nrLaterais  = nrLaterais;
            this.nrMedios    = nrMedios;
            this.nrAvancados = nrAvancados;
        }
        else new Formacao();
    }

    public Formacao(Formacao formacao){
        this.nrDefesas   = formacao.getNrDefesas();
        this.nrLaterais  = formacao.getNrLaterais();
        this.nrMedios    = formacao.getNrMedios();
        this.nrAvancados = formacao.getNrAvancados();
    }

    //Gets e Sets

    public int getNrDefesas() {
        return nrDefesas;
    }

    public void setNrDefesas(int nrDefesas) {
        this.nrDefesas = nrDefesas;
    }

    public int getNrLaterais() {
        return nrLaterais;
    }

    public void setNrLaterais(int nrLaterais) {
        this.nrLaterais = nrLaterais;
    }

    public int getNrMedios() {
        return nrMedios;
    }

    public void setNrMedios(int nrMedios) {
        this.nrMedios = nrMedios;
    }

    public int getNrAvancados() {
        return nrAvancados;
    }

    public void setNrAvancados(int nrAvancados) {
        this.nrAvancados = nrAvancados;
    }


    //Clone
    public Formacao clone(){
        return new Formacao(this);
    }


}
