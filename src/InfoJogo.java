import java.io.Serializable;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class InfoJogo implements Serializable {
    private String equipaCasa;
    private String equipaFora;
    private LocalDate data;
    private int golosCasa;
    private int golosFora;
    private List<Integer> jogadoresIniciaisCasa;
    private List<Integer> jogadoresIniciaisFora;
    private final List<Map.Entry<Integer, Integer>> substituicoesCasa;
    private final List<Map.Entry<Integer, Integer>> substituicoesFora;

    public InfoJogo(String equipaCasa, String equipaFora, LocalDate data, List<Integer> jogadoresIniciaisCasa, List<Integer> jogadoresIniciaisFora) {
        this.equipaCasa = equipaCasa;
        this.equipaFora = equipaFora;
        this.data = data;
        this.golosCasa = 0;
        this.golosFora = 0;
        this.jogadoresIniciaisCasa = new ArrayList<>(jogadoresIniciaisCasa);
        this.jogadoresIniciaisFora = new ArrayList<>(jogadoresIniciaisFora);
        this.substituicoesCasa = new ArrayList<>();
        this.substituicoesFora = new ArrayList<>();
    }

    public InfoJogo(String equipaCasa, String equipaFora, int golosCasa, int golosFora, LocalDate data, List<Integer> jogadoresIniciaisCasa, List<AbstractMap.SimpleEntry<Integer, Integer>> substituicoesCasa, List<Integer> jogadoresIniciaisFora, List<AbstractMap.SimpleEntry<Integer, Integer>> substituicoesFora ) {
        this.equipaCasa = equipaCasa;
        this.equipaFora = equipaFora;
        this.data = data;
        this.golosCasa = golosCasa;
        this.golosFora = golosFora;
        this.jogadoresIniciaisCasa = new ArrayList<>(jogadoresIniciaisCasa);
        this.jogadoresIniciaisFora = new ArrayList<>(jogadoresIniciaisFora);
        this.substituicoesCasa = new ArrayList<>(substituicoesCasa);
        this.substituicoesFora = new ArrayList<>(substituicoesFora);
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

    public List<Map.Entry<Integer, Integer>> getSubstituicesCasa() {
        return this.substituicoesCasa.stream().map(e -> new AbstractMap.SimpleEntry<>(e.getKey(), e.getValue())).collect(Collectors.toList());
    }

    public List<Map.Entry<Integer, Integer>> getSubstituicesFora() {
        return this.substituicoesFora.stream().map(e -> new AbstractMap.SimpleEntry<>(e.getKey(), e.getValue())).collect(Collectors.toList());
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
        this.substituicoesCasa.add(new AbstractMap.SimpleEntry<>(numeroCamisolaJogadorASubstituir, numeroCamisolaJogadorSubstituto));
    }

    public void newSubstituicaoFora(int numeroCamisolaJogadorASubstituir, int numeroCamisolaJogadorSubstituto) {
        this.substituicoesFora.add(new AbstractMap.SimpleEntry<>(numeroCamisolaJogadorASubstituir, numeroCamisolaJogadorSubstituto));
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

    /** Parse **/

    public static InfoJogo parse(String input){
        String[] campos = input.split(",");
        String[] data = campos[4].split("-");
        List<Integer> jc = new ArrayList<>();
        List<Integer> jf = new ArrayList<>();
        List<AbstractMap.SimpleEntry<Integer, Integer>> subsC = new ArrayList<>();
        List<AbstractMap.SimpleEntry<Integer, Integer>> subsF = new ArrayList<>();

        for (int i = 5; i < 16; i++){
            jc.add(Integer.parseInt(campos[i]));
        }
        for (int i = 16; i < 19; i++){
            String[] sub = campos[i].split("->");
            subsC.add(new AbstractMap.SimpleEntry<>(Integer.parseInt(sub[0]), Integer.parseInt(sub[1])));
        }
        for (int i = 19; i < 30; i++){
            jf.add(Integer.parseInt(campos[i]));
        }
        for (int i = 30; i < 33; i++){
            String[] sub = campos[i].split("->");
            subsF.add(new AbstractMap.SimpleEntry<>(Integer.parseInt(sub[0]), Integer.parseInt(sub[1])));
        }

        return new InfoJogo(campos[0], campos[1], Integer.parseInt(campos[2]), Integer.parseInt(campos[3]),
                LocalDate.of(Integer.parseInt(data[0]), Integer.parseInt(data[1]), Integer.parseInt(data[2])),
                jc, subsC, jf, subsF);
    }
}
