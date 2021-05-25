import java.util.*;
import java.util.stream.Collectors;

public class Equipa implements Team{
    private String nome;
    private int pontuacaoGlobal;
    private Formacao formacao;

    //Jogadores na lista de titulares são guardados pela seguinte ordem : Guarda-Redes -> Defesas -> Laterais -> Medios -> Avançados
    //Os indices nos quais serão guardados dependem da formação
    private Player[] titulares;
    private Map<Integer,Player> suplentes;


    /***** Funções Auxiliares dos construtores e setters *****/

    /** Verifica existência na equipa/titulares/suplentes **/
    //Retorna posição no array se existir. Caso contrário, retorna -1
    public int existeEmTitulares(Player jog){
        for(int i = 0; i < 11; i++)
            if(this.titulares[i].equals(jog)) return i;
        return -1;
    }

    public int existeEmTitulares(int nrCamisola){
        for(int index = 0; index < 11 ; index++)
            if(this.titulares[index].getShirtNumber() == nrCamisola)
                return index;
        return -1;
    }

    public boolean existeEmSuplentes(Player jog){
        return this.suplentes.containsValue(jog);
    }

    public boolean existeEmSuplentes(int nrCamisola){
        return this.suplentes.containsKey(nrCamisola);
    }

    public boolean existeNaEquipa(Player jog){ return Arrays.stream(this.titulares).anyMatch(j -> j.equals(jog)) || existeEmSuplentes(jog);}

    public boolean existeNaEquipa(int nrCamisola){ return Arrays.stream(this.titulares).anyMatch(jog -> jog != null && jog.getShirtNumber() == nrCamisola)
                                                       || existeEmSuplentes(nrCamisola);
    }


    /** Auxiliar na inserção de jogadores **/

    private static final Comparator<Player> nrCamisolaComparator = Comparator.comparingInt(Player::getShirtNumber);

    //Retorna o maior número de camisola presente na equipa
    //Usada para que em caso de colisão, seja atribuido automaticamente um número ainda não utilizado
    private int maxNrCamisola(){
        //Maior número de camisola nos titulares
        int maxTitulares = 0;
        Player maxTitular = Arrays.stream(this.titulares)
                                   .max(nrCamisolaComparator)
                                   .orElse(null);
        if(maxTitular != null) maxTitulares = maxTitular.getShirtNumber();

        //Maior número de camisola nos suplentes
        int maxSuplentes = 0;
        for(int nrCamisola : this.suplentes.keySet())
            if(nrCamisola > maxSuplentes) maxSuplentes = nrCamisola;

        return Math.max(maxTitulares, maxSuplentes);
    }

    //Recebe um jogador e retorna um clone dele com o número da camisola alterado em caso de um jogador com esse número já existir na equipa
    //Caso o nr de camisola nao seja válido, será atribuido um automaticamente (Nr de Camisola mais elevado + 1)
    private Player cloneAndModNrCamisola(Player jog){
        Player jogClone = jog.Clone();
        int nrCamisola  = jogClone.getShirtNumber();
        if(this.existeNaEquipa(nrCamisola))
            jogClone.setShirtNumber(this.maxNrCamisola() + 1);
        return jogClone;
    }

    /** Construtores **/

    public Equipa(String nome) {
        this.nome = nome;
        this.pontuacaoGlobal = 0;
        this.titulares = new Player[11];
        this.suplentes = new HashMap<>();
        this.formacao  = new Formacao();
    }

    public Equipa(String nome, int nrDefesas, int nrLaterais, int nrMedios, int nrAvancados) {
        this.nome = nome;
        this.pontuacaoGlobal = 0;
        this.titulares = new Player[11];
        this.suplentes = new HashMap<>();
        this.formacao  = new Formacao(nrDefesas, nrLaterais, nrMedios, nrAvancados);
    }

    public Equipa(Equipa e){
        this.nome            = e.getNome();
        this.pontuacaoGlobal = e.getTeamOverall();
        this.titulares       = e.getTitulares();
        this.suplentes       = e.getSuplentes();
        this.formacao        = e.getFormacao();
    }

    /** Clone **/

    public Equipa clone(){
        return new Equipa(this);
    }

    public Team Clone(){
        return this.clone();
    }

    /** Gets **/

    public String getNome() { return this.nome; }
    public String getName() { return this.nome; }

    public int getTeamOverall() { return this.pontuacaoGlobal; }

    // ** Funçoes sobre as Formacoes **
    public Formacao getFormacao() { return this.formacao.clone(); }

