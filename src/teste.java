import java.util.ArrayList;
import java.util.List;

public class teste {
    public static void main(String[] args) {
        List<Jogador> equipa = new ArrayList<>(11);

        Jogador jog = new Defesa("Ronaldo");
        Jogador jog1 = new Defesa("Xiko Fininho");
        equipa.add(jog);
        Equipa equipa1 = new Equipa("SLB");
        Equipa equipa2 = equipa1.clone();
        if(equipa.contains(jog1))System.out.println("usa Equals!\n");
    }
}