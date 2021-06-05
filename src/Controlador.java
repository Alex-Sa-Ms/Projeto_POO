import java.util.List;
import java.util.Observable;
import java.util.Observer;

public class Controlador extends Observable implements Observer {
    private Catalogo catalogo;
    private String evento;

    public Controlador(Catalogo catalogo){
        this.catalogo = catalogo;
    }

    //Relativas aos jogadores

    public void criaJogador(String[] parametros){
        this.catalogo.criaJogador(parametros);
    }

    public String getJogador(int index){
        return this.catalogo.getJogadorString(index);
    }

    public String[] getArrayNomesJogadores(){
        return this.catalogo.getNomesJogadores();
    }


    //Relativas ás equipas

    public String getEquipa(String nomeEquipa){
        return this.catalogo.getEquipaString(nomeEquipa);
    }

    public String[] getArrayNomesEquipas() { return this.catalogo.getNomesEquipas(); }

    //Faz pedido ao modelo para mudar o jogador no index fornecido para a equipa com nome 'nomeEquipa'
    public void mudaJogadorDeEquipa(int index, String nomeEquipa){
        this.catalogo.mudaJogadorDeEquipa(index,nomeEquipa);
    }

    public void criaEquipa(String[] parametros){
        this.catalogo.criaEquipa(parametros);
    }

    public boolean equipaValidaParaJogo(String nomeEquipa){
        return this.catalogo.equipaValidaParaJogo(nomeEquipa);
    }

    public boolean equipaProntaParaJogar(String nomeEquipa){
        return this.catalogo.equipaProntaParaJogo(nomeEquipa);
    }

    public String[] getArrayInfoGenericaTitulares(String nomeEquipa, boolean inGame){
        return this.catalogo.getArrayInfoGenericaTitulares(nomeEquipa, inGame);
    }

    public String[] getArrayInfoGenericaJogadores(String nomeEquipa){
        return this.catalogo.getArrayInfoGenericaJogadores(nomeEquipa);
    }

    public int getNumeroCamisolaJogador(int index, String nomeEquipa){
        return this.catalogo.getNumeroCamisolaJogador(index,nomeEquipa);
    }

    public boolean numeroCamisolaJogadorValido(int numeroCamisola, String nomeEquipa){
        return this.catalogo.numeroCamisolaJogadorValido(numeroCamisola, nomeEquipa);
    }

    public int substituicao(int numeroCamisolaASubstituir, int numeroCamisolaSubstituto, String nomeEquipa, boolean inGame){
        return this.catalogo.substituicao(numeroCamisolaASubstituir, numeroCamisolaSubstituto, nomeEquipa, inGame);
    }

    //Relativas aos jogos

    public String getInfoJogo(int index){
        return this.catalogo.getInfoJogoString(index);
    }

    public String[] getArrayNomesJogos(){
        return this.catalogo.getTitulosJogos();
    }

    public void novoJogo(String nomeEquipaCasa, String nomeEquipaFora){
        this.catalogo.iniciaNovoJogo(nomeEquipaCasa, nomeEquipaFora);
    }

    public void correJogo(){
        this.catalogo.correJogo();
    }


    //Avisa a Vista que existe um novo evento

    public void update(Observable o, Object arg) {
        List<Eventos> ls = (List<Eventos>) arg;
        int last = ls.size() - 1;
        evento = ls.get(last).toString();
        setChanged();
        notifyObservers(evento);
    }

    // Ler e guardar estado
    public int carregaEstado(String nomeFicheiro){
        Catalogo cat;
        int err = 0;
        try {
            cat = Catalogo.carregaEstado(nomeFicheiro);
        }
        catch (java.lang.ClassNotFoundException cnfe)
        {
            return 1;
        }
        catch (java.io.FileNotFoundException fnfe)
        {
            return 2;
        }
        catch (java.io.IOException fnfe)
        {
            return 3;
        }

        this.catalogo = cat;
        return 0;
    }

    public int guardaEstado(String nomeFicheiro){
        try {
            this.catalogo.guardaEstado(nomeFicheiro);
            return 0;
        }
        catch (java.io.FileNotFoundException fnfe){
            return 0;
        }
        catch (java.io.IOException fnfe) {
            return 1;
        }
    }

    public int lerEstadoLog(String nomeFicheiro){
        try {
            this.catalogo = Parser.parse(nomeFicheiro);
            return 0;
        }
        catch (LinhaIncorretaException e){
            return 1;
        }
    }
}
