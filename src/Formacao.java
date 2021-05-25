public class Formacao {
    private int nrDefesas;
    private int nrLaterais;
    private int nrMedios;
    private int nrAvancados;

    //Verifica se uma formacao é valida
    //Só não é válida caso a soma de todos os números não seja igual a 10, e se o número de laterais não seja 0 ou 2.
    public boolean isValid(int nrDefesas, int nrLaterais, int nrMedios, int nrAvancados){
        return nrDefesas + nrLaterais + nrMedios + nrAvancados == 10 && (nrLaterais == 0 || nrLaterais == 2);
    }

    //Construtor

    public Formacao() {
        this.nrDefesas   = 2;
        this.nrLaterais  = 2;
        this.nrMedios    = 3;
        this.nrAvancados = 3;
    }

    public Formacao(int nrDefesas, int nrLaterais, int nrMedios, int nrAvancados) {
        if(!setFormacao(nrDefesas, nrLaterais, nrMedios, nrAvancados)) new Formacao();
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

    public int getNrLaterais() {
        return nrLaterais;
    }

    public int getNrMedios() {
        return nrMedios;
    }

    public int getNrAvancados() {
        return nrAvancados;
    }

    public boolean setFormacao(int nrDefesas, int nrLaterais, int nrMedios, int nrAvancados) {
        if(isValid(nrDefesas, nrLaterais, nrMedios, nrAvancados)) {
            this.nrDefesas   = nrDefesas;
            this.nrLaterais  = nrLaterais;
            this.nrMedios    = nrMedios;
            this.nrAvancados = nrAvancados;
            return true;
        }
        else return false;
    }

    //Clone
    public Formacao clone(){
        return new Formacao(this);
    }


}
