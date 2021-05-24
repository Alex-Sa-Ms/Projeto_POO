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

}
