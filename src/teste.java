import java.util.ArrayList;
import java.util.List;

public class teste {
    public static void main(String[] args) {
        List<Jogador> equipa = new ArrayList<>(11);

        Jogador jog = new Defesa();
        Jogador jog1 = new Defesa();
        equipa.add(jog);

        if(equipa.contains(jog1))System.out.println("usa Equals!\n");
    }
}