public class Eventos implements Events {
    private String descricao;
    private float tempo;

    public Eventos(String descricao, float tempo){
        this.descricao = descricao;
        this.tempo = tempo;
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

    public void setTimeStamp(float tempo) {
        this.tempo = tempo;
    }

}
