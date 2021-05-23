import java.util.*;
import java.util.stream.Collectors;

public class Equipa {
    private String nome;
    private int pontuacaoGlobal;
    private Formacao formacao;

    //Jogadores na lista de titulares são guardados pela seguinte ordem : Guarda-Redes -> Defesas -> Laterais -> Medios -> Avançados
    //Os indices nos quais serão guardados dependem da formação
    private Jogador[] titulares;
    private Map<Integer,Jogador> suplentes;


    /***** Funções Auxiliares dos construtores e setters *****/

    /** Verifica existência na equipa/titulares/suplentes **/
    //Retorna posição no array se existir. Caso contrário, retorna -1
    public int existeEmTitulares(Jogador jog){
        for(int i = 0; i < 11; i++)
            if(this.titulares[i].equals(jog)) return i;
        return -1;
    }

    public int existeEmTitulares(int nrCamisola){
        for(int index = 0; index < 11 ; index++)
            if(this.titulares[index].getNrCamisola() == nrCamisola)
                return index;
        return -1;
    }

    public boolean existeEmSuplentes(Jogador jog){
        return this.suplentes.containsValue(jog);
    }

    public boolean existeEmSuplentes(int nrCamisola){
        return this.suplentes.containsKey(nrCamisola);
    }

    public boolean existeNaEquipa(Jogador jog){ return Arrays.stream(this.titulares).anyMatch(j -> j.equals(jog)) || existeEmSuplentes(jog);}

    public boolean existeNaEquipa(int nrCamisola){ return Arrays.stream(this.titulares).anyMatch(jog -> jog != null && jog.getNrCamisola() == nrCamisola)
            || existeEmSuplentes(nrCamisola);
    }


    /** Auxiliar na inserção de jogadores **/

    Comparator<Jogador> nrCamisolaComparator = Comparator.comparingInt(Jogador::getNrCamisola);

