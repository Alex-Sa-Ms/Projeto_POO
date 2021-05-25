import java.util.*;
import java.util.stream.Collectors;

public class Jogo{

    private Team equipaCasa;
    private Team equipaFora;
    private int golosCasa;
    private int golosFora;
    private boolean posseDeBola; //'true' se a equipa da casa é que tem a posse de bola, 'false' em caso contrário
    private int time;
    private int parte;
    private List<Eventos> eventos;

    public Jogo(Team equipaCasa, Team equipaFora) {
        this.equipaCasa  = equipaCasa.Clone();
        this.equipaFora  = equipaFora.Clone();
        this.golosCasa   = 0;
        this.golosCasa   = 0;
        this.posseDeBola = true;
        this.time        = 0;
        this.parte       = 1;
        this.eventos     = new ArrayList<>();
    }

    public boolean ataque() {
        Random rand = new Random();
        List<Player> atacantes;
        List<Player> defensores;

        int atkPower;

        if(posseDeBola) {
            atacantes = this.equipaCasa.getAttackers().stream().map(Player::Clone).collect(Collectors.toList());

            defensores = this.equipaFora.getDefenders().stream().map(Player::Clone).collect(Collectors.toList());
        }
        else{
            atacantes  = this.equipaFora.getAttackers().stream().map(Player::Clone).collect(Collectors.toList());

            defensores = this.equipaCasa.getDefenders().stream().map(Player::Clone).collect(Collectors.toList());
        }

        atkPower = sumPontGeral(atacantes);

        int total = 2*atkPower + sumPontGeral(defensores);

        int aleatorio = rand.nextInt(total);

        if (aleatorio < 2 * atkPower){
            if(posseDeBola) this.eventos.add(Eventos.getSucessfulAttackEvent( this.equipaCasa.getName(), time));
            else this.eventos.add(Eventos.getSucessfulAttackEvent( this.equipaFora.getName(), time));
            return true;
        }
        else{
            if(posseDeBola) this.eventos.add(Eventos.getFailedAttackEvent( this.equipaFora.getName(), time));
            else this.eventos.add(Eventos.getFailedAttackEvent( this.equipaCasa.getName(), time));
            return false;
        }
    }

    public boolean remate() {
        Random rand = new Random();
        Player striker;
        Player guardaRedes;

        if(posseDeBola) {
            List<Player> atacantes = this.equipaCasa.getStrikers().stream().map(Player::Clone).collect(Collectors.toList());

            striker = atacantes.get(rand.nextInt(atacantes.size())).Clone();

            guardaRedes = this.equipaFora.getStartingPlayers().get(0).Clone();
        }
        else{
            List<Player> atacantes = this.equipaFora.getStrikers().stream().map(Player::Clone).collect(Collectors.toList());

            striker = atacantes.get(rand.nextInt(atacantes.size())).Clone();

            guardaRedes = this.equipaCasa.getStartingPlayers().get(0).Clone();
        }

        int total;
        if(rand.nextBoolean())
            total = guardaRedes.getOverall(0,0) + striker.getStrike();
        else
            total = guardaRedes.getOverall(0,0) + striker.getHeadGame();

        int aleatorio = rand.nextInt(total);

        int playTime = rand.nextInt(3);

        this.time += playTime;

        if (aleatorio < guardaRedes.getOverall(0,0)) {
            this.eventos.add(Eventos.getFailedStrikeEvent( striker.getName(), time));
            return false;
        }
        else{
            this.eventos.add(Eventos.getSucessfulStrikeEvent( striker.getName(), time));

            if(posseDeBola) this.golosCasa++;
            else this.golosFora++;

            return true;
        }

    }

    public void disputaDeBola(){
        Random rand = new Random();
        List<Player> atacantes;
        List<Player> defensores;

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

        int total = sumPontGeral(atacantes) + sumPontGeral(defensores);

        int aleatorio = rand.nextInt(total);

        if(aleatorio >= sumPontGeral(atacantes)) {

            if(posseDeBola) this.eventos.add(Eventos.getFailedDisputaEvent(this.equipaFora.getName(), time));
            else this.eventos.add(Eventos.getFailedDisputaEvent(this.equipaCasa.getName(), time));

            this.posseDeBola = !this.posseDeBola;
        }
        else {
            if(posseDeBola) this.eventos.add(Eventos.getSucessfulDisputaEvent(this.equipaCasa.getName(), time));
            else this.eventos.add(Eventos.getSucessfulDisputaEvent(this.equipaFora.getName(), time));
        }

        int playTime = rand.nextInt(4);

        this.time += 2 + playTime;
    }

    public int sumPontGeral(List<Player> ljog){
        int sum=0;
        int pos = 0;
        for(Player jog : ljog){
            sum += jog.getOverall();
        }
        return sum;
    }


    public void correJogo(){
        this.eventos.add(Eventos.getStartEvent(time));

        while(this.parte <= 2) {
            if(this.parte == 2) this.eventos.add(Eventos.getStart2PartEvent(time));

            while (time < 45 * this.time ) {
                disputaDeBola();

                if(ataque()) {
                    if(remate()){
                        if(posseDeBola) golosCasa++;
                        else golosFora++;
                    }
                }

                this.posseDeBola = !this.posseDeBola;
            }
            if(this.parte == 1) this.eventos.add(Eventos.getFinish1PartEvent(time));

            this.parte ++;
            time = 45 * (parte-1); //formatar o tempo
        }
        this.eventos.add(Eventos.getFinishEvent(time));
    }
}