    // Recebe o index da posicao no array de titulares, e tendo em conta a formacao da equipa retorna a posicao a que esse index corresponde (Avancado, Medio, etc..)
    // Return:
    //      - 0 para GR
    //      - 1 para Defesa(/Lateral)
    //      - 2 para Medio
    //      - 3 para Avancado
    private int getPosicao(int index){
        int aux; //Variavel utilizada para evitar contas desnecessárias. Usada para guardar o indice onde se começa a guardar os médios

        //É Guarda-Redes?
        if(index == 0) return 0;

        //É Defesa ou Lateral?
        aux = getInicioMedios();
        if (index >= 1 && index < aux) return 1;

        //É Médio?
        if (index >= aux && index < aux + this.formacao.getNrMedios()) return 2;
        else return 3;
    }

    // Recebe o index da posicao no array de titulares, e tendo em conta a formacao da equipa retorna a área de jogo a que esse index corresponde (Esquerda, centro ou direita)
    // Return:
    //      - 0 para Esquerda
    //      - 1 para Centro
    //      - 2 para Direita
    private int getAreaDeJogo(int index, int posicao){
        //Para o guarda-redes nao interessa a posicao
        if(index == 0) return 1;

        //Se for defesa retorna 1 já que estes sao sempre centrais (dado que existem laterais)
        if(posicao == 1) {
            //Se for defesa
            if(index < this.getInicioLaterais()) return 1;

            //Se for lateral (Só podem existir 2)
            if(index - this.getInicioLaterais() == 0) //O lateral esquerdo encontra-se no primeiro slot para os laterais. Portanto o outro só pode ser lateral direito
                return 0;
            else
                return 2;
        }
        else {
            //Método de calculo da area de jogo é igual para os medios e avancados

            int nrJogadores, indexInicial;
            if(posicao == 2){
                nrJogadores  = this.formacao.getNrMedios();
                indexInicial = this.getInicioMedios();
            }
            else {
                nrJogadores  = this.formacao.getNrAvancados();
                indexInicial = this.getInicioAvancados();
            }

            //Se o número de jogadores dessa posicao for 1 ou 2 só podem ser centrais
            if(nrJogadores <= 2) return 1;
            else{
                //O indice mais baixo relativo a uma certa posicao(nr de jogadores > 2) corresponde ao jogador que joga na esquerda
                //O mais alto corresponde ao que joga á direita
                //As restantes sao dos jogadores centrais
                if(index == indexInicial) return 0;
                else if(index == indexInicial + nrJogadores - 1) return 2;
                else return 1;
            }
        }
    }


    public Player[] getTitulares(){
        Player[] cloneArray = new Player[11];
        for(int i = 0; i < 11 ; i++)
            if(this.titulares[i] != null)
                cloneArray[i] = this.titulares[i].Clone();
        return cloneArray;
    }

    public List<Player> getStartingPlayers(){
        return Arrays.stream(this.titulares)
                     .map(Player::Clone)
                     .collect(Collectors.toCollection(ArrayList::new));
    }

    public Map<Integer,Player> getSuplentes() {
        Map<Integer,Player> newMap = new HashMap<>();
        this.suplentes.entrySet().forEach(e -> newMap.put(e.getKey(), e.getValue().Clone()));
        return newMap;
    }

    public List<Player> getStrikers(){
        List<Player> ljog = new ArrayList<>();

        int n = this.getInicioAvancados();
        for(int i = n; i < 11; i++){
            ljog.add(this.getTitulares()[i].Clone());
        }

        return ljog;
    }

    public List<Player> getAttackers(){
        List<Player> ljog = new ArrayList<>();

        //Clonagem dos avancados para a lista que vai ser retornada
        int n = this.getInicioAvancados();
        for(int i = n; i < 11; i++){
            ljog.add(this.getTitulares()[i].Clone());
        }

        //Gerado um número de médios que vão auxiliar no ataque
        Random rand = new Random();
        int aleatorio = rand.nextInt(this.formacao.getNrMedios()+1);
        List<Player> medios = new ArrayList<>();

        //Copiados todos os médios para uma lista auxiliar
        for(int i = this.getInicioMedios(); i < n; i++){
            medios.add(this.getTitulares()[i]);
        }

        //Escolha aleatória e clonagem dos médios que vão auxiliar o ataque
        for(int i = 0; i < aleatorio; i++){
            int randomIndex = rand.nextInt(medios.size());
            ljog.add(medios.get(randomIndex).Clone());
            medios.remove(randomIndex);
        }
        
        return ljog;
    }

