import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Catalogo {
    private List<InfoJogo> jogos;
    private Map<String, Team> equipas;

    public Catalogo(){
        this.jogos = new ArrayList<>();
        this.equipas = new HashMap<>();
    }

    //getters
    public List<Jogo> getJogos() {
        return jogos;
    }

    public Map<String, Team> getEquipas() {
        return equipas;
    }

    public Team getEquipa(String name){
        if(this.equipas.containsKey(name)) return this.equipas.get(name).Clone();
        else return null;
    }

    public InfoJogo getJogo(int n){

    }


    //setters
    public void setJogos(List<Jogo> jogos) {
        this.jogos = jogos;
    }

    public void setEquipas(Map<String, Equipa> equipas) {
        this.equipas = equipas;
    }

    //comparator
    
}
