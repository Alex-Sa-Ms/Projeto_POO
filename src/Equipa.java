import java.util.*;
import java.util.stream.Collectors;

public class Equipa {
    private String nome;
    private int pontuacaoGlobal;

    //Jogadores na lista de titulares são guardados pela seguinte ordem : Guarda-Redes -> Defesas -> Laterais -> Medios -> Avançados
    //Os indices nos quais serão guardados dependem da formação
    private List<Jogador> titulares;

    //Jogadores na lista de suplentes não são organizados
    private List<Jogador> suplentes;

    //Formação
    private int nrDefesas;
    private int nrLaterais;
    private int nrMedios;
    private int nrAvancados;


    /***** Funções Auxiliares dos construtores e setters *****/

    /** Verifica existência na equipa/titulares/suplentes **/
    public boolean existeNaEquipa(Jogador jog){ return this.titulares.contains(jog) || this.suplentes.contains(jog); }

    public boolean existeNaEquipa(int nrCamisola){ return this.titulares.stream().anyMatch(jog -> jog.getNrCamisola() == nrCamisola)
            || this.suplentes.stream().anyMatch(jog -> jog.getNrCamisola() == nrCamisola);
    }

    //Retorna posição no array se existir. Caso contrário, retorna -1
    public int existeEmTitulares(Jogador jog){ return this.titulares.indexOf(jog); }

    public int existeEmTitulares(int nrCamisola){
        int index;
        for(index = 0; index < this.titulares.size() && this.titulares.get(index).getNrCamisola() != nrCamisola ; index++);
        if (index < this.titulares.size()) return index;
        return -1;
    }

    //Retorna posição no array se existir. Caso contrário, retorna -1
    public int existeEmSuplentes(Jogador jog){ return this.suplentes.indexOf(jog); }

    public int existeEmSuplentes(int nrCamisola){
        int index;
        for(index = 0; index < this.suplentes.size() && this.suplentes.get(index).getNrCamisola() != nrCamisola ; index++);
        if (index < this.suplentes.size()) return index;
        return -1;
    }

    /** Auxiliar na inserção de jogadores **/

    //Retorna o maior número de camisola presente na equipa
    //Usada para que em caso de colisão, seja atribuido automaticamente um número ainda não utilizado
    private int maxNrCamisola(){
        int maxTitulares = 0,
                maxSuplentes = 0;

        OptionalInt optMaxTitulares = this.titulares.stream()
                .filter(Objects::nonNull)
                .mapToInt(jog -> jog.getNrCamisola())
                .max();

        OptionalInt optMaxSuplentes = this.suplentes.stream()
                .mapToInt(jog -> jog.getNrCamisola())
                .max();

        if(optMaxTitulares != null) maxTitulares = optMaxTitulares.getAsInt();
        if(optMaxSuplentes != null) maxSuplentes = optMaxSuplentes.getAsInt();

        if(maxTitulares > maxSuplentes) return maxTitulares;
        else return maxSuplentes;
    }

    //Recebe um jogador e retorna um clone dele com o número da camisola alterado em caso de um jogador com esse número já existir na equipa
    //Caso o nr de camisola nao seja válido, será atribuido um automaticamente (Nr de Camisola mais elevado + 1)
    private Jogador cloneAndModNrCamisola(Jogador jog){
        Jogador jogClone = jog.clone();
        int nrCamisola   = jogClone.getNrCamisola();
        if(this.existeNaEquipa(nrCamisola))
            jogClone.setNrCamisola(this.maxNrCamisola() + 1);
        return jogClone;
    }

    /** Construtores **/

    public Equipa() {
        this.nome = "";
        this.pontuacaoGlobal = 0;
        this.titulares   = new ArrayList<>(11);
        for(int i = 0; i < 11; i++) this.titulares.add(null); //Necessário para que seja possível aceder a uma posição específica
        this.suplentes   = new ArrayList<>();
        this.nrDefesas   = 2;
        this.nrLaterais  = 2;
        this.nrMedios    = 3;
        this.nrAvancados = 3;
    }

    public Equipa(String nome, List<Jogador> titulares, List<Jogador> suplentes, int nrDefesas, int nrLaterais, int nrMedios, int nrAvancados) {
        this.nome = nome;
        this.setTitulares(titulares);
        this.setPontuacaoGlobal();
        this.suplentes       = suplentes.stream()
                                        .map(Jogador::clone)
                                        .collect(Collectors.toList());
        if(!this.setFormacao(nrDefesas, nrLaterais, nrMedios, nrAvancados)){
            this.nrDefesas   = 2;
            this.nrLaterais  = 2;
            this.nrMedios    = 3;
            this.nrAvancados = 3;
        }
    }

    public Equipa(String nome, List<Jogador> titulares, List<Jogador> suplentes){
        this(nome, titulares, suplentes, 2, 2, 3, 3);
    }