    public List<Player> getDefenders(){
        List<Player> ljog = new ArrayList<>();
        for(int i = 1; i < this.getInicioMedios() ; i++){
            ljog.add(this.getTitulares()[i].Clone());
        }
        return ljog;
    }

    /** Sets **/

    public void setNome(String nome) { this.nome = nome; }

    public void setFormacao(int nrDefesas, int nrLaterais, int nrMedios, int nrAvancados){
        this.formacao = new Formacao(nrDefesas, nrLaterais, nrMedios, nrAvancados);
    }

    public void setPontuacaoGlobal() {
        //Soma das pontuacoes gerais de cada jogador
        int somaPontuacoes = Arrays.stream(this.titulares)
                                   .filter(Objects::nonNull)
                                   .mapToInt(Player::getOverall)
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

    private int getInicioAvancados(){ return 1 + formacao.getNrDefesas() + formacao.getNrLaterais() + formacao.getNrMedios(); }

    //Esta função tenta inserir um jogador, que não exista na equipa, começando no indice 'index' fornecido até 'limite' - 1. Um jogador só é inserido numa posição null.
    //Retorna true caso consiga inserir, e false caso contrário.
    //Também atualiza a pontuação global da equipa
    private boolean tentaInserirTitular(Player jog, int index, int limite){
        //Verifica se já existe um jogador igual a este no plantel
        if(jog == null || this.existeNaEquipa(jog)) return false;

        //Clona e altera número da camisola caso seja necessário.
        Player jogClone = cloneAndModNrCamisola(jog);

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
    private boolean tentaInserirTitular(Player jog){
        return tentaInserirTitular(jog, 0, 11);
    }

    //Retorna:
    //  false se já existirem 11 titulares, ou se o jogador for 'null'
    //  true se for adicionado com sucesso. É adicionado na primeira vaga.
    public boolean addJogadorTitular(Player jog){
        return tentaInserirTitular(jog);
    }

    //Retorna:
    //  false se for 'null'
    //  true se for adicionado com sucesso. É adicionado na primeira vaga.
    public void addJogadorSuplente(Player jog){
        if(jog == null || this.existeNaEquipa(jog)) return;

        //Clona e altera número da camisola caso seja necessário.
        Player jogClone = cloneAndModNrCamisola(jog);

        this.suplentes.put(jogClone.getShirtNumber(), jogClone);
    }

    //Retorna:
    //  false se já existir um guarda-redes ou se já existir na equipa.
    //  true se for adicionado com sucesso.
    public boolean addJogadorComoGuardaRedes(Player jog){
        if(jog == null || this.titulares[0] != null || this.existeNaEquipa(jog)) return false;
        Player jogClone = cloneAndModNrCamisola(jog);
        this.titulares[0] = jogClone;
        this.setPontuacaoGlobal();
        return true;
    }

    // Para todas as funções addJogadorComo* :
    //      Retorna:
    //          false se já existirem jogadores em todos os indices especificos do array para essa posição, ou se já existir na equipa.
    //          true se for adicionado com sucesso.
    public boolean addJogadorComoDefesa(Player jog)  { return tentaInserirTitular(jog, this.getInicioDefesas(), this.getInicioLaterais()); }

    public boolean addJogadorComoLateral(Player jog) { return tentaInserirTitular(jog, this.getInicioLaterais(), this.getInicioMedios()); }

    public boolean addJogadorComoMedio(Player jog)   { return tentaInserirTitular(jog, this.getInicioMedios(), this.getInicioAvancados()); }

    public boolean addJogadorComoAvancado(Player jog){ return tentaInserirTitular(jog, this.getInicioAvancados(), 11); }


    /** Substituição de jogadores **/

    //Retorna:
    //  false se não existir algum dos jogadores a titular
    //  true  se a troca for feita com sucesso
    private boolean trocaJogadoresTitulares(int nrCamisolaJog1, int nrCamisolaJog2){
        int indexJog1 = this.existeEmTitulares(nrCamisolaJog1); if(indexJog1 == -1) return false;
        int indexJog2 = this.existeEmTitulares(nrCamisolaJog2); if(indexJog2 == -1) return false;

        Player tmp = this.titulares[indexJog1];
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

        Player tmp = this.suplentes.get(nrCamisolaJog2);
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

    /** Verifica Equipa **/

    public boolean prontaParaJogar(){
        return Arrays.stream(this.titulares).filter(Objects::nonNull).count() == 11;
    }
}
