import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Equipa {
    private String nome;

    //Jogadores na lista de titulares são guardados pela seguinte ordem : Guarda-Redes -> Defesas -> Laterais -> Medios -> Avançados
    //Os indices nos quais serão guardados dependem da formação
    private List<Jogador> titulares;

    //Jogadores na lista de suplentes não são organizados
    private List<Jogador> suplentes;

    /** Formação (Talvez criar classe... Fazer comparações entre formações, sendo este um dos fatores que determinam as probabilidades de marcar ou defender)**/
    private int nrDefesas;
    private int nrLaterais;
    private int nrMedios;
    private int nrAvancados;

    //Função privada para que dada uma lista de jogadores e um numero máximo 'max', copia até a 'max' elementos.
    //Caso o numero de elementos presentes na lista for menor que 'max' coloca os restantes a null
    private List<Jogador> copiaListaJogadores (List<Jogador> jogadores, int max){
        int i = 0;
        int nr_players_to_copy = jogadores.size();
        List<Jogador> cloneJogadores = new ArrayList<>();

        if(nr_players_to_copy > max) nr_players_to_copy = max;
        for(; i < nr_players_to_copy; i++)
            cloneJogadores.add(jogadores.get(i).clone());
        for(; i < max ; i++)
            cloneJogadores.add(null);

        return cloneJogadores;
    }

    //Construtores

    public Equipa() {
        this.nome = "";
        this.titulares   = new ArrayList<>(11);
        for(int i = 0; i < 11; i++) this.titulares.add(null);
        this.suplentes   = new ArrayList<>(7);
        //Formação Default
        this.nrDefesas   = 2;
        this.nrLaterais  = 2;
        this.nrMedios    = 3;
        this.nrAvancados = 3;
    }

    public Equipa(String nome, List<Jogador> titulares, List<Jogador> suplentes, int nrDefesas, int nrLaterais, int nrMedios, int nrAvancados) {
        this.nome = nome;
        this.titulares   = copiaListaJogadores(titulares, 11);
        this.suplentes   = copiaListaJogadores(suplentes, 7);
        if(this.setFormacao(nrDefesas, nrLaterais, nrMedios, nrAvancados) == false){
            this.nrDefesas   = 2;
            this.nrLaterais  = 2;
            this.nrMedios    = 3;
            this.nrAvancados = 3;
        }
    }

    public Equipa(Equipa e){
        this.nome        = e.getNome();
        this.titulares   = e.getTitulares();
        this.suplentes   = e.getSuplentes();
        this.nrDefesas   = e.getNrDefesas();
        this.nrLaterais  = e.getNrLaterais();
        this.nrMedios    = e.getNrMedios();
        this.nrAvancados = e.getNrAvancados();
    }


    //Gets

    public String getNome() { return this.nome; }

    public List<Jogador> getTitulares() { return this.titulares.stream().collect(Collectors.toList()); }

    public List<Jogador> getSuplentes() { return this.suplentes.stream().collect(Collectors.toList()); }

    public int getNrDefesas() { return this.nrDefesas; }

    public int getNrLaterais() { return this.nrLaterais; }

    public int getNrMedios() { return this.nrMedios; }

    public int getNrAvancados() { return this.nrAvancados; }


    //Sets

    public void setNome(String nome) { this.nome = nome; }

    public void setTitulares(List<Jogador> titulares){ this.titulares = copiaListaJogadores(titulares, 11); }

    public void setSuplentes(List<Jogador> suplentes){ this.suplentes = copiaListaJogadores(suplentes, 7); }

    public boolean setFormacao(int nrDefesas, int nrLaterais, int nrMedios, int nrAvancados){
        if(nrDefesas + nrLaterais + nrMedios + nrAvancados == 10) {
            this.nrDefesas   = nrDefesas;
            this.nrLaterais  = nrLaterais;
            this.nrMedios    = nrMedios;
            this.nrAvancados = nrAvancados;
            return true;
        }
        else return false;
    }

    //public void setNrDefesas(int nrDefesas) { this.nrDefesas = nrDefesas; }

    //public void setNrLaterais(int nrLaterais) { this.nrLaterais = nrLaterais; }

    //public void setNrMedios(int nrMedios) { this.nrMedios = nrMedios; }

    //public void setNrAvancados(int nrAvancados) { this.nrAvancados = nrAvancados; }


    /** Funcoes de inserção na equipa **/

    public boolean existeNaEquipa(Jogador jog){ return this.titulares.contains(jog) || this.suplentes.contains(jog); }

    //Esta função tenta inserir um jogador, que já não exista na equipa, começando no indice 'index' fornecido até 'limite' - 1. Um jogador só é inserido numa posição null.
    //Retorna true caso consiga inserir, e false caso contrário.
    private boolean tentaInserirJogador(Jogador jog, int index, int limite){
        if(existeNaEquipa(jog) == true) return false;
        for(; index < limite ; index++){
            if(this.titulares.get(index) == null){
                this.titulares.set(index, jog.clone());
                return true;
            }
        }
        return false;
    }

    //Retorna:
    //  false se já existirem 11 titulares.
    //  true se for adicionado com sucesso. É adicionado na primeira vaga.
    public boolean addJogadorTitular(Jogador jog){
        return tentaInserirJogador(jog, 0, 11);
    }

    //Retorna:
    //  false se já existirem 7 suplentes(Nº máximo).
    //  true se for adicionado com sucesso. É adicionado na primeira vaga.
    public boolean addJogadorSuplente(Jogador jog){
        if(this.titulares.size() == 7) return false;
        this.suplentes.add(jog.clone());
        return true;
    }

    //Retorna:
    //  false se já existir um guarda-redes ou se já existir na equipa.
    //  true se for adicionado com sucesso.
    public boolean addJogadorComoGuardaRedes(Jogador jog){
        if(this.titulares.get(0) != null || this.existeNaEquipa(jog) == true) return false;
        this.titulares.set(0,jog.clone());
        return true;
    }

    // Para todas as funções addJogadorComo* :
    //      Retorna:
    //          false se já existirem jogadores em todos os indices especificos do array para essa posição, ou se já existir na equipa.
    //          true se for adicionado com sucesso.
    public boolean addJogadorComoDefesa(Jogador jog){
        int index  = 1,                                 // Indice do array onde se começa a guardar os defesas
            limite = 1 + this.nrDefesas;                // Indice do array onde se começa a guardar jogadores da posição seguinte, neste caso, os laterais.
        return tentaInserirJogador(jog, index, limite);
    }

    public boolean addJogadorComoLateral(Jogador jog){
        int index  = 1 + this.nrDefesas,                // Indice do array onde se começa a guardar os laterais
            limite = index + nrLaterais;                // Indice do array onde se começa a guardar jogadores da posição seguinte, neste caso, os medios.
        return tentaInserirJogador(jog, index, limite);
    }

    public boolean addJogadorComoMedio(Jogador jog){
        int index  = 1 + this.nrDefesas + this.nrLaterais, // Indice do array onde se começa a guardar os laterais
            limite = index + nrMedios;                     // Indice do array onde se começa a guardar jogadores da posição seguinte, neste caso, os medios.
        return tentaInserirJogador(jog, index, limite);
    }

    public boolean addJogadorComoAvancado(Jogador jog){
        int index  = 1 + this.nrDefesas + this.nrLaterais + this.nrMedios, // Indice do array onde se começa a guardar os laterais
            limite = 11;                                                   // Indice do array onde se começa a guardar jogadores da posição seguinte, neste caso, os medios.
        return tentaInserirJogador(jog, index, limite);
    }
}
