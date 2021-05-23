import java.util.List;

public interface Team {
    Team Clone();

    List<Player> getStartingPlayers();

    List<Player> getStrikers();

    List<Player> getAttackers();

    List<Player> getDefenders();

    String getName();
}
