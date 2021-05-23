import java.sql.Time;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

import static java.lang.Integer.compare;


public class Jogo {

    private Equipa equipaCasa;
    private Equipa equipaFora;
    private int golosCasa;
    private int golosFora;
    private boolean posseDeBola; //'true' se a equipa da casa é que tem a posse de bola, 'false' em caso contrário
    private float time;
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
        this.equipaCasa = equipaCasa.clone();
        this.equipaFora = equipaFora.clone();
        this.golosCasa = 0;
        this.golosCasa = 0;
        this.posseDeBola = true;
        this.time = 0;
    }

    public boolean ataque() {

    }

    public boolean remate() {
        Random rand = new Random();
        Jogador striker;
        Jogador guardaRedes;

        if(posseDeBola) {
            List<Jogador> atacantes = this.equipaCasa.getAvancados().clone();

            striker = atacantes.get(rand.nextInt(atacantes.size())).clone();

            guardaRedes = this.equipaFora.getTitulares().get(0).clone();
        }
        else{
            List<Jogador> atacantes = this.equipaFora.getAvancados().clone();

            striker = atacantes.get(rand.nextInt(atacantes.size())).clone();

            guardaRedes = this.equipaCasa.getTitulares().get(0).clone();
        }

        int total;
        if(getRandomBoolean()) total = guardaRedes.getPontuacaoGeral() + (striker.getRemate() + striker.getEfeito()) / 2;

        else { total = guardaRedes.getPontuacaoGeral() + striker.getJogoDeCabeca(); }

        int aleatorio = rand.nextInt(total);

        if(compare(aleatorio , guardaRedes.getPontuacaoGeral())<1) return false;
        else return true;

    }

    public void disputaDeBola(){
        Random rand = new Random();
        List<Jogador> atacantes;
        List<Jogador> defensores;

        int atkPower;

        if(posseDeBola) {
            atacantes = this.equipaCasa.getAvancados().clone();

            defensores = this.equipaFora.getAvancados().clone();
        }
        else{
            atacantes  = this.equipaFora.getAvancados().clone();

            defensores = this.equipaCasa.getAvancados().clone();
        }

        atkPower = sumPontGeral(atacantes);

        int total = 2*atkPower + sumPontGeral(defensores);

        int aleatorio = rand.nextInt(total);

        if(compare(aleatorio , 2*atkPower) <1) return true;
        else return false;
    }

    public int sumPontGeral(List<Jogador> ljog){
        int sum=0;
        for(Jogador jog : ljog){
            sum += jog.getPontuacaoGeral();
        }
        return sum;
    }


    public void correJogo(){
        /*     while(gerrentPart()<getTotalParts()) {
            while (time < (getTotalTime() / gealParts()) ) {
                disputaDeBola();

                if(ataque()) {
                    if(remate()){
                        if(posseDeBola) golosCasa++;
                        else golosFora++;
                    }
                }

                reversePosseDeBola();
            }

            time = (getTotalTime() / getTotalParts())*getCurrentPart; //formatar o tempo

            CurrentPart +1;
        }

}
