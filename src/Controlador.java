public class Controlador {
    private Catalogo catalogo;

    public Controlador(Catalogo catalogo){
        this.catalogo = catalogo;
    }

    public void criaJogador(String[] parametros){
        this.catalogo.criaJogador(parametros);
    }

    public String getJogador(int index){
        return this.catalogo.getJogadorString(index);
    }

    public String[] getArrayNomesJogadores(){
        return this.catalogo.getNomesJogadores();
    }

    public String getInfoJogo(int index){
        return this.catalogo.getInfoJogoString(index);
    }
}
