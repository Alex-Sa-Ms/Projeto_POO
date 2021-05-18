import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

public class Jogo{
    
    private Team equipaCasa;
    private Team equipaFora;
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


    public Jogo(int tempoCadaParte, int nrPartes, Team equipaCasa, Team equipaFora){
        this.equipaCasa = equipaCasa.clone();
        this.equipaFora = equipaFora.clone();
        this.golosCasa = 0;
        this.golosCasa = 0;
        this.posseDeBola = true;
        this.time = 0;
    }

    public boolean ataque(){

    }

    public boolean remate(){

    }

    public void disputaDeBola(){

    }
    public void correJogo(){
        /*     while(getCurrentPart()<getTotalParts()) {
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
