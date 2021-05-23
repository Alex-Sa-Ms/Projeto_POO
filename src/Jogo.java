import java.sql.Time;
import java.util.*;
import java.util.stream.Collectors;

import static java.lang.Integer.compare;


public class Jogo implements Game{

    private Team equipaCasa;
    private Team equipaFora;
    private int golosCasa;
    private int golosFora;
    private boolean posseDeBola; //'true' se a equipa da casa é que tem a posse de bola, 'false' em caso contrário
    private float time;
    private int parte;
    private List<Events> eventos;
    /*
    private List<Jogador> ataque;
    private List<Jogador> defesa;
    private Jogador guardaRedes;
    private Jogador goleador;
    private int mediaAtacante;
    private int mediaDefensiva;
    */


    public Jogo(int tempoCadaParte, int nrPartes, Team equipaCasa, Team equipaFora) {
        this.equipaCasa = equipaCasa.Clone();
        this.equipaFora = equipaFora.Clone();
        this.golosCasa = 0;
        this.golosCasa = 0;
        this.posseDeBola = true;
        this.time = 0;
    }

    public boolean ataque() {
        Random rand = new Random();
        List<Jogador> atacantes;
        List<Jogador> defensores;

        int atkPower;

        if(posseDeBola) {
            atacantes = this.equipaCasa.getAttackers().stream().map(Jogador::clone).collect(Collectors.toList());

            defensores = this.equipaFora.getDefenders().stream().map(Jogador::clone).collect(Collectors.toList());
        }
        else{
            atacantes  = this.equipaFora.getAttackers().stream().map(Jogador::clone).collect(Collectors.toList());

            defensores = this.equipaCasa.getDefenders().stream().map(Jogador::clone).collect(Collectors.toList());
        }

        atkPower = sumPontGeral(atacantes);

        int total = 2*atkPower + sumPontGeral(defensores);

        int aleatorio = rand.nextInt(total);

        if (aleatorio < 2 * atkPower){
            if(posseDeBola) this.eventos.add("Equipa " + this.equipaCasa.getNome() + " finaliza e ");
            else this.eventos.add("Equipa " + this.equipaFora.getNome() + " finaliza e ");
            return true;
        }
        else{
            if(posseDeBola) this.eventos.add("Equipa " + this.equipaFora.getNome() + " interceta o ataque!!");
            else this.eventos.add("Equipa " + this.equipaCasa.getNome() + " interceta o ataque!!");
            return false;
        }
    }

    public boolean remate() {
        Random rand = new Random();
        Jogador striker;
        Jogador guardaRedes;

        if(posseDeBola) {
            List<Jogador> atacantes = this.equipaCasa.getStrikers().stream().map(Jogador::clone).collect(Collectors.toList());

            striker = atacantes.get(rand.nextInt(atacantes.size())).clone();

            guardaRedes = this.equipaFora.getStartingPlayers()[0].clone();
        }
        else{
            List<Jogador> atacantes = this.equipaFora.getStrikers().stream().map(Jogador::clone).collect(Collectors.toList());

            striker = atacantes.get(rand.nextInt(atacantes.size())).clone();

            guardaRedes = this.equipaCasa.getStartingPlayers()[0].clone();
        }

        int total;
        if(rand.nextBoolean()) total = guardaRedes.getPontuacaoGeral() + striker.getRemate();

        else  total = guardaRedes.getPontuacaoGeral() + striker.getJogoDeCabeca();

        int aleatorio = rand.nextInt(total);

        float playTime = rand.nextFloat(3);

        this.time += playTime;

        if (aleatorio < guardaRedes.getPontuacaoGeral()) {
            this.eventos.add("Jogador " + striker.getNome() + "rematou e falhou!! Mas que falhanço!!");
            return false;
        }
        else{
            this.eventos.add("Golo !! Jogador " + striker.getNome() + "rematou e marcou!!");
            return true;
        }

    }

    public void disputaDeBola(){
        Random rand = new Random();
        List<Jogador> atacantes;
        List<Jogador> defensores;

        int atkPower;

        if(posseDeBola) {
            atacantes = Arrays.asList(this.equipaCasa.getStartingPlayers().clone());

            defensores = Arrays.asList(this.equipaFora.getStartingPlayers().clone());
        }
        else{
            atacantes  = Arrays.asList(this.equipaFora.getStartingPlayers().clone());

            defensores = Arrays.asList(this.equipaCasa.getStartingPlayers().clone());
        }

        atkPower = sumPontGeral(atacantes);

        int total = sumPontGeral(atacantes) + sumPontGeral(defensores);

        int aleatorio = rand.nextInt(total);

        if(aleatorio >= sumPontGeral(atacantes)) {


            if(posseDeBola) this.eventos.add(new Eventos("Equipa " + this.equipaFora.getName() + "tomou posse de bola.", this.getCurrentTime()));
            else this.eventos.add("Equipa " + this.equipaCasa.getName() + "tomou posse de bola.");
            this.posseDeBola = !this.posseDeBola;
        }
        else {
            if(posseDeBola) this.eventos.add("Equipa " + this.equipaCasa.getName() + "continua o seu ataque.");
            else this.eventos.add("Equipa " + this.equipaFora.getName() + "continua o seu ataque.");
        }

        float playTime = rand.nextFloat(4);

        this.time += 2+playTime;
    }

    public int sumPontGeral(List<Jogador> ljog){
        int sum=0;
        for(Jogador jog : ljog){
            sum += jog.getPontuacaoGeral();
        }
        return sum;
    }


    public void correJogo(){
        while(this.parte <= 2) {
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

            this.parte ++;
            time = 45 * (parte-1); //formatar o tempo

        }
}
