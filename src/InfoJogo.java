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

    public InfoJogo(String equipaCasa, String equipaFora, LocalDate data, List<Integer> jogadoresIniciaisCasa, List<Integer> jogadoresIniciaisFora) {
        this.equipaCasa = equipaCasa;
        this.equipaFora = equipaFora;
        this.data = data;
        this.golosCasa = 0;
        this.golosFora = 0;
        this.jogadoresIniciaisCasa = new ArrayList<>(jogadoresIniciaisCasa);
        this.jogadoresIniciaisFora = new ArrayList<>(jogadoresIniciaisFora);
        this.substituicoesCasa = new HashMap<>();
        this.substituicoesFora = new HashMap<>();
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

    public void newSubstituicaoCasa(int numeroCamisolaJogadorASubstituir, int numeroCamisolaJogadorSubstituto) {
        this.substituicoesCasa.put(numeroCamisolaJogadorASubstituir, numeroCamisolaJogadorSubstituto);
    }

    public void newSubstituicaoFora(int numeroCamisolaJogadorASubstituir, int numeroCamisolaJogadorSubstituto) {
        this.substituicoesFora.put(numeroCamisolaJogadorASubstituir, numeroCamisolaJogadorSubstituto);
    }

    //clone
    public InfoJogo clone(){
        return new InfoJogo(this);
    }

    //toString
    public String getTituloJogo(){
        return equipaCasa + " vs " + equipaFora + " (" + data + ")";
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(equipaCasa).append(" ").append(golosCasa).append(" - ").append(golosFora).append(" ").append(equipaFora).append("\n\n")
          .append("Data: ").append(data).append("\n\n")
          .append("Titulares (Equipa Casa): ").append(jogadoresIniciaisCasa).append("\n\n")
          .append("Titulares (Equipa Fora): ").append(jogadoresIniciaisFora).append("\n\n")
          .append("Substituicoes (Equipa Casa): ").append(substituicoesCasa).append("\n\n")
          .append("Substituicoes (Equipa Fora): ").append(substituicoesFora).append("\n\n");

        return sb.toString();
    }
}
