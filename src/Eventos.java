public class Eventos implements Events {
    private String descricao;
    private float tempo;
    private int parte;

    public String getEventDescription(){
        return this.descricao;
    }

    public float getTimeStamp(){
        return this.tempo;
    }

    public float getPart(){
        return this.parte;
    }
}
