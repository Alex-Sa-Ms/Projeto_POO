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
    private List<Integer> jogadoresIniciaisCasa;
    private List<Integer> jogadoresIniciaisFora;
    private Map<Integer, Integer> substituicoesCasa;
    private Map<Integer, Integer> substituicoesFora;

    public InfoJogo() {
        this.equipaCasa = "";
        this.equipaFora = "";
        this.data = LocalDate.now();
        this.golosCasa = 0;
        this.golosFora = 0;
        this.jogadoresIniciaisCasa = new ArrayList<>();
        this.jogadoresIniciaisFora = new ArrayList<>();
        this.substituicoesCasa = new HashMap<>();
        this.substituicoesFora = new HashMap<>();
    }

    public InfoJogo(String equipaCasa, String equipaFora, LocalDate data, int golosCasa, int golosFora, List<Integer> jogadoresIniciaisCasa, List<Integer> jogadoresIniciaisFora, Map<Integer, Integer> substituicoesCasa, Map<Integer, Integer> substituicoesFora) {
        this.equipaCasa = equipaCasa;
        this.equipaFora = equipaFora;
        this.data = data;
        this.golosCasa = golosCasa;
        this.golosFora = golosFora;
        this.jogadoresIniciaisCasa = new ArrayList<>(jogadoresIniciaisCasa);
        this.jogadoresIniciaisFora = new ArrayList<>(jogadoresIniciaisFora);
        this.substituicoesCasa = substituicoesCasa.entrySet().stream().collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
        this.substituicoesFora = substituicoesFora.entrySet().stream().collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    public InfoJogo(InfoJogo ij){
        this.equipaCasa = ij.getEquipaCasa();
        this.equipaFora = ij.getEquipaFora();
        this.data = ij.getData();
        this.golosCasa = ij.golosCasa;
        this.golosFora = ij.golosFora;
        this.jogadoresIniciaisCasa = ij.getJogadoresIniciaisCasa();
        this.jogadoresIniciaisFora = ij.getJogadoresIniciaisFora();
        this.substituicoesCasa = ij.getSubstituicesCasa();
        this.substituicoesFora = ij.getSubstituicesFora();
    }

    public InfoJogo clone(){
        return new InfoJogo(this);
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

    public List<Integer> getJogadoresIniciaisCasa() {
        return new ArrayList<>(this.jogadoresIniciaisCasa);
    }

    public List<Integer> getJogadoresIniciaisFora() {
        return new ArrayList<>(this.jogadoresIniciaisFora);
    }

    public Map<Integer, Integer> getSubstituicesCasa() {
        return this.substituicoesCasa.entrySet().stream().collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    public Map<Integer, Integer> getSubstituicesFora() {
        return this.substituicoesFora.entrySet().stream().collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
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

    public void setJogadoresIniciaisCasa(List<Integer> jogadoresIniciais) {
        this.jogadoresIniciaisCasa = new ArrayList<>(jogadoresIniciais);
    }

    public void setJogadoresIniciaisFora(List<Integer> jogadoresIniciais) {
        this.jogadoresIniciaisFora = new ArrayList<>(jogadoresIniciais);
    }

    public void setSubstituicesCasa(Map<Integer, Integer> substituices) {
        this.substituicoesCasa = substituices.entrySet().stream().collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    public void setSubstituicesFora(Map<Integer, Integer> substituices) {
        this.substituicoesFora = substituices.entrySet().stream().collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }
}
