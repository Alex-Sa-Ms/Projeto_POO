import java.util.List;

public interface Team {
    Team Clone();

    String getName();

    int getTeamOverall();

    List<Player> getStartingPlayers();

    List<Player> getStrikers();

    List<Player> getAttackers();

    List<Player> getDefenders();
}