    public Equipa(Equipa e){
        this.nome            = e.getNome();
        this.pontuacaoGlobal = e.getPontuacaoGlobal();
        this.titulares       = e.getTitulares();
        this.suplentes       = e.getSuplentes();
        this.nrDefesas       = e.getNrDefesas();
        this.nrLaterais      = e.getNrLaterais();
        this.nrMedios        = e.getNrMedios();
        this.nrAvancados     = e.getNrAvancados();
    }


    /** Gets **/

    public String getNome() { return this.nome; }

    public List<Jogador> getTitulares() { return this.titulares.stream()
                                                               .filter(Objects::nonNull)
                                                               .collect(Collectors.toList());
    }

    public List<Jogador> getSuplentes() { return this.suplentes.stream()
                                                               .filter(Objects::nonNull)
                                                               .collect(Collectors.toList());
    }

    public int getNrDefesas() { return this.nrDefesas; }

    public int getNrLaterais() { return this.nrLaterais; }

    public int getNrMedios() { return this.nrMedios; }

    public int getNrAvancados() { return this.nrAvancados; }

    public int getPontuacaoGlobal() { return this.pontuacaoGlobal; }


    /** Sets **/

    public void setNome(String nome) { this.nome = nome; }

    public void setTitulares(List<Jogador> titulares){
        this.titulares = new ArrayList<>();

        //Determinação do número de jogadores a copiar da lista fornecida.
        int nr_players_to_copy = 0;
        if(titulares != null) nr_players_to_copy = titulares.size();
        if(nr_players_to_copy > 11) nr_players_to_copy = 11;

        // Clona titulares para a nova lista
        int i = 0; Jogador jog; int nrCamisola;

        for(; i < nr_players_to_copy; i++) {
            //Clona o jogador e modifica o nr da camisola caso necessário.
            jog = cloneAndModNrCamisola( titulares.get(i) );

            //Inserçao do jogador na lista
            this.titulares.add(jog);
        }

        //Caso o numero de elementos presentes na lista for menor que 'max' coloca os restantes a null,
        //de forma a que estas posições possam ser acedidas pelo index
        for(; i < 11 ; i++)
            this.titulares.add(null);
    }

    public void setSuplentes(List<Jogador> suplentes){ this.suplentes = suplentes.stream()
                                                                                 .filter(Objects::nonNull)
                                                                                 .collect(Collectors.toList());
    }

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

    public void setPontuacaoGlobal() {
        //Filtragem dos jogadores 'null' na lista
        List<Jogador> aux    = this.titulares.stream()
                                             .filter(Objects::nonNull)
                                             .collect(Collectors.toList());

        //Soma das pontuacoes gerais de cada jogador
        int somaPontuacoes   = aux.stream()
                                  .mapToInt(Jogador::getPontuacaoGeral)
                                  .sum();

        //Contagem do número de jogadores não 'null'
        int nrJogadores      = aux.size();

        this.pontuacaoGlobal = somaPontuacoes / nrJogadores;
    }


    /* Métodos que não fazem sentido ser invocados individualmente. Substituidos por 'setFormacao' */
    //public void setNrDefesas(int nrDefesas) { this.nrDefesas = nrDefesas; }

    //public void setNrLaterais(int nrLaterais) { this.nrLaterais = nrLaterais; }

    //public void setNrMedios(int nrMedios) { this.nrMedios = nrMedios; }

    //public void setNrAvancados(int nrAvancados) { this.nrAvancados = nrAvancados; }


    /** Inserção na equipa **/

    //Esta função tenta inserir um jogador, que já não exista na equipa, começando no indice 'index' fornecido até 'limite' - 1. Um jogador só é inserido numa posição null.
    //Retorna true caso consiga inserir, e false caso contrário.
    //Também atualiza a pontuação global da equipa
    private boolean tentaInserirTitular(Jogador jog, int index, int limite){
        //Verifica se já existe um jogador igual a este no plantel
        if(this.existeNaEquipa(jog)) return false;

        //Clona e altera número da camisola caso seja necessário.
        Jogador jogClone = cloneAndModNrCamisola(jog);

        //Insere jogador numa posição que não esteja ocupado, i.e., esteja ocupada por 'null'
        for(; index < limite ; index++){
            if(this.titulares.get(index) == null){
                this.titulares.set(index, jogClone);
                this.setPontuacaoGlobal();
                return true;
            }
        }
        return false;
    }

    //Retorna:
    //  false se já existirem 11 titulares, ou se o jogador for 'null'
    //  true se for adicionado com sucesso. É adicionado na primeira vaga.
    public boolean addJogadorTitular(Jogador jog){
        if(jog == null) return false;
        return tentaInserirTitular(jog, 0, 11);
    }

