import java.util.Random;

public class Eventos{
    private String descricao;
    private int tempo;

    public Eventos(String descricao, int tempo){
        this.descricao = descricao;
        this.tempo     = tempo;
    }

    public String getEventDescription(){
        return this.descricao;
    }

    public float getTimeStamp(){
        return this.tempo;
    }


    public void setEventDescription(String descricao) {
        this.descricao = descricao;
    }

    public void setTimeStamp(int tempo) {
        this.tempo = tempo;
    }

    public static Eventos getSucessfulStrikeEvent(String jog, int time){
        Random rand = new Random();
        int aleatorio = rand.nextInt(3);

        if(aleatorio == 0) return new Eventos("Grande golo de " + jog + "!", time);
        else if(aleatorio == 1) return new Eventos("Golo! Mas que finalização de" + jog + "!", time);
        else return new Eventos("Inacreditável! Mas que golo! Fantástico remate de " + jog + "!", time);
    }

    public static Eventos getFailedStrikeEvent(String jog, int time){
        Random rand = new Random();
        int aleatorio = rand.nextInt(3);

        if(aleatorio == 0) return new Eventos("A bola de " + jog + " acertou na trave!", time);
        else if(aleatorio == 1) return new Eventos("Falhou! Mas que oportunidade desperdiçada por" + jog + "!", time);
        else return new Eventos("Inacreditável! Parecia certeiro mas " + jog + " conseguiu falhar!", time);
    }

    public static Eventos getSucessfulAttackEvent(String equipa, int time){
        Random rand = new Random();
        int aleatorio = rand.nextInt(3);

        if(aleatorio == 0) return new Eventos(equipa + " passou a linha defensiva e ...", time);
        else if(aleatorio == 1) return new Eventos(equipa + " tem oportunidade de finalizar e ...", time);
        else return new Eventos(equipa + " desmontam a defesa adversária e ...", time);
    }

    public static Eventos getFailedAttackEvent(String equipa, int time){
        Random rand = new Random();
        int aleatorio = rand.nextInt(3);

        if(aleatorio == 0) return new Eventos(equipa + " recupera da situação de perigo e contra-ataca!", time);
        else if(aleatorio == 1) return new Eventos(equipa + " desarma o ataque adversário e avança!", time);
        else return new Eventos("A linha defensiva do " + equipa + " não deixou a bola passar e começou a atacar", time);

    }

    public static Eventos getSucessfulDisputaEvent(String equipa, int time){
        Random rand = new Random();
        int aleatorio = rand.nextInt(3);

        if(aleatorio == 0) return new Eventos(equipa + " continua o seu ataque!", time);
        else if(aleatorio == 1) return new Eventos(equipa + " gradualmente conquista o campo!", time);
        else return new Eventos(equipa + " avança no campo adversário!", time);
    }

    public static Eventos getFailedDisputaEvent(String equipa, int time){
        Random rand = new Random();
        int aleatorio = rand.nextInt(3);

        if(aleatorio == 0) return new Eventos(equipa + " recupera a bola!", time);
        else if(aleatorio == 1) return new Eventos(equipa + " tomou posse de bola!", time);
        else return new Eventos(equipa + " ganhou a disputa de bola!", time);
    }

    public static Eventos getStartEvent(int time){
        return new Eventos ("O jogo começou!", time);
    }

    public static Eventos getStart2PartEvent(int time){
        return new Eventos ("A segunda parte do jogo começou!", time);
    }

    public static Eventos getFinishEvent(int time, int golosCasa, int golosFora, String nomeEquipaCasa, String nomeEquipaFora){
        if(golosCasa == golosFora) return new Eventos ("O jogo terminou com o empate entre as duas equipas!\nResultado: " + golosCasa + " - " + golosFora, time);
        else if(golosCasa > golosFora) return new Eventos ("O jogo terminou com uma grande vitória de " + nomeEquipaCasa + "!\nResultado: " + golosCasa + " - " + golosFora, time);
        else return new Eventos ("O jogo terminou com a vitória de " + nomeEquipaFora + "!\nResultado: " + golosCasa + " - " + golosFora, time);
    }

    public static Eventos getFinish1PartEvent(int time){
        return new Eventos ("A primeira parte do jogo terminou!", time);
    }

    public static Eventos getSwapEvent(String jog, String sub, int time){
        return new Eventos("Jogador " + jog + " é substituido por " + sub + "!", time);
    }

    public String toString(){
        return tempo + "' " + this.descricao;
    }
}
