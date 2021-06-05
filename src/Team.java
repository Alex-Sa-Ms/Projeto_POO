import java.util.AbstractMap;
import java.util.List;

public interface Team {
    Team Clone();

    String getName();

    int getTeamOverall();

    //Retorna um jogador dado um indice
    Player getPlayer(int pos);

    List<AbstractMap.SimpleEntry<Player,Integer>> getStartingPlayers();

    AbstractMap.SimpleEntry<Player,Integer> getGoalKeeper();

    List<AbstractMap.SimpleEntry<Player,Integer>> getDefenders();

    List<AbstractMap.SimpleEntry<Player,Integer>> getAttackers();

    List<AbstractMap.SimpleEntry<Player,Integer>> getStrikers();

    int substituicao(int numeroCamisolaASubstituir, int numeroCamisolaSubstituto);

    int numberOfPlayers();
}