    //Retorna:
    //  false se for 'null'
    //  true se for adicionado com sucesso. É adicionado na primeira vaga.
    public boolean addJogadorSuplente(Jogador jog){
        if(jog == null) return false;

        //Clona e altera número da camisola caso seja necessário.
        Jogador jogClone = cloneAndModNrCamisola(jog);

        this.suplentes.add(jogClone);
        return true;
    }

    //Retorna:
    //  false se já existir um guarda-redes ou se já existir na equipa.
    //  true se for adicionado com sucesso.
    public boolean addJogadorComoGuardaRedes(Jogador jog){
        if(jog == null || this.titulares.get(0) != null || this.existeNaEquipa(jog)) return false;
        Jogador jogClone = cloneAndModNrCamisola(jog);
        this.titulares.set(0,jogClone);
        this.setPontuacaoGlobal();
        return true;
    }

    // Para todas as funções addJogadorComo* :
    //      Retorna:
    //          false se já existirem jogadores em todos os indices especificos do array para essa posição, ou se já existir na equipa.
    //          true se for adicionado com sucesso.
    public boolean addJogadorComoDefesa(Jogador jog){
        if(jog == null) return false;
        int index  = 1,                                 // Indice do array onde se começa a guardar os defesas
            limite = 1 + this.nrDefesas;                // Indice do array onde se começa a guardar jogadores da posição seguinte, neste caso, os laterais.
        return tentaInserirTitular(jog, index, limite);
    }

    public boolean addJogadorComoLateral(Jogador jog){
        if(jog == null) return false;
        int index  = 1 + this.nrDefesas,                // Indice do array onde se começa a guardar os laterais
            limite = index + nrLaterais;                // Indice do array onde se começa a guardar jogadores da posição seguinte, neste caso, os medios.
        return tentaInserirTitular(jog, index, limite);
    }

    public boolean addJogadorComoMedio(Jogador jog){
        if(jog == null) return false;
        int index  = 1 + this.nrDefesas + this.nrLaterais, // Indice do array onde se começa a guardar os laterais
            limite = index + nrMedios;                     // Indice do array onde se começa a guardar jogadores da posição seguinte, neste caso, os medios.
        return tentaInserirTitular(jog, index, limite);
    }

    public boolean addJogadorComoAvancado(Jogador jog){
        if(jog == null) return false;
        int index  = 1 + this.nrDefesas + this.nrLaterais + this.nrMedios, // Indice do array onde se começa a guardar os laterais
            limite = 11;                                                   // Indice do array onde se começa a guardar jogadores da posição seguinte, neste caso, os medios.
        return tentaInserirTitular(jog, index, limite);
    }


    /** Substituição de jogadores **/

    //Retorna:
    //  false se não existir um dos jogadores a titular
    //  true  se a troca for feita com sucesso
    private void trocaJogadoresTitulares(int index_jog1, int index_jog2){
        Jogador jogador1 = this.titulares.get(index_jog1);
        Jogador jogador2 = this.titulares.get(index_jog2);

        this.titulares.set(index_jog1, jogador2);
        this.titulares.set(index_jog2, jogador1);
    }

    //Retorna:
    //  false se não existir o jog1 a titular ou o jog2 a suplente
    //  true  se a troca for feita com sucesso
    private void trocaTitularPorSuplente(int index_jog1, int index_jog2){
        Jogador jogador1 = this.titulares.get(index_jog1);
        Jogador jogador2 = this.suplentes.get(index_jog2);

        this.titulares.set(index_jog1, jogador2);
        this.suplentes.set(index_jog2, jogador1);
    }

    public boolean substituicao(Jogador jog1, Jogador jog2){
        int index1, index2;

        //Jogador 1 é um titular.
        if((index1 = this.existeEmTitulares(jog1)) != -1){
            //Jogador 2 também é titular?
            if((index2 = this.existeEmTitulares(jog2)) != -1) this.trocaJogadoresTitulares(index1, index2);
            //Jogador 2 é suplente?
            else if((index2 = this.existeEmSuplentes(jog2)) != -1) this.trocaTitularPorSuplente(index1, index2);
            //Jogador 2 não existe na equipa.
            else return false;

            return true;
        }
        //Jogador 1 é um suplente.
        else if((index1 = this.existeEmSuplentes(jog1)) != -1){
            //Jogador 2 é titular?
            if((index2 = this.existeEmTitulares(jog2)) != -1) { this.trocaTitularPorSuplente(index2, index1); return true; }
            //Jogador 2 também é suplente(logo não há necessidade para a substituicao), ou não existe
            else return false;
        }

        this.setPontuacaoGlobal();

        return false;
    }


    /** Verifica Equipa **/

    public boolean prontaParaJogar(){
        return this.titulares.stream().filter(Objects::nonNull).count() == 11;
    }
}
