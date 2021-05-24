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

    String getSucessfulStrikeEvent(String jog){
        Random rand = new Random();
        int aleatorio = rand.nextInt(3);

        if(aleatorio == 0) return ("Grande golo de " + jog + "!");
        else if(aleatorio == 1) return ("Golo! Mas que finalização de" + jog + "!");
        else return ("Inacreditável! Fantástico remate de " + jog + "!");
    }

    String getFailedStrikeEvent(String jog){
        Random rand = new Random();
        int aleatorio = rand.nextInt(3);

        if(aleatorio == 0) return ("A bola de " + jog + " acertou na trave!");
        else if(aleatorio == 1) return ("Falhou! Mas que oportunidade desperdiçada por" + jog + "!");
        else return ("Inacreditável! Parecia certeiro mas " + jog + " conseguiu falhar!");
    }

    String getSucessfulAttackEvent(String equipa){
        Random rand = new Random();
        int aleatorio = rand.nextInt(3);

        if(aleatorio == 0) return (equipa + " passou a linha defensiva e ...");
        else if(aleatorio == 1) return (equipa + " tem oportunidade de finalizar e ...");
        else return (equipa + " desmontam a defesa adversária e ...");
    }

    String getFailedAttackEvent(String equipa){
        Random rand = new Random();
        int aleatorio = rand.nextInt(3);

        if(aleatorio == 0) return (equipa + " recupera da situação de perigo e contra-ataca!");
        else if(aleatorio == 1) return (equipa + " desarma o ataque adversário e avança!");
        else return ("A linha defensiva do " + equipa + " não deixou a bola passar e começou a atacar");

    }

    String getSucessfulDisputaEvent(String equipa){
        Random rand = new Random();
        int aleatorio = rand.nextInt(3);

        if(aleatorio == 0) return (equipa + " continua o seu ataque!");
        else if(aleatorio == 1) return (equipa + " gradualmente conquista o campo!");
        else return (equipa + " avança no campo adversário!");
    }

    String getFailedDisputaEvent(String equipa){
        Random rand = new Random();
        int aleatorio = rand.nextInt(3);

        if(aleatorio == 0) return (equipa + " recupera a bola!");
        else if(aleatorio == 1) return (equipa + " tomou posse de bola!");
        else return (equipa + " ganhou a disputa de bola!");
    }

    String getStartEvent(){
        return ("O jogo começou!");
    }

    String getStart2PartEvent(){
        return ("A segunda parte do jogo começou!");
    }

    String getFinishEvent(){
        return ("O jogo terminou!");
    }

    String getStart1PartEvent(){
        return ("A primeira parte do jogo terminou!");
    }

    String getSwapEvent(String jog, String sub){
        return ("Jogador " + jog + " é substituido por " + sub + "!");
    }
}
