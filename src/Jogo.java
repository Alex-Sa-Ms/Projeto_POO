import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class Jogo extends Observable {

    private final Team equipaCasa;
    private final Team equipaFora;
    private int golosCasa;
    private int golosFora;
    private int substituicoesCasa;
    private int substituicoesFora;
    private boolean posseDeBola; //'true' se a equipa da casa é que tem a posse de bola, 'false' em caso contrário
    private int time;
    private int parte;
    private List<Eventos> eventos;
    private InfoJogo resumoJogo;

    public Jogo(Team equipaCasa, Team equipaFora) {
        this.equipaCasa        = equipaCasa.Clone();
        this.equipaFora        = equipaFora.Clone();
        this.golosCasa         = 0;
        this.golosFora         = 0;
        this.substituicoesCasa = 0;
        this.substituicoesFora = 0;
        this.posseDeBola       = true;
        this.time              = 0;
        this.parte             = 1;
        this.eventos           = new ArrayList<>();
        this.resumoJogo        = new InfoJogo(equipaCasa.getName(),equipaFora.getName(), LocalDate.now(), this.getNumerosCamisolasTitulares(equipaCasa), this.getNumerosCamisolasTitulares(equipaFora));
    }

    //Auxiliar Construtor InfoJogo
    private List<Integer> getNumerosCamisolasTitulares(Team team){
        return team.getStartingPlayers().stream().map(entry -> entry.getKey().getShirtNumber()).collect(Collectors.toList());
    }


    public int getSubstituicoesCasa() {
        return substituicoesCasa;
    }

    public int getSubstituicoesFora() {
        return substituicoesFora;
    }

    public Team getEquipa(String nomeEquipa){
        if(this.equipaCasa.getName().equals(nomeEquipa))
            return this.equipaCasa.Clone();
        else if(this.equipaFora.getName().equals(nomeEquipa))
            return this.equipaFora.Clone();
        else
            return null;
    }

    /** substituicao **/

    public int substituicao(int numeroCamisolaASubstituir, int numeroCamisolaSubstituto, String nomeEquipa){
        Team equipa;
        int r = -1;

        if(nomeEquipa.equals(this.equipaCasa.getName()) && this.substituicoesCasa < 3) {
            equipa = equipaCasa;
            this.resumoJogo.newSubstituicaoCasa(numeroCamisolaASubstituir,numeroCamisolaSubstituto);
            r = equipa.substituicao(numeroCamisolaASubstituir, numeroCamisolaSubstituto);
            this.substituicoesCasa += r;

            if(this.substituicoesCasa == 3) return -1;
        }
        else if(nomeEquipa.equals(this.equipaFora.getName()) && this.substituicoesFora < 3) {
            equipa = equipaFora;
            this.resumoJogo.newSubstituicaoFora(numeroCamisolaASubstituir,numeroCamisolaSubstituto);
            r = equipa.substituicao(numeroCamisolaASubstituir, numeroCamisolaSubstituto);
            this.substituicoesFora += r;

            if(this.substituicoesFora == 3) return -1;
        }

        return r;
    }

    /** Gera Eventos **/

    public int sumPontGeral(List<AbstractMap.SimpleEntry<Player,Integer>> ljog){
        return ljog.stream()
                .mapToInt(AbstractMap.SimpleEntry::getValue)
                .sum();
    }


    public boolean ataque() {
        Random rand = new Random();
        List<AbstractMap.SimpleEntry<Player,Integer>> atacantes;
        List<AbstractMap.SimpleEntry<Player,Integer>> defensores;

        int atkPower;

        if(posseDeBola) {
            atacantes  = this.equipaCasa.getAttackers();

            defensores = this.equipaFora.getDefenders();
        }
        else{
            atacantes  = this.equipaFora.getAttackers();

            defensores = this.equipaCasa.getDefenders();
        }

        atkPower = sumPontGeral(atacantes);

        int total = 2 * atkPower + sumPontGeral(defensores);

        int aleatorio = rand.nextInt(total);

        if (aleatorio < 2 * atkPower){
            if(posseDeBola) this.eventos.add(Eventos.getSucessfulAttackEvent( this.equipaCasa.getName(), this.time));
            else this.eventos.add(Eventos.getSucessfulAttackEvent( this.equipaFora.getName(), this.time));

            //Notifica os observadores que a lista de eventos foi atualizada
            setChanged();
            notifyObservers(this.eventos);

            return true;
        }
        else{
            if(posseDeBola) this.eventos.add(Eventos.getFailedAttackEvent( this.equipaFora.getName(), this.time));
            else this.eventos.add(Eventos.getFailedAttackEvent( this.equipaCasa.getName(), this.time));

            //Notifica os observadores que a lista de eventos foi atualizada
            setChanged();
            notifyObservers(this.eventos);

            return false;
        }
    }

    public boolean remate() {
        Random rand = new Random();
        AbstractMap.SimpleEntry<Player,Integer> striker;
        AbstractMap.SimpleEntry<Player,Integer> guardaRedes;

        if(posseDeBola) {
            List<AbstractMap.SimpleEntry<Player,Integer>> atacantes = this.equipaCasa.getStrikers();
            striker     = atacantes.get(rand.nextInt(atacantes.size()));
            guardaRedes = this.equipaFora.getGoalKeeper();
        }
        else{
            List<AbstractMap.SimpleEntry<Player,Integer>> atacantes = this.equipaFora.getStrikers();
            striker     = atacantes.get(rand.nextInt(atacantes.size()));
            guardaRedes = this.equipaCasa.getGoalKeeper();
        }

        int total = guardaRedes.getValue();
        if(rand.nextBoolean())
            total += striker.getKey().getStrike() * 0.2;
        else
            total += striker.getKey().getHeadGame() * 0.2;

        int aleatorio = rand.nextInt(total);

        int playTime = rand.nextInt(2);

        this.time += playTime;

        if (aleatorio < guardaRedes.getValue()) {
            this.eventos.add(Eventos.getFailedStrikeEvent( striker.getKey().getName(), this.time));

            //Notifica os observadores que a lista de eventos foi atualizada
            setChanged();
            notifyObservers(this.eventos);

            return false;
        }
        else{
            this.eventos.add(Eventos.getSucessfulStrikeEvent( striker.getKey().getName(), this.time));

            //Notifica os observadores que a lista de eventos foi atualizada
            setChanged();
            notifyObservers(this.eventos);

            if(posseDeBola) this.golosCasa++;
            else this.golosFora++;

            return true;
        }

    }

    public void disputaDeBola(){
        Random rand = new Random();
        List<AbstractMap.SimpleEntry<Player,Integer>> atacantes;
        List<AbstractMap.SimpleEntry<Player,Integer>> defensores;

        int atkPower;

        if(posseDeBola) {
            atacantes = this.equipaCasa.getStartingPlayers();

            defensores = this.equipaFora.getStartingPlayers();
        }
        else{
            atacantes  = this.equipaFora.getStartingPlayers();

            defensores = this.equipaCasa.getStartingPlayers();
        }

        atkPower = sumPontGeral(atacantes);

        int total = atkPower + sumPontGeral(defensores);

        int aleatorio = rand.nextInt(total);

        if(aleatorio >= atkPower) {

            if(posseDeBola) this.eventos.add(Eventos.getFailedDisputaEvent(this.equipaFora.getName(), this.time));
            else this.eventos.add(Eventos.getFailedDisputaEvent(this.equipaCasa.getName(), this.time));

            this.posseDeBola = !this.posseDeBola;
        }
        else {
            if(posseDeBola) this.eventos.add(Eventos.getSucessfulDisputaEvent(this.equipaCasa.getName(), this.time));
            else this.eventos.add(Eventos.getSucessfulDisputaEvent(this.equipaFora.getName(), this.time));
        }

        //Notifica os observadores que a lista de eventos foi atualizada
        setChanged();
        notifyObservers(this.eventos);

        int playTime = rand.nextInt(4);

        this.time += (2 + playTime);
    }

    public InfoJogo getResumoJogo() {
        return resumoJogo.clone();
    }

    /** Simula Jogo **/

    //Retorna o número da parte que simulou, ou -1 se já tivesse terminado
    public int correJogo(){
        if(this.parte <= 2) {
            if(this.parte == 1)
                this.eventos.add(Eventos.getStartEvent(this.time));
            else {
                this.eventos.add(Eventos.getStart2PartEvent(this.time));
                this.posseDeBola = false; //Atribuir a posse de bola á equipa de fora
            }

            //Notifica os observadores que a lista de eventos foi atualizada
            setChanged();
            notifyObservers(this.eventos);

            while (this.time < 45 * this.parte) {
                disputaDeBola();

                if (ataque()) remate();

                this.posseDeBola = !this.posseDeBola;
            }

            if (this.parte == 1) {
                this.eventos.add(Eventos.getFinish1PartEvent(this.time));
                this.time = 45;
            }
            else {
                this.eventos.add(Eventos.getFinishEvent(this.time, this.golosCasa, this.golosFora, this.equipaCasa.getName(), this.equipaFora.getName()));
                this.resumoJogo.setGolosCasa(this.golosCasa);
                this.resumoJogo.setGolosFora(this.golosFora);
            }

            //Notifica os observadores que a lista de eventos foi atualizada
            setChanged();
            notifyObservers(this.eventos);

            this.parte++;
            return this.parte - 1;
        }
        return -1;
    }
}