    //Retorna o maior número de camisola presente na equipa
    //Usada para que em caso de colisão, seja atribuido automaticamente um número ainda não utilizado
    private int maxNrCamisola(){
        //Maior número de camisola nos titulares
        int maxTitulares = 0;
        Jogador maxTitular = Arrays.stream(this.titulares)
                                   .max(nrCamisolaComparator)
                                   .orElse(null);
        if(maxTitular != null) maxTitulares = maxTitular.getNrCamisola();

        //Maior número de camisola nos suplentes
        int maxSuplentes = 0;
        for(int nrCamisola : this.suplentes.keySet())
            if(nrCamisola > maxSuplentes) maxSuplentes = nrCamisola;

        return Math.max(maxTitulares, maxSuplentes);
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

    public Equipa(String nome) {
        this.nome = nome;
        this.pontuacaoGlobal = 0;
        this.titulares = new Jogador[11];
        this.suplentes = new HashMap<>();
        this.formacao  = new Formacao();
    }

    public Equipa(String nome, int nrDefesas, int nrLaterais, int nrMedios, int nrAvancados) {
        this.nome = nome;
        this.pontuacaoGlobal = 0;
        this.titulares = new Jogador[11];
        this.suplentes = new HashMap<>();
        this.formacao  = new Formacao(nrDefesas, nrLaterais, nrMedios, nrAvancados);
    }

    public Equipa(Equipa e){
        this.nome            = e.getNome();
        this.pontuacaoGlobal = e.getPontuacaoGlobal();
        this.titulares       = e.getTitulares();
        this.suplentes       = e.getSuplentes();
        this.formacao        = e.getFormacao();
    }


    /** Gets **/

    public String getNome() { return this.nome; }

    public Jogador[] getTitulares() { return Arrays.copyOf(this.titulares, 11); }

    public Map<Integer,Jogador> getSuplentes() {
        Map<Integer,Jogador> newMap = new HashMap<>();
        this.suplentes.entrySet().forEach(e -> newMap.put(e.getKey(), e.getValue().clone()));
        return newMap;
    }

    public Formacao getFormacao() { return this.formacao.clone(); }

    public int getPontuacaoGlobal() { return this.pontuacaoGlobal; }


    /** Sets **/

    public void setNome(String nome) { this.nome = nome; }

    public void setFormacao(int nrDefesas, int nrLaterais, int nrMedios, int nrAvancados){
        this.formacao = new Formacao(nrDefesas, nrLaterais, nrMedios, nrAvancados);
    }

    public void setPontuacaoGlobal() {
        //Soma das pontuacoes gerais de cada jogador
        int somaPontuacoes = Arrays.stream(this.titulares)
                                   .filter(Objects::nonNull)
                                   .mapToInt(Jogador::getPontuacaoGeral)
                                   .sum();

        this.pontuacaoGlobal = somaPontuacoes / 11;
    }

    /** Inserção na equipa **/

    //Funções auxiliares que indicam o indice a partir do qual se insere os jogadores de uma dada posicao
    private int getInicioDefesas(){
        return 1;
    }

    private int getInicioLaterais(){
        return 1 + formacao.getNrDefesas();
    }

    private int getInicioMedios(){
        return 1 + formacao.getNrDefesas() + formacao.getNrLaterais();
    }

    private int getInicioAvancados(){
        return 1 + formacao.getNrDefesas() + formacao.getNrLaterais() + formacao.getNrMedios();
    }

    //Esta função tenta inserir um jogador, que não exista na equipa, começando no indice 'index' fornecido até 'limite' - 1. Um jogador só é inserido numa posição null.
    //Retorna true caso consiga inserir, e false caso contrário.
    //Também atualiza a pontuação global da equipa
    private boolean tentaInserirTitular(Jogador jog, int index, int limite){
        //Verifica se já existe um jogador igual a este no plantel
        if(jog == null || this.existeNaEquipa(jog)) return false;

        //Clona e altera número da camisola caso seja necessário.
        Jogador jogClone = cloneAndModNrCamisola(jog);

        //Insere jogador numa posição que não esteja ocupado, i.e., esteja ocupada por 'null'
        for(; index < limite ; index++){
            if(this.titulares[index] == null){
                this.titulares[index] = jogClone;
                this.setPontuacaoGlobal();
                return true;
            }
        }
        return false;
    }

    //Tenta inserir jogador na primeira posicao livre que existir
    private boolean tentaInserirTitular(Jogador jog){
        return tentaInserirTitular(jog, 0, 11);
    }

    //Retorna:
    //  false se já existirem 11 titulares, ou se o jogador for 'null'
    //  true se for adicionado com sucesso. É adicionado na primeira vaga.
    public boolean addJogadorTitular(Jogador jog){
        return tentaInserirTitular(jog);
    }

    //Retorna:
    //  false se for 'null'
    //  true se for adicionado com sucesso. É adicionado na primeira vaga.
    public void addJogadorSuplente(Jogador jog){
        if(jog == null || this.existeNaEquipa(jog)) return;

        //Clona e altera número da camisola caso seja necessário.
        Jogador jogClone = cloneAndModNrCamisola(jog);

        this.suplentes.put(jogClone.getNrCamisola(), jogClone);
    }

    //Retorna:
    //  false se já existir um guarda-redes ou se já existir na equipa.
    //  true se for adicionado com sucesso.
    public boolean addJogadorComoGuardaRedes(Jogador jog){
        if(jog == null || this.titulares[0] != null || this.existeNaEquipa(jog)) return false;
        Jogador jogClone = cloneAndModNrCamisola(jog);
        this.titulares[0] = jogClone;
        this.setPontuacaoGlobal();
        return true;
    }

    // Para todas as funções addJogadorComo* :
    //      Retorna:
    //          false se já existirem jogadores em todos os indices especificos do array para essa posição, ou se já existir na equipa.
    //          true se for adicionado com sucesso.
    public boolean addJogadorComoDefesa(Jogador jog)  { return tentaInserirTitular(jog, this.getInicioDefesas(), this.getInicioLaterais()); }

    public boolean addJogadorComoLateral(Jogador jog) { return tentaInserirTitular(jog, this.getInicioLaterais(), this.getInicioMedios()); }

    public boolean addJogadorComoMedio(Jogador jog)   { return tentaInserirTitular(jog, this.getInicioMedios(), this.getInicioAvancados()); }

    public boolean addJogadorComoAvancado(Jogador jog){ return tentaInserirTitular(jog, this.getInicioAvancados(), 11); }


    /** Substituição de jogadores **/

    //Retorna:
    //  false se não existir algum dos jogadores a titular
    //  true  se a troca for feita com sucesso
    private boolean trocaJogadoresTitulares(int nrCamisolaJog1, int nrCamisolaJog2){
        int indexJog1 = this.existeEmTitulares(nrCamisolaJog1); if(indexJog1 == -1) return false;
        int indexJog2 = this.existeEmTitulares(nrCamisolaJog2); if(indexJog2 == -1) return false;

        Jogador tmp = this.titulares[indexJog1];
        this.titulares[indexJog1] = this.titulares[indexJog2];
        this.titulares[indexJog2] = tmp;
        return true;
    }

    //Retorna:
    //  false se não existir o jog1 a titular ou o jog2 a suplente
    //  true  se a troca for feita com sucesso
    private boolean trocaTitularPorSuplente(int nrCamisolaJog1, int nrCamisolaJog2){
        int indexJog1 = this.existeEmTitulares(nrCamisolaJog1);
        if(indexJog1 == -1 || !this.existeEmSuplentes(nrCamisolaJog2)) return false;

        Jogador tmp = this.suplentes.get(nrCamisolaJog2);
        this.suplentes.remove(nrCamisolaJog2);
        this.suplentes.put(nrCamisolaJog1,this.titulares[indexJog1]);
        this.titulares[indexJog1] = tmp;
        return true;
    }

    public void substituicao(int nrCamisolaJog1, int nrCamisolaJog2) throws substituicaoInvalidaException{
        if(!trocaJogadoresTitulares(nrCamisolaJog1, nrCamisolaJog2))
            if (!trocaTitularPorSuplente(nrCamisolaJog1, nrCamisolaJog2)) throw new substituicaoInvalidaException();
        this.setPontuacaoGlobal(); //Atualiza a Pontuacao Global
    }

    /*
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
    }*/


    /** Verifica Equipa **/

    public boolean prontaParaJogar(){
        return Arrays.stream(this.titulares).filter(Objects::nonNull).count() == 11;
    }
}
