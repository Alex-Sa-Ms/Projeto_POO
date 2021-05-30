import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class InfoJogo {
    private String equipaCasa;
    private String equipaFora;
    private LocalDate data;
    private int golosCasa;
    private int golosFora;
    private List<Integer> jogadoresIniciais;
    private Map<Integer, Integer> substituices;

    public InfoJogo() {
        this.equipaCasa = "";
        this.equipaFora = "";
        this.data = LocalDate.now();
        this.golosCasa = 0;
        this.golosFora = 0;
        this.jogadoresIniciais = new ArrayList<>();
        this.substituices = new HashMap<>();
    }

    public InfoJogo(String equipaCasa, String equipaFora, LocalDate data, int golosCasa, int golosFora, List<Integer> jogadoresIniciais, Map<Integer, Integer> substituices) {
        this.equipaCasa = equipaCasa;
        this.equipaFora = equipaFora;
        this.data = data;
        this.golosCasa = golosCasa;
        this.golosFora = golosFora;
        this.jogadoresIniciais = jogadoresIniciais.stream().collect(Collectors.toList());
        this.substituices = substituices.entrySet().stream().collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    //getters
    public String getEquipaCasa() {
        return this.equipaCasa;
    }

    public LocalDate getData() {
        return this.data;
    }

    public String getEquipaFora() {
        return this.equipaFora;
    }

    public int getGolosCasa() {
        return this.golosCasa;
    }

    public int getGolosFora() {
        return this.golosFora;
    }

    public List<Integer> getJogadoresIniciais() {
        return this.jogadoresIniciais.stream().collect(Collectors.toList());
    }

    public Map<Integer, Integer> getSubstituices() {
        return this.substituices.entrySet().stream().collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    //setters
    public void setEquipaCasa(String equipaCasa) {
        this.equipaCasa = equipaCasa;
    }
    public void setEquipaFora(String equipaFora) {
        this.equipaFora = equipaFora;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public void setGolosCasa(int golosCasa) {
        this.golosCasa = golosCasa;
    }

    public void setGolosFora(int golosFora) {
        this.golosFora = golosFora;
    }

    public void setJogadoresIniciais(List<Integer> jogadoresIniciais) {
        this.jogadoresIniciais = jogadoresIniciais.stream().collect(Collectors.toList());
    }

    public void setSubstituices(Map<Integer, Integer> substituices) {
        this.substituices = substituices.entrySet().stream().collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }
}
